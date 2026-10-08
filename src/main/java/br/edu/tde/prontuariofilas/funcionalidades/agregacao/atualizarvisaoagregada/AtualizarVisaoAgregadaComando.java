package br.edu.tde.prontuariofilas.funcionalidades.agregacao.atualizarvisaoagregada;

import br.edu.tde.prontuariofilas.funcionalidades.fila.EventoFila;

/** CasoDeUso — entrada do caso de uso. */
public record AtualizarVisaoAgregadaComando(String unidadeId, EventoFila evento) {
}
