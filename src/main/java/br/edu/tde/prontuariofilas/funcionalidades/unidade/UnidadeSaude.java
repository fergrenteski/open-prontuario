package br.edu.tde.prontuariofilas.funcionalidades.unidade;

/** Entidade — UBS ou UPA. */
public record UnidadeSaude(String id, String nome, TipoUnidade tipo, Double latitude, Double longitude) {

    /** Distância em linha reta (km), usada como pré-filtro antes do Azure Maps. */
    public Double calcularDistancia(Double latitudeOrigem, Double longitudeOrigem) {
        throw new UnsupportedOperationException("TODO");
    }
}
