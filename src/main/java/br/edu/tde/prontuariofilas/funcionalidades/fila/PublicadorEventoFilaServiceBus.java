package br.edu.tde.prontuariofilas.funcionalidades.fila;

import com.azure.core.util.BinaryData;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import java.time.Instant;
import java.util.Map;
import org.springframework.stereotype.Component;

/** Adaptador — implementa {@link PublicadorEventoFila} com Azure Service Bus (AMQP). */
@Component
class PublicadorEventoFilaServiceBus implements PublicadorEventoFila {

    private final ServiceBusSenderClient remetente;

    PublicadorEventoFilaServiceBus(ServiceBusSenderClient remetente) {
        this.remetente = remetente;
    }

    @Override
    public void publicar(EventoFila evento, RegistroFila registro) {
        Map<String, String> carga = Map.of(
                "evento", evento.name(),
                "registroId", registro.id(),
                "unidadeId", registro.unidadeId(),
                "status", registro.status().name(),
                "prioridade", registro.prioridade().name(),
                "ocorridoEm", Instant.now().toString());
        ServiceBusMessage mensagem = new ServiceBusMessage(BinaryData.fromObject(carga))
                .setSubject(evento.name())
                .setContentType("application/json");
        remetente.sendMessage(mensagem);
    }
}
