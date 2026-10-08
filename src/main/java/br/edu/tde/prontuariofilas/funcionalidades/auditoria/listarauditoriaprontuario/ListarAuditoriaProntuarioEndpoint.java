package br.edu.tde.prontuariofilas.funcionalidades.auditoria.listarauditoriaprontuario;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.auditoria.LogAuditoria;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/** Adaptador — GET /auditoria/prontuarios/{prontuarioId} (operationId: listarAuditoriaProntuario). */
@RestController
class ListarAuditoriaProntuarioEndpoint {

    private final CasoDeUso<ListarAuditoriaProntuarioComando, List<LogAuditoria>> casoDeUso;

    ListarAuditoriaProntuarioEndpoint(CasoDeUso<ListarAuditoriaProntuarioComando, List<LogAuditoria>> casoDeUso) {
        this.casoDeUso = casoDeUso;
    }

    @GetMapping("/auditoria/prontuarios/{prontuarioId}")
    @PreAuthorize("hasAnyRole('GESTOR', 'PACIENTE')")
    List<ListarAuditoriaProntuarioResposta> listar(@Valid @ModelAttribute ListarAuditoriaProntuarioRequisicao requisicao) {
        throw new UnsupportedOperationException("TODO");
    }
}
