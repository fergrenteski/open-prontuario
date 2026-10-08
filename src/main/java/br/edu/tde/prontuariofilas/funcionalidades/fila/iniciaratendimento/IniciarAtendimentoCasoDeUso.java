package br.edu.tde.prontuariofilas.funcionalidades.fila.iniciaratendimento;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.fila.PublicadorEventoFila;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RegistroFila;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RepositorioRegistroFila;
import org.springframework.stereotype.Service;

/** CasoDeUso — RF13. Publica ATENDIMENTO_INICIADO. */
@Service
class IniciarAtendimentoCasoDeUso implements CasoDeUso<IniciarAtendimentoComando, RegistroFila> {

    private final RepositorioRegistroFila registros;
    private final PublicadorEventoFila eventos;

    IniciarAtendimentoCasoDeUso(RepositorioRegistroFila registros, PublicadorEventoFila eventos) {
        this.registros = registros;
        this.eventos = eventos;
    }

    @Override
    public RegistroFila executar(IniciarAtendimentoComando comando) {
        throw new UnsupportedOperationException("TODO");
    }
}
