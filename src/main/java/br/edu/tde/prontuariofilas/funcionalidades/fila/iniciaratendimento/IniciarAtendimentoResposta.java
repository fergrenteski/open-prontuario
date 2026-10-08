package br.edu.tde.prontuariofilas.funcionalidades.fila.iniciaratendimento;

import br.edu.tde.prontuariofilas.funcionalidades.fila.RegistroFila;
import br.edu.tde.prontuariofilas.funcionalidades.fila.StatusFila;
import java.time.LocalDateTime;

/** Adaptador — corpo da resposta HTTP. */
public record IniciarAtendimentoResposta(String registroId, StatusFila status, LocalDateTime horarioInicioAtendimento) {

    static IniciarAtendimentoResposta de(RegistroFila registro) {
        throw new UnsupportedOperationException("TODO");
    }
}
