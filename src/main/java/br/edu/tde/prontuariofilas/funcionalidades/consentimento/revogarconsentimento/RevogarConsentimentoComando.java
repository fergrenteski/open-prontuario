package br.edu.tde.prontuariofilas.funcionalidades.consentimento.revogarconsentimento;

/** CasoDeUso — pacienteId garante que só o titular revoga. */
public record RevogarConsentimentoComando(String consentimentoId, String pacienteId) {
}
