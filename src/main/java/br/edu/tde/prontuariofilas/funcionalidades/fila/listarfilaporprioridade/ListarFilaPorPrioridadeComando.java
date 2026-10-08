package br.edu.tde.prontuariofilas.funcionalidades.fila.listarfilaporprioridade;

import br.edu.tde.prontuariofilas.funcionalidades.fila.StatusFila;

/** CasoDeUso — status opcional distingue A_CAMINHO de AGUARDANDO (RF09). */
public record ListarFilaPorPrioridadeComando(String unidadeId, StatusFila status) {
}
