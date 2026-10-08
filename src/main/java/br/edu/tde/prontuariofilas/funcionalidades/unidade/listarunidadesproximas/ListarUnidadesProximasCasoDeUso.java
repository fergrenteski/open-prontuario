package br.edu.tde.prontuariofilas.funcionalidades.unidade.listarunidadesproximas;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.unidade.RepositorioUnidadeSaude;
import br.edu.tde.prontuariofilas.funcionalidades.unidade.RepositorioVisaoAgregada;
import java.util.List;
import org.springframework.stereotype.Service;

/** CasoDeUso — RF02. */
@Service
class ListarUnidadesProximasCasoDeUso implements CasoDeUso<ListarUnidadesProximasComando, List<UnidadeProxima>> {

    private final RepositorioUnidadeSaude unidades;
    private final RepositorioVisaoAgregada visoes;
    private final GatewayGeolocalizacao geolocalizacao;

    ListarUnidadesProximasCasoDeUso(RepositorioUnidadeSaude unidades, RepositorioVisaoAgregada visoes, GatewayGeolocalizacao geolocalizacao) {
        this.unidades = unidades;
        this.visoes = visoes;
        this.geolocalizacao = geolocalizacao;
    }

    @Override
    public List<UnidadeProxima> executar(ListarUnidadesProximasComando comando) {
        throw new UnsupportedOperationException("TODO");
    }
}
