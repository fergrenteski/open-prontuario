package br.edu.tde.prontuariofilas.funcionalidades.agregacao.atualizarvisaoagregada;

/** Adaptador — corpo da mensagem do Service Bus (publicada por PublicadorEventoFilaServiceBus). */
public record AtualizarVisaoAgregadaRequisicao(String evento, String registroId, String unidadeId, String status, String prioridade, String ocorridoEm) {
}
