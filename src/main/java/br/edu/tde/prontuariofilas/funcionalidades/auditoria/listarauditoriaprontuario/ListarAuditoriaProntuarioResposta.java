package br.edu.tde.prontuariofilas.funcionalidades.auditoria.listarauditoriaprontuario;

import br.edu.tde.prontuariofilas.funcionalidades.auditoria.LogAuditoria;
import java.time.LocalDateTime;

/** Adaptador — corpo da resposta HTTP. */
public record ListarAuditoriaProntuarioResposta(String usuarioId, String unidadeId, LocalDateTime acessadoEm) {

    static ListarAuditoriaProntuarioResposta de(LogAuditoria log) {
        throw new UnsupportedOperationException("TODO");
    }
}
