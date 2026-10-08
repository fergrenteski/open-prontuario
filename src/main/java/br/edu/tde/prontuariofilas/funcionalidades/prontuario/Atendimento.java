package br.edu.tde.prontuariofilas.funcionalidades.prontuario;

import java.time.LocalDateTime;

/** Entidade — atendimento anterior registrado no prontuário. */
public record Atendimento(String id, String prontuarioId, String unidadeId, String descricao, LocalDateTime data) {
}
