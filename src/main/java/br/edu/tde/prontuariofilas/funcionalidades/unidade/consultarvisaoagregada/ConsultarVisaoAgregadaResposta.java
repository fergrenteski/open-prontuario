package br.edu.tde.prontuariofilas.funcionalidades.unidade.consultarvisaoagregada;

import br.edu.tde.prontuariofilas.funcionalidades.unidade.VisaoAgregada;
import java.time.LocalDateTime;

/** Adaptador — corpo da resposta HTTP. */
public record ConsultarVisaoAgregadaResposta(String unidadeId, Integer totalPresente, Integer totalACaminho,
                                            Integer tempoMedioEsperaMin, LocalDateTime atualizadoEm) {

    static ConsultarVisaoAgregadaResposta de(VisaoAgregada visao) {
        throw new UnsupportedOperationException("TODO");
    }
}
