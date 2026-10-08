package br.edu.tde.prontuariofilas.funcionalidades.prontuario.consultarprontuario;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.compartilhado.UsuarioAtual;
import br.edu.tde.prontuariofilas.funcionalidades.prontuario.Prontuario;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/** Adaptador — GET /prontuarios/{pacienteId} (operationId: consultarProntuario). */
@RestController
class ConsultarProntuarioEndpoint {

    private final CasoDeUso<ConsultarProntuarioComando, Prontuario> casoDeUso;

    ConsultarProntuarioEndpoint(CasoDeUso<ConsultarProntuarioComando, Prontuario> casoDeUso) {
        this.casoDeUso = casoDeUso;
    }

    @GetMapping("/prontuarios/{pacienteId}")
    @PreAuthorize("hasAnyRole('ATENDENTE', 'PROFISSIONAL')")
    ConsultarProntuarioResposta consultar(@Valid @ModelAttribute ConsultarProntuarioRequisicao requisicao, UsuarioAtual usuario) {
        throw new UnsupportedOperationException("TODO");
    }
}
