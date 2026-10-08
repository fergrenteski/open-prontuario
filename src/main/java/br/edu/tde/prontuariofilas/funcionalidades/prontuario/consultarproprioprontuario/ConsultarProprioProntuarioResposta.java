package br.edu.tde.prontuariofilas.funcionalidades.prontuario.consultarproprioprontuario;

import br.edu.tde.prontuariofilas.funcionalidades.prontuario.Prontuario;
import java.time.LocalDateTime;
import java.util.List;

/** Adaptador — corpo da resposta HTTP. */
public record ConsultarProprioProntuarioResposta(String prontuarioId, List<String> alergias, List<String> medicacoes,
                                                 List<ItemAtendimento> atendimentos) {

    public record ItemAtendimento(String unidadeId, String descricao, LocalDateTime data) {
    }

    static ConsultarProprioProntuarioResposta de(Prontuario prontuario) {
        throw new UnsupportedOperationException("TODO");
    }
}
