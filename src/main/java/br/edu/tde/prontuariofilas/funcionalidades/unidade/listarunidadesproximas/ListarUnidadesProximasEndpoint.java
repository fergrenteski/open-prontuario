package br.edu.tde.prontuariofilas.funcionalidades.unidade.listarunidadesproximas;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/** Adaptador — GET /unidades/proximas (operationId: listarUnidadesProximas). */
@RestController
class ListarUnidadesProximasEndpoint {

    private final CasoDeUso<ListarUnidadesProximasComando, List<UnidadeProxima>> casoDeUso;

    ListarUnidadesProximasEndpoint(CasoDeUso<ListarUnidadesProximasComando, List<UnidadeProxima>> casoDeUso) {
        this.casoDeUso = casoDeUso;
    }

    @GetMapping("/unidades/proximas")
    @PreAuthorize("hasRole('PACIENTE')")
    List<ListarUnidadesProximasResposta> listar(@Valid @ModelAttribute ListarUnidadesProximasRequisicao requisicao) {
        throw new UnsupportedOperationException("TODO");
    }
}
