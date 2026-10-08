package br.edu.tde.prontuariofilas.funcionalidades.prontuario;

import java.util.List;

/** Entidade — histórico clínico do paciente. */
public record Prontuario(String id, String pacienteId, List<String> alergias, List<String> medicacoes,
                         List<Atendimento> atendimentos) {
}
