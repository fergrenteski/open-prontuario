package br.edu.tde.prontuariofilas.funcionalidades.prontuario.consultarproprioprontuario;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.prontuario.Prontuario;
import br.edu.tde.prontuariofilas.funcionalidades.prontuario.RepositorioProntuario;
import org.springframework.stereotype.Service;

/** CasoDeUso — RF06. Sem consentimento e sem auditoria de terceiros (arc42 §8). */
@Service
class ConsultarProprioProntuarioCasoDeUso implements CasoDeUso<ConsultarProprioProntuarioComando, Prontuario> {

    private final RepositorioProntuario prontuarios;

    ConsultarProprioProntuarioCasoDeUso(RepositorioProntuario prontuarios) {
        this.prontuarios = prontuarios;
    }

    @Override
    public Prontuario executar(ConsultarProprioProntuarioComando comando) {
        throw new UnsupportedOperationException("TODO");
    }
}
