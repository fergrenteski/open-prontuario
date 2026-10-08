package br.edu.tde.prontuariofilas.funcionalidades.unidade;

import br.edu.tde.prontuariofilas.funcionalidades.fila.RegistroFila;
import java.time.LocalDateTime;
import java.util.List;

/** Entidade — indicadores pré-calculados por unidade (RF18), mantidos pelo Worker. */
public class VisaoAgregada {

    private final String unidadeId;
    private Integer totalPresente;
    private Integer totalACaminho;
    private Integer tempoMedioEsperaMin;
    private LocalDateTime atualizadoEm;

    public VisaoAgregada(String unidadeId) {
        this.unidadeId = unidadeId;
    }

    /** Recalcula totais e tempo médio a partir dos registros ativos da unidade. */
    public void recalcular(List<RegistroFila> registrosAtivos) {
        throw new UnsupportedOperationException("TODO");
    }
}
