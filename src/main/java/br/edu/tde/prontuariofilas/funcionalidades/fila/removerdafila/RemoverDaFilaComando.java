package br.edu.tde.prontuariofilas.funcionalidades.fila.removerdafila;

import br.edu.tde.prontuariofilas.funcionalidades.fila.MotivoSaida;

/** CasoDeUso — entrada do caso de uso. */
public record RemoverDaFilaComando(String registroId, MotivoSaida motivo) {
}
