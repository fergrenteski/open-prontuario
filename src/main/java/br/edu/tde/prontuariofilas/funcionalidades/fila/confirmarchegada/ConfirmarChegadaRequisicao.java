package br.edu.tde.prontuariofilas.funcionalidades.fila.confirmarchegada;

import jakarta.validation.constraints.NotBlank;

/** Adaptador — parâmetro de rota. */
public record ConfirmarChegadaRequisicao(@NotBlank String registroId) {
}
