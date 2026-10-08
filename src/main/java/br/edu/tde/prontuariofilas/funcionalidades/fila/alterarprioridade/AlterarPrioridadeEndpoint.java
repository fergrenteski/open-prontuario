package br.edu.tde.prontuariofilas.funcionalidades.fila.alterarprioridade;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RegistroFila;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** Adaptador — PATCH /fila/{registroId}/prioridade (operationId: alterarPrioridade). */
@RestController
class AlterarPrioridadeEndpoint {

    private final CasoDeUso<AlterarPrioridadeComando, RegistroFila> casoDeUso;

    AlterarPrioridadeEndpoint(CasoDeUso<AlterarPrioridadeComando, RegistroFila> casoDeUso) {
        this.casoDeUso = casoDeUso;
    }

    @PatchMapping("/fila/{registroId}/prioridade")
    @PreAuthorize("hasAnyRole('ATENDENTE', 'PROFISSIONAL')")
    AlterarPrioridadeResposta alterar(@PathVariable String registroId,
                                    @Valid @RequestBody AlterarPrioridadeRequisicao requisicao) {
        throw new UnsupportedOperationException("TODO");
    }
}
