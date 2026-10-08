package br.edu.tde.prontuariofilas.funcionalidades.consentimento.revogarconsentimento;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.compartilhado.UsuarioAtual;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** Adaptador — DELETE /consentimentos/{consentimentoId} (operationId: revogarConsentimento). */
@RestController
class RevogarConsentimentoEndpoint {

    private final CasoDeUso<RevogarConsentimentoComando, Void> casoDeUso;

    RevogarConsentimentoEndpoint(CasoDeUso<RevogarConsentimentoComando, Void> casoDeUso) {
        this.casoDeUso = casoDeUso;
    }

    @DeleteMapping("/consentimentos/{consentimentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('PACIENTE')")
    void revogar(@Valid @ModelAttribute RevogarConsentimentoRequisicao requisicao, UsuarioAtual usuario) {
        throw new UnsupportedOperationException("TODO");
    }
}
