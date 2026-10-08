package br.edu.tde.prontuariofilas.funcionalidades.unidade.listarminhasunidades;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.unidade.RepositorioUnidadeSaude;
import br.edu.tde.prontuariofilas.funcionalidades.unidade.UnidadeSaude;
import java.util.List;
import org.springframework.stereotype.Service;

/** CasoDeUso — RF07. */
@Service
class ListarMinhasUnidadesCasoDeUso implements CasoDeUso<ListarMinhasUnidadesComando, List<UnidadeSaude>> {

    private final RepositorioUnidadeSaude unidades;

    ListarMinhasUnidadesCasoDeUso(RepositorioUnidadeSaude unidades) {
        this.unidades = unidades;
    }

    @Override
    public List<UnidadeSaude> executar(ListarMinhasUnidadesComando comando) {
        throw new UnsupportedOperationException("TODO");
    }
}
