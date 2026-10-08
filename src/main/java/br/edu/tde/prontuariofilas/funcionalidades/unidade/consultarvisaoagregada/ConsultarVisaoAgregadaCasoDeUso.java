package br.edu.tde.prontuariofilas.funcionalidades.unidade.consultarvisaoagregada;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.unidade.RepositorioVisaoAgregada;
import br.edu.tde.prontuariofilas.funcionalidades.unidade.VisaoAgregada;
import org.springframework.stereotype.Service;

/** CasoDeUso — RF18 (leitura). */
@Service
class ConsultarVisaoAgregadaCasoDeUso implements CasoDeUso<ConsultarVisaoAgregadaComando, VisaoAgregada> {

    private final RepositorioVisaoAgregada visoes;

    ConsultarVisaoAgregadaCasoDeUso(RepositorioVisaoAgregada visoes) {
        this.visoes = visoes;
    }

    @Override
    public VisaoAgregada executar(ConsultarVisaoAgregadaComando comando) {
        throw new UnsupportedOperationException("TODO");
    }
}
