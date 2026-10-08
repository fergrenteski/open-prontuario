package br.edu.tde.prontuariofilas.funcionalidades.consentimento.listarconsentimentos;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.consentimento.Consentimento;
import br.edu.tde.prontuariofilas.funcionalidades.consentimento.RepositorioConsentimento;
import java.util.List;
import org.springframework.stereotype.Service;

/** CasoDeUso — RF06 (histórico de compartilhamentos). */
@Service
class ListarConsentimentosCasoDeUso implements CasoDeUso<ListarConsentimentosComando, List<Consentimento>> {

    private final RepositorioConsentimento consentimentos;

    ListarConsentimentosCasoDeUso(RepositorioConsentimento consentimentos) {
        this.consentimentos = consentimentos;
    }

    @Override
    public List<Consentimento> executar(ListarConsentimentosComando comando) {
        throw new UnsupportedOperationException("TODO");
    }
}
