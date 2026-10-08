package br.edu.tde.prontuariofilas.funcionalidades.auditoria.listarauditoriaprontuario;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.auditoria.LogAuditoria;
import br.edu.tde.prontuariofilas.funcionalidades.auditoria.RepositorioLogAuditoria;
import java.util.List;
import org.springframework.stereotype.Service;

/** CasoDeUso — RF16. */
@Service
class ListarAuditoriaProntuarioCasoDeUso implements CasoDeUso<ListarAuditoriaProntuarioComando, List<LogAuditoria>> {

    private final RepositorioLogAuditoria logs;

    ListarAuditoriaProntuarioCasoDeUso(RepositorioLogAuditoria logs) {
        this.logs = logs;
    }

    @Override
    public List<LogAuditoria> executar(ListarAuditoriaProntuarioComando comando) {
        throw new UnsupportedOperationException("TODO");
    }
}
