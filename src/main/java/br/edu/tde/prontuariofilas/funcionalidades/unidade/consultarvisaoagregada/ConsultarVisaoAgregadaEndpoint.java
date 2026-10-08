package br.edu.tde.prontuariofilas.funcionalidades.unidade.consultarvisaoagregada;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.unidade.VisaoAgregada;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/** Adaptador — GET /unidades/{unidadeId}/visao-agregada (operationId: consultarVisaoAgregada). */
@RestController
class ConsultarVisaoAgregadaEndpoint {

    private final CasoDeUso<ConsultarVisaoAgregadaComando, VisaoAgregada> casoDeUso;

    ConsultarVisaoAgregadaEndpoint(CasoDeUso<ConsultarVisaoAgregadaComando, VisaoAgregada> casoDeUso) {
        this.casoDeUso = casoDeUso;
    }

    @GetMapping("/unidades/{unidadeId}/visao-agregada")
    @PreAuthorize("hasAnyRole('ATENDENTE', 'PROFISSIONAL', 'GESTOR')")
    ConsultarVisaoAgregadaResposta consultar(@Valid @ModelAttribute ConsultarVisaoAgregadaRequisicao requisicao) {
        throw new UnsupportedOperationException("TODO");
    }
}
