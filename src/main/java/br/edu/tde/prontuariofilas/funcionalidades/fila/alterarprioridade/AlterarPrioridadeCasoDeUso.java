package br.edu.tde.prontuariofilas.funcionalidades.fila.alterarprioridade;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.fila.PublicadorEventoFila;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RegistroFila;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RepositorioRegistroFila;
import org.springframework.stereotype.Service;

/** CasoDeUso — RF11. Publica PRIORIDADE_ALTERADA. */
@Service
class AlterarPrioridadeCasoDeUso implements CasoDeUso<AlterarPrioridadeComando, RegistroFila> {

    private final RepositorioRegistroFila registros;
    private final PublicadorEventoFila eventos;

    AlterarPrioridadeCasoDeUso(RepositorioRegistroFila registros, PublicadorEventoFila eventos) {
        this.registros = registros;
        this.eventos = eventos;
    }

    @Override
    public RegistroFila executar(AlterarPrioridadeComando comando) {
        throw new UnsupportedOperationException("TODO");
    }
}
