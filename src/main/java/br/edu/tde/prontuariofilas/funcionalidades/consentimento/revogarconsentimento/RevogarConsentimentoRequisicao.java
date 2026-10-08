package br.edu.tde.prontuariofilas.funcionalidades.consentimento.revogarconsentimento;

import jakarta.validation.constraints.NotBlank;

/** Adaptador — parâmetro de rota. */
public record RevogarConsentimentoRequisicao(@NotBlank String consentimentoId) {
}
