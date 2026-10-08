package br.edu.tde.prontuariofilas.funcionalidades.prontuario.consultarprontuario;

import jakarta.validation.constraints.NotBlank;

/** Adaptador — parâmetro de rota e de consulta (?unidadeId=). */
public record ConsultarProntuarioRequisicao(@NotBlank String pacienteId, @NotBlank String unidadeId) {
}
