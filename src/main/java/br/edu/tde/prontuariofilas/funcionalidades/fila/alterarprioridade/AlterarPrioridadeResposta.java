package br.edu.tde.prontuariofilas.funcionalidades.fila.alterarprioridade;

import br.edu.tde.prontuariofilas.funcionalidades.fila.Prioridade;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RegistroFila;
import br.edu.tde.prontuariofilas.funcionalidades.fila.StatusFila;

/** Adaptador — corpo da resposta HTTP. */
public record AlterarPrioridadeResposta(String registroId, Prioridade prioridade, StatusFila status) {

    static AlterarPrioridadeResposta de(RegistroFila registro) {
        throw new UnsupportedOperationException("TODO");
    }
}
