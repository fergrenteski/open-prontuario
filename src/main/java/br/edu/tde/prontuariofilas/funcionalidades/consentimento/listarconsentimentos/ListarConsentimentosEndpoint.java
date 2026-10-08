package br.edu.tde.prontuariofilas.funcionalidades.consentimento.listarconsentimentos;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.compartilhado.UsuarioAtual;
import br.edu.tde.prontuariofilas.funcionalidades.consentimento.Consentimento;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/** Adaptador — GET /consentimentos (operationId: listarConsentimentos). */
@RestController
class ListarConsentimentosEndpoint {

    private final CasoDeUso<ListarConsentimentosComando, List<Consentimento>> casoDeUso;

    ListarConsentimentosEndpoint(CasoDeUso<ListarConsentimentosComando, List<Consentimento>> casoDeUso) {
        this.casoDeUso = casoDeUso;
    }

    @GetMapping("/consentimentos")
    @PreAuthorize("hasRole('PACIENTE')")
    List<ListarConsentimentosResposta> listar(@ModelAttribute ListarConsentimentosRequisicao requisicao, UsuarioAtual usuario) {
        throw new UnsupportedOperationException("TODO");
    }
}
