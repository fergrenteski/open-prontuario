package br.edu.tde.prontuariofilas.funcionalidades.fila;

/** Um evento por transição da máquina de estados (arc42 §6.3 e §8). */
public enum EventoFila {
    PACIENTE_A_CAMINHO,
    PACIENTE_CHEGOU,
    PRIORIDADE_ALTERADA,
    ATENDIMENTO_INICIADO,
    PACIENTE_REMOVIDO
}
