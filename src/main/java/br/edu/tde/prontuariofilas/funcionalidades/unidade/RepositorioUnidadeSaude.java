package br.edu.tde.prontuariofilas.funcionalidades.unidade;

import java.util.List;
import java.util.Optional;

/** Porta — leitura de unidades de saúde (ListarUnidadesProximas, ListarMinhasUnidades). */
public interface RepositorioUnidadeSaude {

    Optional<UnidadeSaude> buscarPorId(String unidadeId);

    List<UnidadeSaude> listarTodas();

    List<UnidadeSaude> listarPorIds(List<String> unidadeIds);
}
