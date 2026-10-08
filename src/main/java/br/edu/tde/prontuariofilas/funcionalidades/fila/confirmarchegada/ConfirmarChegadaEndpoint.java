package br.edu.tde.prontuariofilas.funcionalidades.fila.confirmarchegada;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RegistroFila;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/** Adaptador — POST /fila/{registroId}/confirmar-chegada (operationId: confirmarChegada). */
@RestController
class ConfirmarChegadaEndpoint {

    private final CasoDeUso<ConfirmarChegadaComando, RegistroFila> casoDeUso;

    ConfirmarChegadaEndpoint(CasoDeUso<ConfirmarChegadaComando, RegistroFila> casoDeUso) {
        this.casoDeUso = casoDeUso;
    }

    @PostMapping("/fila/{registroId}/confirmar-chegada")
    @PreAuthorize("hasRole('ATENDENTE')")
    ConfirmarChegadaResposta confirmar(@Valid @ModelAttribute ConfirmarChegadaRequisicao requisicao) {
        throw new UnsupportedOperationException("TODO");
    }
}
