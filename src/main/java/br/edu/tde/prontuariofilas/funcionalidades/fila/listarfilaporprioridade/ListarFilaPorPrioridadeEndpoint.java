package br.edu.tde.prontuariofilas.funcionalidades.fila.listarfilaporprioridade;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RegistroFila;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/** Adaptador — GET /fila/{unidadeId} (operationId: listarFilaPorPrioridade). */
@RestController
class ListarFilaPorPrioridadeEndpoint {

    private final CasoDeUso<ListarFilaPorPrioridadeComando, List<RegistroFila>> casoDeUso;

    ListarFilaPorPrioridadeEndpoint(CasoDeUso<ListarFilaPorPrioridadeComando, List<RegistroFila>> casoDeUso) {
        this.casoDeUso = casoDeUso;
    }

    @GetMapping("/fila/{unidadeId}")
    @PreAuthorize("hasAnyRole('ATENDENTE', 'PROFISSIONAL')")
    List<ListarFilaPorPrioridadeResposta> listar(@Valid @ModelAttribute ListarFilaPorPrioridadeRequisicao requisicao) {
        throw new UnsupportedOperationException("TODO");
    }
}
