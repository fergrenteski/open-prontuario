package br.edu.tde.prontuariofilas.funcionalidades.consentimento.concederconsentimento;

import jakarta.validation.constraints.NotBlank;

/** Adaptador — corpo da requisição HTTP. */
public record ConcederConsentimentoRequisicao(@NotBlank String unidadeId) {
}
