package br.edu.tde.prontuariofilas.funcionalidades.fila.iniciaratendimento;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RegistroFila;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/** Adaptador — POST /fila/{registroId}/iniciar-atendimento (operationId: iniciarAtendimento). */
@RestController
class IniciarAtendimentoEndpoint {

    private final CasoDeUso<IniciarAtendimentoComando, RegistroFila> casoDeUso;

    IniciarAtendimentoEndpoint(CasoDeUso<IniciarAtendimentoComando, RegistroFila> casoDeUso) {
        this.casoDeUso = casoDeUso;
    }

    @PostMapping("/fila/{registroId}/iniciar-atendimento")
    @PreAuthorize("hasAnyRole('ATENDENTE', 'PROFISSIONAL')")
    IniciarAtendimentoResposta iniciar(@Valid @ModelAttribute IniciarAtendimentoRequisicao requisicao) {
        throw new UnsupportedOperationException("TODO");
    }
}
