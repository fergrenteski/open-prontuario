package br.edu.tde.prontuariofilas.funcionalidades.unidade.listarunidadesproximas;

import br.edu.tde.prontuariofilas.funcionalidades.unidade.UnidadeSaude;
import br.edu.tde.prontuariofilas.funcionalidades.unidade.VisaoAgregada;

/** CasoDeUso — saída: unidade + distância + status de fila (RF02). */
public record UnidadeProxima(UnidadeSaude unidade, Double distanciaKm, VisaoAgregada visao) {
}
