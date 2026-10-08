package br.edu.tde.prontuariofilas.funcionalidades.fila.avisarchegada;

import java.util.List;

/** CasoDeUso — entrada do caso de uso. */
public record AvisarChegadaComando(String pacienteId, String unidadeId, List<String> sintomas) {
}
