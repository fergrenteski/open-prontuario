package br.edu.tde.prontuariofilas.funcionalidades.fila.iniciaratendimento;

import jakarta.validation.constraints.NotBlank;

/** Adaptador — parâmetro de rota. */
public record IniciarAtendimentoRequisicao(@NotBlank String registroId) {
}
