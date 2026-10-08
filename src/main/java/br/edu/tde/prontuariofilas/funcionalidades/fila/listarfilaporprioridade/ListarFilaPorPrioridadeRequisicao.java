package br.edu.tde.prontuariofilas.funcionalidades.fila.listarfilaporprioridade;

import br.edu.tde.prontuariofilas.funcionalidades.fila.StatusFila;
import jakarta.validation.constraints.NotBlank;

/** Adaptador — parâmetros de rota e de consulta (?status=). */
public record ListarFilaPorPrioridadeRequisicao(@NotBlank String unidadeId, StatusFila status) {
}
