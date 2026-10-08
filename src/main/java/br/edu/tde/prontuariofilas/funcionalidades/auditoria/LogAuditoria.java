package br.edu.tde.prontuariofilas.funcionalidades.auditoria;

import java.time.LocalDateTime;

/** Entidade — registro imutável de acesso a prontuário de terceiro (RF15). */
public record LogAuditoria(String id, String prontuarioId, String usuarioId, String unidadeId,
                           LocalDateTime acessadoEm) {
}
