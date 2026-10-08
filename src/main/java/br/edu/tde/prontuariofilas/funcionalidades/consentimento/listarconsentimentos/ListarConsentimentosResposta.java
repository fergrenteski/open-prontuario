package br.edu.tde.prontuariofilas.funcionalidades.consentimento.listarconsentimentos;

import br.edu.tde.prontuariofilas.funcionalidades.consentimento.Consentimento;
import java.time.LocalDateTime;

/** Adaptador — corpo da resposta HTTP. */
public record ListarConsentimentosResposta(String consentimentoId, String unidadeId, Boolean ativo,
                                          LocalDateTime concedidoEm, LocalDateTime revogadoEm) {

    static ListarConsentimentosResposta de(Consentimento consentimento) {
        throw new UnsupportedOperationException("TODO");
    }
}
