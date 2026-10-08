package br.edu.tde.prontuariofilas.funcionalidades.prontuario.consultarprontuario;

import br.edu.tde.prontuariofilas.funcionalidades.prontuario.Prontuario;
import java.time.LocalDateTime;
import java.util.List;

/** Adaptador — corpo da resposta HTTP. */
public record ConsultarProntuarioResposta(String prontuarioId, String pacienteId, List<String> alergias,
                                          List<String> medicacoes, List<ItemAtendimento> atendimentos) {

    public record ItemAtendimento(String unidadeId, String descricao, LocalDateTime data) {
    }

    static ConsultarProntuarioResposta de(Prontuario prontuario) {
        throw new UnsupportedOperationException("TODO");
    }
}
