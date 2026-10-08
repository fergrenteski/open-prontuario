package br.edu.tde.prontuariofilas.funcionalidades.fila.listarfilaporprioridade;

import br.edu.tde.prontuariofilas.funcionalidades.fila.Prioridade;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RegistroFila;
import br.edu.tde.prontuariofilas.funcionalidades.fila.StatusFila;
import java.time.LocalDateTime;
import java.util.List;

/** Adaptador — corpo da resposta HTTP. */
public record ListarFilaPorPrioridadeResposta(String registroId, String pacienteId, Prioridade prioridade,
                                              StatusFila status, boolean presente, List<String> sintomas,
                                              LocalDateTime horarioAviso, LocalDateTime horarioChegada) {

    static ListarFilaPorPrioridadeResposta de(RegistroFila registro) {
        throw new UnsupportedOperationException("TODO");
    }
}
