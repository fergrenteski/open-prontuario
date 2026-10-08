package br.edu.tde.prontuariofilas.funcionalidades.consentimento.concederconsentimento;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.compartilhado.UsuarioAtual;
import br.edu.tde.prontuariofilas.funcionalidades.consentimento.Consentimento;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** Adaptador — POST /consentimentos (operationId: concederConsentimento). */
@RestController
class ConcederConsentimentoEndpoint {

    private final CasoDeUso<ConcederConsentimentoComando, Consentimento> casoDeUso;

    ConcederConsentimentoEndpoint(CasoDeUso<ConcederConsentimentoComando, Consentimento> casoDeUso) {
        this.casoDeUso = casoDeUso;
    }

    @PostMapping("/consentimentos")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('PACIENTE')")
    ConcederConsentimentoResposta conceder(@Valid @RequestBody ConcederConsentimentoRequisicao requisicao,
                                          UsuarioAtual usuario) {
        throw new UnsupportedOperationException("TODO");
    }
}
