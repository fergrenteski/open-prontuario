package br.edu.tde.prontuariofilas.funcionalidades.fila.listarfilaporprioridade;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RegistroFila;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RepositorioRegistroFila;
import java.util.List;
import org.springframework.stereotype.Service;

/** CasoDeUso — RF08, RF09. Ordena por prioridade e desempata por horário (chegada ou aviso). */
@Service
class ListarFilaPorPrioridadeCasoDeUso implements CasoDeUso<ListarFilaPorPrioridadeComando, List<RegistroFila>> {

    private final RepositorioRegistroFila registros;

    ListarFilaPorPrioridadeCasoDeUso(RepositorioRegistroFila registros) {
        this.registros = registros;
    }

    @Override
    public List<RegistroFila> executar(ListarFilaPorPrioridadeComando comando) {
        throw new UnsupportedOperationException("TODO");
    }
}
