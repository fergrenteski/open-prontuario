package br.edu.tde.prontuariofilas.funcionalidades.fila.removerdafila;

import br.edu.tde.prontuariofilas.funcionalidades.fila.MotivoSaida;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/** Adaptador — parâmetro de rota e de consulta (?motivo=). */
public record RemoverDaFilaRequisicao(@NotBlank String registroId, @NotNull MotivoSaida motivo) {
}
