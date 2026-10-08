package br.edu.tde.prontuariofilas.funcionalidades.unidade.consultarvisaoagregada;

import jakarta.validation.constraints.NotBlank;

/** Adaptador — parâmetro de rota. */
public record ConsultarVisaoAgregadaRequisicao(@NotBlank String unidadeId) {
}
