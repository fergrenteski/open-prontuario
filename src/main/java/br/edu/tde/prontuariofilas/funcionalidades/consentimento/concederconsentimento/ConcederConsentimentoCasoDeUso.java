package br.edu.tde.prontuariofilas.funcionalidades.consentimento.concederconsentimento;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.consentimento.Consentimento;
import br.edu.tde.prontuariofilas.funcionalidades.consentimento.RepositorioConsentimento;
import org.springframework.stereotype.Service;

/** CasoDeUso — RF05 (concessão). */
@Service
class ConcederConsentimentoCasoDeUso implements CasoDeUso<ConcederConsentimentoComando, Consentimento> {

    private final RepositorioConsentimento consentimentos;

    ConcederConsentimentoCasoDeUso(RepositorioConsentimento consentimentos) {
        this.consentimentos = consentimentos;
    }

    @Override
    public Consentimento executar(ConcederConsentimentoComando comando) {
        throw new UnsupportedOperationException("TODO");
    }
}
