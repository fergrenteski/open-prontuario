package br.edu.tde.prontuariofilas.funcionalidades.unidade.listarunidadesproximas;

import br.edu.tde.prontuariofilas.funcionalidades.unidade.UnidadeSaude;
import java.util.List;
import java.util.Map;

/** Porta — distância por rota até cada unidade. Exclusiva desta fatia. */
public interface GatewayGeolocalizacao {

    /** @return unidadeId → distância em km */
    Map<String, Double> calcularDistanciasKm(Double latitude, Double longitude, List<UnidadeSaude> unidades);
}
