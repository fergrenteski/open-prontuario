package br.edu.tde.prontuariofilas.funcionalidades.fila;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Framework — cliente do tópico eventos-atendimento. */
@Configuration
class ConfiguracaoServiceBus {

    @Bean(destroyMethod = "close")
    ServiceBusSenderClient remetenteEventosAtendimento(@Value("${azure.servicebus.connection-string}") String conexao,
                                                       @Value("${azure.servicebus.topic}") String topico) {
        return new ServiceBusClientBuilder()
                .connectionString(conexao)
                .sender()
                .topicName(topico)
                .buildClient();
    }
}
