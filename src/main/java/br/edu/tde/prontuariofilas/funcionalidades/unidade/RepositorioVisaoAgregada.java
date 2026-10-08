package br.edu.tde.prontuariofilas.funcionalidades.unidade;

import java.util.Optional;

/** Porta — leitura pela API (ListarUnidadesProximas, ConsultarVisaoAgregada), escrita pelo Worker. */
public interface RepositorioVisaoAgregada {

    Optional<VisaoAgregada> buscarPorUnidade(String unidadeId);

    void salvar(VisaoAgregada visao);
}
