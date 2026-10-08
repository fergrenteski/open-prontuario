package br.edu.tde.prontuariofilas.funcionalidades.consentimento.concederconsentimento;

import br.edu.tde.prontuariofilas.funcionalidades.consentimento.Consentimento;
import java.time.LocalDateTime;

/** Adaptador — corpo da resposta HTTP. */
public record ConcederConsentimentoResposta(String consentimentoId, String unidadeId, Boolean ativo,
                                           LocalDateTime concedidoEm) {

    static ConcederConsentimentoResposta de(Consentimento consentimento) {
        throw new UnsupportedOperationException("TODO");
    }
}
