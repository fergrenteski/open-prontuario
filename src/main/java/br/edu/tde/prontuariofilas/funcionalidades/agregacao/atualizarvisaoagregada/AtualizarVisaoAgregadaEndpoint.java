package br.edu.tde.prontuariofilas.funcionalidades.agregacao.atualizarvisaoagregada;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.ServiceBusTopicTrigger;
import org.springframework.stereotype.Component;

/**
 * Adaptador — entrada assíncrona: Service Bus Trigger do Worker (Azure Functions).
 * Exceção lançada aqui → Service Bus reentrega; esgotado o maxDeliveryCount, a mensagem vai para a dead-letter queue.
 */
@Component
class AtualizarVisaoAgregadaEndpoint {

    private final CasoDeUso<AtualizarVisaoAgregadaComando, Void> casoDeUso;

    AtualizarVisaoAgregadaEndpoint(CasoDeUso<AtualizarVisaoAgregadaComando, Void> casoDeUso) {
        this.casoDeUso = casoDeUso;
    }

    @FunctionName("atualizarVisaoAgregada")
    public void receber(@ServiceBusTopicTrigger(name = "mensagem", topicName = "eventos-atendimento",
                                subscriptionName = "worker-visao-agregada",
                                connection = "SERVICEBUS_CONNECTION_STRING") AtualizarVisaoAgregadaRequisicao mensagem,
                        ExecutionContext contexto) {
        throw new UnsupportedOperationException("TODO");
    }
}
