package br.edu.tde.prontuariofilas.funcionalidades.fila.confirmarchegada;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.fila.PublicadorEventoFila;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RegistroFila;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RepositorioRegistroFila;
import org.springframework.stereotype.Service;

/** CasoDeUso — RF10. Publica PACIENTE_CHEGOU. */
@Service
class ConfirmarChegadaCasoDeUso implements CasoDeUso<ConfirmarChegadaComando, RegistroFila> {

    private final RepositorioRegistroFila registros;
    private final PublicadorEventoFila eventos;

    ConfirmarChegadaCasoDeUso(RepositorioRegistroFila registros, PublicadorEventoFila eventos) {
        this.registros = registros;
        this.eventos = eventos;
    }

    @Override
    public RegistroFila executar(ConfirmarChegadaComando comando) {
        throw new UnsupportedOperationException("TODO");
    }
}
