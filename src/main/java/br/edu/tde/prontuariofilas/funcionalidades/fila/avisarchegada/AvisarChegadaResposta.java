package br.edu.tde.prontuariofilas.funcionalidades.fila.avisarchegada;

import br.edu.tde.prontuariofilas.funcionalidades.fila.Prioridade;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RegistroFila;
import br.edu.tde.prontuariofilas.funcionalidades.fila.StatusFila;
import java.time.LocalDateTime;

/** Adaptador — corpo da resposta HTTP. */
public record AvisarChegadaResposta(String registroId, String unidadeId, StatusFila status, Prioridade prioridade,
                                    LocalDateTime horarioAviso) {

    static AvisarChegadaResposta de(RegistroFila registro) {
        return new AvisarChegadaResposta(registro.id(), registro.unidadeId(), registro.status(),
                registro.prioridade(), registro.horarioAviso());
    }
}
