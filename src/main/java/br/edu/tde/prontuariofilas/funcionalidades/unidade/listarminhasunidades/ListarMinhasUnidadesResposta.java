package br.edu.tde.prontuariofilas.funcionalidades.unidade.listarminhasunidades;

import br.edu.tde.prontuariofilas.funcionalidades.unidade.TipoUnidade;
import br.edu.tde.prontuariofilas.funcionalidades.unidade.UnidadeSaude;

/** Adaptador — corpo da resposta HTTP. */
public record ListarMinhasUnidadesResposta(String unidadeId, String nome, TipoUnidade tipo) {

    static ListarMinhasUnidadesResposta de(UnidadeSaude unidade) {
        throw new UnsupportedOperationException("TODO");
    }
}
