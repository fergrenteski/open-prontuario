package br.edu.tde.prontuariofilas.funcionalidades.consentimento.revogarconsentimento;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.consentimento.RepositorioConsentimento;
import org.springframework.stereotype.Service;

/** CasoDeUso — RF05 (revogação, efeito na requisição seguinte). */
@Service
class RevogarConsentimentoCasoDeUso implements CasoDeUso<RevogarConsentimentoComando, Void> {

    private final RepositorioConsentimento consentimentos;

    RevogarConsentimentoCasoDeUso(RepositorioConsentimento consentimentos) {
        this.consentimentos = consentimentos;
    }

    @Override
    public Void executar(RevogarConsentimentoComando comando) {
        throw new UnsupportedOperationException("TODO");
    }
}
