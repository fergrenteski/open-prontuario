package br.edu.tde.prontuariofilas.funcionalidades.auditoria.listarauditoriaprontuario;

import jakarta.validation.constraints.NotBlank;

/** Adaptador — parâmetro de rota. */
public record ListarAuditoriaProntuarioRequisicao(@NotBlank String prontuarioId) {
}
