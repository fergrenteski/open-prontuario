package br.edu.tde.prontuariofilas.funcionalidades.fila.alterarprioridade;

import br.edu.tde.prontuariofilas.funcionalidades.fila.Prioridade;
import jakarta.validation.constraints.NotNull;

/** Adaptador — corpo da requisição HTTP. */
public record AlterarPrioridadeRequisicao(@NotNull Prioridade prioridade) {
}
