package br.edu.tde.prontuariofilas.funcionalidades.auditoria;

import java.util.List;

/** Porta — gravação (RF15, por prontuario/consultarprontuario) e consulta (RF16) do log de auditoria. */
public interface RepositorioLogAuditoria {

    void registrar(LogAuditoria log);

    List<LogAuditoria> listarPorProntuario(String prontuarioId);
}
