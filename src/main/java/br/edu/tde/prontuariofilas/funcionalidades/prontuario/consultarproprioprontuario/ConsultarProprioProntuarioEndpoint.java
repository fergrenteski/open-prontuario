package br.edu.tde.prontuariofilas.funcionalidades.prontuario.consultarproprioprontuario;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.compartilhado.UsuarioAtual;
import br.edu.tde.prontuariofilas.funcionalidades.prontuario.Prontuario;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/** Adaptador — GET /prontuarios/me (operationId: consultarProprioProntuario). */
@RestController
class ConsultarProprioProntuarioEndpoint {

    private final CasoDeUso<ConsultarProprioProntuarioComando, Prontuario> casoDeUso;

    ConsultarProprioProntuarioEndpoint(CasoDeUso<ConsultarProprioProntuarioComando, Prontuario> casoDeUso) {
        this.casoDeUso = casoDeUso;
    }

    @GetMapping("/prontuarios/me")
    @PreAuthorize("hasRole('PACIENTE')")
    ConsultarProprioProntuarioResposta consultar(@ModelAttribute ConsultarProprioProntuarioRequisicao requisicao, UsuarioAtual usuario) {
        throw new UnsupportedOperationException("TODO");
    }
}
