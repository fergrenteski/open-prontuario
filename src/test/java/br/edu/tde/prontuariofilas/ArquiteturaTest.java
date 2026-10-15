package br.edu.tde.prontuariofilas;

import static com.tngtech.archunit.base.DescribedPredicate.or;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.simpleNameEndingWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/**
 * Testes de arquitetura: Clean Architecture + Vertical Slice.
 * Feature = funcionalidades.<feature> (domínio + portas + adaptadores);
 * Slice = funcionalidades.<feature>.<casodeuso> (Endpoint, CasoDeUso, Comando, Requisicao, Resposta).
 */
@AnalyzeClasses(packages = "br.edu.tde.prontuariofilas", importOptions = ImportOption.DoNotIncludeTests.class)
class ArquiteturaTest {

    // Adaptadores de infraestrutura (Mongo, Service Bus, Azure Maps, configurações)
    static final DescribedPredicate<JavaClass> INFRA = or(
            simpleNameEndingWith("Mongo"), simpleNameEndingWith("Documento"),
            simpleNameEndingWith("ServiceBus"), simpleNameEndingWith("AzureMaps"),
            JavaClass.Predicates.simpleNameStartingWith("Configuracao"));

    // Domínio: classes na raiz de cada feature que não são adaptadores
    static final DescribedPredicate<JavaClass> DOMINIO =
            resideInAPackage("..funcionalidades.*").and(DescribedPredicate.not(INFRA)).as("classes de domínio");

    @ArchTest // Domínio não depende de framework, infraestrutura nem das slices
    static final ArchRule dominio_nao_depende_de_outras_camadas = noClasses().that(DOMINIO)
            .should().dependOnClassesThat().resideInAnyPackage("org.springframework..", "com.azure..")
            .orShould().dependOnClassesThat(INFRA)
            .orShould().dependOnClassesThat().resideInAPackage("..funcionalidades.*.*..");

    @ArchTest // Application (CasoDeUso) não depende de infraestrutura nem da apresentação (Endpoint)
    static final ArchRule casos_de_uso_nao_dependem_de_infra_nem_apresentacao = noClasses()
            .that().haveSimpleNameEndingWith("CasoDeUso")
            .should().dependOnClassesThat(INFRA)
            .orShould().dependOnClassesThat().haveSimpleNameEndingWith("Endpoint")
            .orShould().dependOnClassesThat().resideInAnyPackage("org.springframework.web..", "org.springframework.data..");

    @ArchTest // Apresentação (Endpoint) não acessa infraestrutura diretamente
    static final ArchRule endpoints_nao_dependem_de_infra = noClasses()
            .that().haveSimpleNameEndingWith("Endpoint")
            .should().dependOnClassesThat(INFRA);

    @ArchTest // Vertical Slice: um caso de uso não importa outro
    static final ArchRule slices_sao_independentes = slices()
            .matching("..funcionalidades.(*).(*)..").should().notDependOnEachOther();

    @ArchTest // Sem ciclos entre as features
    static final ArchRule features_sem_ciclos = slices()
            .matching("..funcionalidades.(*)..").should().beFreeOfCycles();
}
