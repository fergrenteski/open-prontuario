package br.edu.tde.prontuariofilas.funcionalidades.unidade.listarminhasunidades;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.compartilhado.UsuarioAtual;
import br.edu.tde.prontuariofilas.funcionalidades.unidade.UnidadeSaude;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/** Adaptador — GET /unidades/minhas (operationId: listarMinhasUnidades). */
@RestController
class ListarMinhasUnidadesEndpoint {

    private final CasoDeUso<ListarMinhasUnidadesComando, List<UnidadeSaude>> casoDeUso;

    ListarMinhasUnidadesEndpoint(CasoDeUso<ListarMinhasUnidadesComando, List<UnidadeSaude>> casoDeUso) {
        this.casoDeUso = casoDeUso;
    }

    @GetMapping("/unidades/minhas")
    @PreAuthorize("hasAnyRole('ATENDENTE', 'PROFISSIONAL', 'GESTOR')")
    List<ListarMinhasUnidadesResposta> listar(@ModelAttribute ListarMinhasUnidadesRequisicao requisicao, UsuarioAtual usuario) {
        throw new UnsupportedOperationException("TODO");
    }
}
