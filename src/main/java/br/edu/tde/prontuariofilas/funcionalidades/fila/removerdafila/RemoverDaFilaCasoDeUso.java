package br.edu.tde.prontuariofilas.funcionalidades.fila.removerdafila;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.fila.PublicadorEventoFila;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RepositorioRegistroFila;
import org.springframework.stereotype.Service;

/** CasoDeUso — RF14. Publica PACIENTE_REMOVIDO. */
@Service
class RemoverDaFilaCasoDeUso implements CasoDeUso<RemoverDaFilaComando, Void> {

    private final RepositorioRegistroFila registros;
    private final PublicadorEventoFila eventos;

    RemoverDaFilaCasoDeUso(RepositorioRegistroFila registros, PublicadorEventoFila eventos) {
        this.registros = registros;
        this.eventos = eventos;
    }

    @Override
    public Void executar(RemoverDaFilaComando comando) {
        throw new UnsupportedOperationException("TODO");
    }
}
