package br.edu.tde.prontuariofilas.funcionalidades.fila.alterarprioridade;

import br.edu.tde.prontuariofilas.funcionalidades.fila.Prioridade;

/** CasoDeUso — entrada do caso de uso. */
public record AlterarPrioridadeComando(String registroId, Prioridade novaPrioridade) {
}
