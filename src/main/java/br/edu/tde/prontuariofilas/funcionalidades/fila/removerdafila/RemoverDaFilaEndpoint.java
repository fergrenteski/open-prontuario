package br.edu.tde.prontuariofilas.funcionalidades.fila.removerdafila;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** Adaptador — DELETE /fila/{registroId} (operationId: removerDaFila). */
@RestController
class RemoverDaFilaEndpoint {

    private final CasoDeUso<RemoverDaFilaComando, Void> casoDeUso;

    RemoverDaFilaEndpoint(CasoDeUso<RemoverDaFilaComando, Void> casoDeUso) {
        this.casoDeUso = casoDeUso;
    }

    @DeleteMapping("/fila/{registroId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ATENDENTE', 'PROFISSIONAL')")
    void remover(@Valid @ModelAttribute RemoverDaFilaRequisicao requisicao) {
        throw new UnsupportedOperationException("TODO");
    }
}
