package br.edu.tde.prontuariofilas.funcionalidades.unidade.listarunidadesproximas;

import br.edu.tde.prontuariofilas.funcionalidades.unidade.TipoUnidade;

/** Adaptador — corpo da resposta HTTP. */
public record ListarUnidadesProximasResposta(String unidadeId, String nome, TipoUnidade tipo, Double distanciaKm,
                                            Integer totalPresente, Integer totalACaminho,
                                            Integer tempoMedioEsperaMin) {

    static ListarUnidadesProximasResposta de(UnidadeProxima unidadeProxima) {
        throw new UnsupportedOperationException("TODO");
    }
}
