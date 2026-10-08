package br.edu.tde.prontuariofilas.funcionalidades.unidade.listarunidadesproximas;

import br.edu.tde.prontuariofilas.funcionalidades.unidade.UnidadeSaude;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/** Adaptador — Azure Maps Route Matrix (REST/HTTPS). */
@Component
class GatewayGeolocalizacaoAzureMaps implements GatewayGeolocalizacao {

    private final RestClient azureMaps;
    private final String chave;

    GatewayGeolocalizacaoAzureMaps(RestClient.Builder construtor,
                                   @Value("${azure.maps.url}") String url,
                                   @Value("${azure.maps.key}") String chave) {
        this.azureMaps = construtor.baseUrl(url).build();
        this.chave = chave;
    }

    @Override
    public Map<String, Double> calcularDistanciasKm(Double latitude, Double longitude, List<UnidadeSaude> unidades) {
        throw new UnsupportedOperationException("TODO");
    }
}
