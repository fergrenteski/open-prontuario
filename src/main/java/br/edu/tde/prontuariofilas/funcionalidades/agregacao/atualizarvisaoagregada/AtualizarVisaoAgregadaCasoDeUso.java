package br.edu.tde.prontuariofilas.funcionalidades.agregacao.atualizarvisaoagregada;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RepositorioRegistroFila;
import br.edu.tde.prontuariofilas.funcionalidades.unidade.RepositorioVisaoAgregada;
import org.springframework.stereotype.Service;

/** CasoDeUso — RF17 + RF18. Recalcula a VisaoAgregada da unidade a cada evento de fila. */
@Service
class AtualizarVisaoAgregadaCasoDeUso implements CasoDeUso<AtualizarVisaoAgregadaComando, Void> {

    private final RepositorioRegistroFila registros;
    private final RepositorioVisaoAgregada visoes;

    AtualizarVisaoAgregadaCasoDeUso(RepositorioRegistroFila registros, RepositorioVisaoAgregada visoes) {
        this.registros = registros;
        this.visoes = visoes;
    }

    @Override
    public Void executar(AtualizarVisaoAgregadaComando comando) {
        throw new UnsupportedOperationException("TODO");
    }
}
