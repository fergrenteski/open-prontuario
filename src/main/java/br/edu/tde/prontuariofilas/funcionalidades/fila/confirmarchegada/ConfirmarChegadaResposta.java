package br.edu.tde.prontuariofilas.funcionalidades.fila.confirmarchegada;

import br.edu.tde.prontuariofilas.funcionalidades.fila.RegistroFila;
import br.edu.tde.prontuariofilas.funcionalidades.fila.StatusFila;
import java.time.LocalDateTime;

/** Adaptador — corpo da resposta HTTP. */
public record ConfirmarChegadaResposta(String registroId, StatusFila status, LocalDateTime horarioChegada) {

    static ConfirmarChegadaResposta de(RegistroFila registro) {
        throw new UnsupportedOperationException("TODO");
    }
}
