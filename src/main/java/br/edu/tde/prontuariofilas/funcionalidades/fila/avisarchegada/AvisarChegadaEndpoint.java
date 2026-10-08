package br.edu.tde.prontuariofilas.funcionalidades.fila.avisarchegada;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.compartilhado.UsuarioAtual;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RegistroFila;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** Adaptador — POST /fila/{unidadeId}/aviso-chegada (operationId: avisarChegada). */
@RestController
class AvisarChegadaEndpoint {

    private final CasoDeUso<AvisarChegadaComando, RegistroFila> casoDeUso;

    AvisarChegadaEndpoint(CasoDeUso<AvisarChegadaComando, RegistroFila> casoDeUso) {
        this.casoDeUso = casoDeUso;
    }

    @PostMapping("/fila/{unidadeId}/aviso-chegada")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('PACIENTE')")
    AvisarChegadaResposta avisar(@PathVariable String unidadeId,
                                @Valid @RequestBody AvisarChegadaRequisicao requisicao,
                                UsuarioAtual usuario) {
        RegistroFila registro = casoDeUso.executar(
                new AvisarChegadaComando(usuario.id(), unidadeId, requisicao.sintomas()));
        return AvisarChegadaResposta.de(registro);
    }
}
