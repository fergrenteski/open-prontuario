# Arquitetura em Fatias Verticais (Vertical Slice) + Clean Architecture

Backend do **Sistema de Prontuário e Filas UBS/UPA** (TDE 2). Fonte da verdade: `arc42_prontuario_filas.md`
(RF01–RF18, matriz Requisito × Endpoint, §5.3, §6.3 e §8).

## Regras de organização

1. **Não há pastas de camada.** O código é organizado por **funcionalidade**: `funcionalidades/<módulo>/<fatia>/`.
   Cada fatia é uma operação da matriz de rastreabilidade.
2. As 4 camadas da Clean Architecture existem como **papéis de arquivo** dentro da fatia:

| Papel (estereótipo) | Arquivo | Camada |
|---|---|---|
| `<<Adaptador>>` | `<Fatia>Endpoint`, `<Fatia>Requisicao`, `<Fatia>Resposta` (na fatia); `Repositorio*Mongo`, `Publicador*ServiceBus`, `Gateway*AzureMaps` | Interface Adapters |
| `<<CasoDeUso>>` | `<Fatia>Comando`, `<Fatia>CasoDeUso` | Use Cases |
| `<<Porta>>` | `CasoDeUso<E,S>` (entrada) e `Repositorio*`, `Publicador*`, `Gateway*` (saída) — na fatia se for exclusiva, na raiz do módulo se for compartilhada | Use Cases |
| `<<Entidade>>` | na raiz do módulo (compartilhada pelas fatias) | Entities |
| `<<Framework>>` | `ConfiguracaoSeguranca`, `ResolvedorUsuarioAtual`, `ConfiguracaoServiceBus`, `RegistroFilaDocumento`, Spring, SDKs Azure | Frameworks & Drivers |

3. **Entidades, portas e adaptadores usados por mais de uma fatia ficam soltos na raiz do módulo**
   (ex.: `fila/RegistroFila.java`, `fila/RepositorioRegistroFila.java`, `fila/RepositorioRegistroFilaMongo.java`).
   **Uma fatia nunca importa classes de outra fatia** — só da raiz do próprio módulo, da raiz de outro módulo ou de `compartilhado/`.
4. Exceções ao "5 arquivos por fatia": `RemoverDaFila` e `RevogarConsentimento` respondem `204 No Content`
   (sem `Resposta`); o Worker `AtualizarVisaoAgregada` é acionado pelo Service Bus (sem `Resposta`).

## Matriz Fatia × Papel

| Módulo | Fatia (operationId) | RF | Endpoint | CasoDeUso | Portas usadas | Entidades |
|---|---|---|---|---|---|---|
| fila | `avisarChegada` ✅ implementada | RF03, RF04, RF17 | `POST /fila/{unidadeId}/aviso-chegada` | `AvisarChegadaCasoDeUso` | `RepositorioRegistroFila`, `PublicadorEventoFila` | `RegistroFila` |
| fila | `listarFilaPorPrioridade` | RF08, RF09 | `GET /fila/{unidadeId}` | `ListarFilaPorPrioridadeCasoDeUso` | `RepositorioRegistroFila` | `RegistroFila` |
| fila | `confirmarChegada` | RF10, RF17 | `POST /fila/{registroId}/confirmar-chegada` | `ConfirmarChegadaCasoDeUso` | `RepositorioRegistroFila`, `PublicadorEventoFila` | `RegistroFila` |
| fila | `alterarPrioridade` | RF11, RF17 | `PATCH /fila/{registroId}/prioridade` | `AlterarPrioridadeCasoDeUso` | `RepositorioRegistroFila`, `PublicadorEventoFila` | `RegistroFila` |
| fila | `iniciarAtendimento` | RF13, RF17 | `POST /fila/{registroId}/iniciar-atendimento` | `IniciarAtendimentoCasoDeUso` | `RepositorioRegistroFila`, `PublicadorEventoFila` | `RegistroFila` |
| fila | `removerDaFila` | RF14, RF17 | `DELETE /fila/{registroId}?motivo=` | `RemoverDaFilaCasoDeUso` | `RepositorioRegistroFila`, `PublicadorEventoFila` | `RegistroFila` |
| unidade | `listarUnidadesProximas` | RF02 | `GET /unidades/proximas` | `ListarUnidadesProximasCasoDeUso` | `RepositorioUnidadeSaude`, `RepositorioVisaoAgregada`, `GatewayGeolocalizacao` (da fatia) | `UnidadeSaude`, `VisaoAgregada` |
| unidade | `listarMinhasUnidades` | RF07 | `GET /unidades/minhas` | `ListarMinhasUnidadesCasoDeUso` | `RepositorioUnidadeSaude` | `UnidadeSaude` |
| unidade | `consultarVisaoAgregada` | RF18 | `GET /unidades/{unidadeId}/visao-agregada` | `ConsultarVisaoAgregadaCasoDeUso` | `RepositorioVisaoAgregada` | `VisaoAgregada` |
| consentimento | `concederConsentimento` | RF05 | `POST /consentimentos` | `ConcederConsentimentoCasoDeUso` | `RepositorioConsentimento` | `Consentimento` |
| consentimento | `revogarConsentimento` | RF05 | `DELETE /consentimentos/{consentimentoId}` | `RevogarConsentimentoCasoDeUso` | `RepositorioConsentimento` | `Consentimento` |
| consentimento | `listarConsentimentos` | RF06 | `GET /consentimentos` | `ListarConsentimentosCasoDeUso` | `RepositorioConsentimento` | `Consentimento` |
| prontuario | `consultarProprioProntuario` | RF06 | `GET /prontuarios/me` | `ConsultarProprioProntuarioCasoDeUso` | `RepositorioProntuario` | `Prontuario` |
| prontuario | `consultarProntuario` | RF12, RF15 | `GET /prontuarios/{pacienteId}?unidadeId=` | `ConsultarProntuarioCasoDeUso` | `RepositorioProntuario`, `RepositorioConsentimento`, `RepositorioLogAuditoria` | `Prontuario`, `Consentimento`, `LogAuditoria` |
| auditoria | `listarAuditoriaProntuario` | RF16 | `GET /auditoria/prontuarios/{prontuarioId}` | `ListarAuditoriaProntuarioCasoDeUso` | `RepositorioLogAuditoria` | `LogAuditoria` |
| agregacao | Worker (Service Bus Trigger) | RF17, RF18 | tópico `eventos-atendimento` | `AtualizarVisaoAgregadaCasoDeUso` | `RepositorioRegistroFila`, `RepositorioVisaoAgregada` | `VisaoAgregada`, `RegistroFila` |

RF01 é delegado ao Microsoft Entra ID (`compartilhado/ConfiguracaoSeguranca`).

---

## 1. Diagramas de Classes (namespace por fatia)

### 1.1 Entidades (raiz de cada módulo)

```mermaid
classDiagram
    direction LR
    namespace fila {
        class RegistroFila {
            <<Entidade>>
            -String id
            -String pacienteId
            -String unidadeId
            -Prioridade prioridade
            -StatusFila status
            -List~String~ sintomas
            -LocalDateTime horarioAviso
            -LocalDateTime horarioChegada
            -LocalDateTime horarioInicioAtendimento
            -MotivoSaida motivoSaida
            +avisarChegada(String, String, List~String~, LocalDateTime)$ RegistroFila
            +reconstituir(...)$ RegistroFila
            +confirmarChegada()
            +alterarPrioridade(Prioridade)
            +iniciarAtendimento()
            +encerrar(MotivoSaida)
        }
        class Prioridade {
            <<enumeration>>
            EMERGENCIA
            MUITO_URGENTE
            URGENTE
            POUCO_URGENTE
            NAO_URGENTE
        }
        class StatusFila {
            <<enumeration>>
            A_CAMINHO
            AGUARDANDO
            EM_ATENDIMENTO
            FINALIZADO
        }
        class MotivoSaida {
            <<enumeration>>
            ATENDIDO
            DESISTENCIA
            ENCAMINHADO
        }
        class EventoFila {
            <<enumeration>>
            PACIENTE_A_CAMINHO
            PACIENTE_CHEGOU
            PRIORIDADE_ALTERADA
            ATENDIMENTO_INICIADO
            PACIENTE_REMOVIDO
        }
    }
    namespace unidade {
        class UnidadeSaude {
            <<Entidade>>
            +String id
            +String nome
            +TipoUnidade tipo
            +Double latitude
            +Double longitude
            +calcularDistancia(Double, Double) Double
        }
        class TipoUnidade {
            <<enumeration>>
            UBS
            UPA
        }
        class VisaoAgregada {
            <<Entidade>>
            -String unidadeId
            -Integer totalPresente
            -Integer totalACaminho
            -Integer tempoMedioEsperaMin
            -LocalDateTime atualizadoEm
            +recalcular(List~RegistroFila~)
        }
    }
    namespace consentimento {
        class Consentimento {
            <<Entidade>>
            -String id
            -String pacienteId
            -String unidadeId
            -Boolean ativo
            -LocalDateTime concedidoEm
            -LocalDateTime revogadoEm
            +conceder(String, String)$ Consentimento
            +estaValido() Boolean
            +revogar()
        }
    }
    namespace prontuario {
        class Prontuario {
            <<Entidade>>
            +String id
            +String pacienteId
            +List~String~ alergias
            +List~String~ medicacoes
            +List~Atendimento~ atendimentos
        }
        class Atendimento {
            <<Entidade>>
            +String id
            +String prontuarioId
            +String unidadeId
            +String descricao
            +LocalDateTime data
        }
    }
    namespace auditoria {
        class LogAuditoria {
            <<Entidade>>
            +String id
            +String prontuarioId
            +String usuarioId
            +String unidadeId
            +LocalDateTime acessadoEm
        }
    }

    RegistroFila "1" --> "1" Prioridade : classificado por
    RegistroFila "1" --> "1" StatusFila : possui
    RegistroFila "0..1" --> "1" MotivoSaida : encerrado por
    UnidadeSaude "1" --> "1" TipoUnidade : classificada como
    VisaoAgregada ..> RegistroFila : agrega
    Prontuario "1" *-- "0..*" Atendimento : contém
    LogAuditoria ..> Prontuario : registra acesso em
```

### 1.2 Módulo `fila` — fatias AvisarChegada (implementada) e ListarFilaPorPrioridade + raiz do módulo

```mermaid
classDiagram
    direction LR
    namespace compartilhado {
        class CasoDeUso~E,S~ {
            <<Porta>>
            +executar(E entrada) S
        }
        class UsuarioAtual {
            <<CasoDeUso>>
            +String id
            +Set~String~ perfis
            +List~String~ unidadeIds
            +lotadoEm(String) boolean
        }
    }
    namespace fila_avisarchegada {
        class AvisarChegadaEndpoint {
            <<Adaptador>>
            +avisar(String unidadeId, AvisarChegadaRequisicao, UsuarioAtual) AvisarChegadaResposta
        }
        class AvisarChegadaRequisicao {
            <<Adaptador>>
            +List~String~ sintomas
        }
        class AvisarChegadaResposta {
            <<Adaptador>>
            +String registroId
            +String unidadeId
            +StatusFila status
            +Prioridade prioridade
            +LocalDateTime horarioAviso
            +de(RegistroFila)$ AvisarChegadaResposta
        }
        class AvisarChegadaComando {
            <<CasoDeUso>>
            +String pacienteId
            +String unidadeId
            +List~String~ sintomas
        }
        class AvisarChegadaCasoDeUso {
            <<CasoDeUso>>
            +executar(AvisarChegadaComando) RegistroFila
        }
    }
    namespace fila_listarfilaporprioridade {
        class ListarFilaPorPrioridadeEndpoint {
            <<Adaptador>>
            +listar(ListarFilaPorPrioridadeRequisicao) List~ListarFilaPorPrioridadeResposta~
        }
        class ListarFilaPorPrioridadeRequisicao {
            <<Adaptador>>
            +String unidadeId
            +StatusFila status
        }
        class ListarFilaPorPrioridadeResposta {
            <<Adaptador>>
            +String registroId
            +Prioridade prioridade
            +StatusFila status
            +boolean presente
        }
        class ListarFilaPorPrioridadeComando {
            <<CasoDeUso>>
            +String unidadeId
            +StatusFila status
        }
        class ListarFilaPorPrioridadeCasoDeUso {
            <<CasoDeUso>>
            +executar(ListarFilaPorPrioridadeComando) List~RegistroFila~
        }
    }
    namespace fila {
        class RegistroFila {
            <<Entidade>>
            +avisarChegada(String, String, List~String~, LocalDateTime)$ RegistroFila
            +confirmarChegada()
            +alterarPrioridade(Prioridade)
            +iniciarAtendimento()
            +encerrar(MotivoSaida)
        }
        class RepositorioRegistroFila {
            <<Porta>>
            +salvar(RegistroFila)
            +buscarPorId(String) Optional~RegistroFila~
            +listarAtivosPorUnidade(String) List~RegistroFila~
        }
        class PublicadorEventoFila {
            <<Porta>>
            +publicar(EventoFila, RegistroFila)
        }
        class RepositorioRegistroFilaMongo {
            <<Adaptador>>
            -MongoTemplate mongo
        }
        class PublicadorEventoFilaServiceBus {
            <<Adaptador>>
            -ServiceBusSenderClient remetente
        }
        class RegistroFilaDocumento {
            <<Framework>>
            +de(RegistroFila)$ RegistroFilaDocumento
            +paraDominio() RegistroFila
        }
        class ConfiguracaoServiceBus {
            <<Framework>>
            +remetenteEventosAtendimento(String, String) ServiceBusSenderClient
        }
    }
    namespace frameworks {
        class MongoTemplate {
            <<Framework>>
        }
        class ServiceBusSenderClient {
            <<Framework>>
        }
    }

    AvisarChegadaEndpoint ..> CasoDeUso : usa
    AvisarChegadaEndpoint ..> AvisarChegadaRequisicao
    AvisarChegadaEndpoint ..> AvisarChegadaResposta
    AvisarChegadaEndpoint ..> UsuarioAtual
    AvisarChegadaCasoDeUso ..|> CasoDeUso
    AvisarChegadaCasoDeUso ..> AvisarChegadaComando
    AvisarChegadaCasoDeUso ..> RegistroFila
    AvisarChegadaCasoDeUso ..> RepositorioRegistroFila
    AvisarChegadaCasoDeUso ..> PublicadorEventoFila

    ListarFilaPorPrioridadeEndpoint ..> CasoDeUso : usa
    ListarFilaPorPrioridadeEndpoint ..> ListarFilaPorPrioridadeRequisicao
    ListarFilaPorPrioridadeEndpoint ..> ListarFilaPorPrioridadeResposta
    ListarFilaPorPrioridadeCasoDeUso ..|> CasoDeUso
    ListarFilaPorPrioridadeCasoDeUso ..> ListarFilaPorPrioridadeComando
    ListarFilaPorPrioridadeCasoDeUso ..> RepositorioRegistroFila

    RepositorioRegistroFilaMongo ..|> RepositorioRegistroFila
    PublicadorEventoFilaServiceBus ..|> PublicadorEventoFila
    RepositorioRegistroFilaMongo ..> RegistroFilaDocumento
    RepositorioRegistroFilaMongo ..> MongoTemplate
    PublicadorEventoFilaServiceBus ..> ServiceBusSenderClient
    ConfiguracaoServiceBus ..> ServiceBusSenderClient : cria
```

### 1.3 Módulo `fila` — fatias de transição de estado

Os adaptadores que implementam as portas da raiz do módulo estão no diagrama 1.2.

```mermaid
classDiagram
    direction LR
    namespace compartilhado {
        class CasoDeUso~E,S~ {
            <<Porta>>
            +executar(E entrada) S
        }
    }
    namespace fila_confirmarchegada {
        class ConfirmarChegadaEndpoint {
            <<Adaptador>>
            +confirmar(ConfirmarChegadaRequisicao) ConfirmarChegadaResposta
        }
        class ConfirmarChegadaRequisicao {
            <<Adaptador>>
            +String registroId
        }
        class ConfirmarChegadaResposta {
            <<Adaptador>>
            +String registroId
            +StatusFila status
            +LocalDateTime horarioChegada
        }
        class ConfirmarChegadaComando {
            <<CasoDeUso>>
            +String registroId
        }
        class ConfirmarChegadaCasoDeUso {
            <<CasoDeUso>>
            +executar(ConfirmarChegadaComando) RegistroFila
        }
    }
    namespace fila_alterarprioridade {
        class AlterarPrioridadeEndpoint {
            <<Adaptador>>
            +alterar(String registroId, AlterarPrioridadeRequisicao) AlterarPrioridadeResposta
        }
        class AlterarPrioridadeRequisicao {
            <<Adaptador>>
            +Prioridade prioridade
        }
        class AlterarPrioridadeResposta {
            <<Adaptador>>
            +String registroId
            +Prioridade prioridade
            +StatusFila status
        }
        class AlterarPrioridadeComando {
            <<CasoDeUso>>
            +String registroId
            +Prioridade novaPrioridade
        }
        class AlterarPrioridadeCasoDeUso {
            <<CasoDeUso>>
            +executar(AlterarPrioridadeComando) RegistroFila
        }
    }
    namespace fila_iniciaratendimento {
        class IniciarAtendimentoEndpoint {
            <<Adaptador>>
            +iniciar(IniciarAtendimentoRequisicao) IniciarAtendimentoResposta
        }
        class IniciarAtendimentoRequisicao {
            <<Adaptador>>
            +String registroId
        }
        class IniciarAtendimentoResposta {
            <<Adaptador>>
            +String registroId
            +StatusFila status
            +LocalDateTime horarioInicioAtendimento
        }
        class IniciarAtendimentoComando {
            <<CasoDeUso>>
            +String registroId
        }
        class IniciarAtendimentoCasoDeUso {
            <<CasoDeUso>>
            +executar(IniciarAtendimentoComando) RegistroFila
        }
    }
    namespace fila_removerdafila {
        class RemoverDaFilaEndpoint {
            <<Adaptador>>
            +remover(RemoverDaFilaRequisicao)
        }
        class RemoverDaFilaRequisicao {
            <<Adaptador>>
            +String registroId
            +MotivoSaida motivo
        }
        class RemoverDaFilaComando {
            <<CasoDeUso>>
            +String registroId
            +MotivoSaida motivo
        }
        class RemoverDaFilaCasoDeUso {
            <<CasoDeUso>>
            +executar(RemoverDaFilaComando) Void
        }
    }
    namespace fila {
        class RegistroFila {
            <<Entidade>>
            +confirmarChegada()
            +alterarPrioridade(Prioridade)
            +iniciarAtendimento()
            +encerrar(MotivoSaida)
        }
        class RepositorioRegistroFila {
            <<Porta>>
            +salvar(RegistroFila)
            +buscarPorId(String) Optional~RegistroFila~
        }
        class PublicadorEventoFila {
            <<Porta>>
            +publicar(EventoFila, RegistroFila)
        }
    }

    ConfirmarChegadaEndpoint ..> CasoDeUso : usa
    ConfirmarChegadaEndpoint ..> ConfirmarChegadaRequisicao
    ConfirmarChegadaEndpoint ..> ConfirmarChegadaResposta
    ConfirmarChegadaCasoDeUso ..|> CasoDeUso
    ConfirmarChegadaCasoDeUso ..> ConfirmarChegadaComando
    ConfirmarChegadaCasoDeUso ..> RegistroFila
    ConfirmarChegadaCasoDeUso ..> RepositorioRegistroFila
    ConfirmarChegadaCasoDeUso ..> PublicadorEventoFila

    AlterarPrioridadeEndpoint ..> CasoDeUso : usa
    AlterarPrioridadeEndpoint ..> AlterarPrioridadeRequisicao
    AlterarPrioridadeEndpoint ..> AlterarPrioridadeResposta
    AlterarPrioridadeCasoDeUso ..|> CasoDeUso
    AlterarPrioridadeCasoDeUso ..> AlterarPrioridadeComando
    AlterarPrioridadeCasoDeUso ..> RegistroFila
    AlterarPrioridadeCasoDeUso ..> RepositorioRegistroFila
    AlterarPrioridadeCasoDeUso ..> PublicadorEventoFila

    IniciarAtendimentoEndpoint ..> CasoDeUso : usa
    IniciarAtendimentoEndpoint ..> IniciarAtendimentoRequisicao
    IniciarAtendimentoEndpoint ..> IniciarAtendimentoResposta
    IniciarAtendimentoCasoDeUso ..|> CasoDeUso
    IniciarAtendimentoCasoDeUso ..> IniciarAtendimentoComando
    IniciarAtendimentoCasoDeUso ..> RegistroFila
    IniciarAtendimentoCasoDeUso ..> RepositorioRegistroFila
    IniciarAtendimentoCasoDeUso ..> PublicadorEventoFila

    RemoverDaFilaEndpoint ..> CasoDeUso : usa
    RemoverDaFilaEndpoint ..> RemoverDaFilaRequisicao
    RemoverDaFilaCasoDeUso ..|> CasoDeUso
    RemoverDaFilaCasoDeUso ..> RemoverDaFilaComando
    RemoverDaFilaCasoDeUso ..> RegistroFila
    RemoverDaFilaCasoDeUso ..> RepositorioRegistroFila
    RemoverDaFilaCasoDeUso ..> PublicadorEventoFila
```

### 1.4 Módulo `unidade`

```mermaid
classDiagram
    direction LR
    namespace compartilhado {
        class CasoDeUso~E,S~ {
            <<Porta>>
            +executar(E entrada) S
        }
    }
    namespace unidade_listarunidadesproximas {
        class ListarUnidadesProximasEndpoint {
            <<Adaptador>>
            +listar(ListarUnidadesProximasRequisicao) List~ListarUnidadesProximasResposta~
        }
        class ListarUnidadesProximasRequisicao {
            <<Adaptador>>
            +Double latitude
            +Double longitude
            +Double raioKm
        }
        class ListarUnidadesProximasResposta {
            <<Adaptador>>
            +String unidadeId
            +Double distanciaKm
            +Integer totalPresente
            +Integer totalACaminho
            +Integer tempoMedioEsperaMin
        }
        class ListarUnidadesProximasComando {
            <<CasoDeUso>>
            +Double latitude
            +Double longitude
            +Double raioKm
        }
        class ListarUnidadesProximasCasoDeUso {
            <<CasoDeUso>>
            +executar(ListarUnidadesProximasComando) List~UnidadeProxima~
        }
        class UnidadeProxima {
            <<CasoDeUso>>
            +UnidadeSaude unidade
            +Double distanciaKm
            +VisaoAgregada visao
        }
        class GatewayGeolocalizacao {
            <<Porta>>
            +calcularDistanciasKm(Double, Double, List~UnidadeSaude~) Map~String,Double~
        }
        class GatewayGeolocalizacaoAzureMaps {
            <<Adaptador>>
            -RestClient azureMaps
        }
    }
    namespace unidade_listarminhasunidades {
        class ListarMinhasUnidadesEndpoint {
            <<Adaptador>>
            +listar(ListarMinhasUnidadesRequisicao, UsuarioAtual) List~ListarMinhasUnidadesResposta~
        }
        class ListarMinhasUnidadesRequisicao {
            <<Adaptador>>
        }
        class ListarMinhasUnidadesResposta {
            <<Adaptador>>
            +String unidadeId
            +String nome
            +TipoUnidade tipo
        }
        class ListarMinhasUnidadesComando {
            <<CasoDeUso>>
            +List~String~ unidadeIds
        }
        class ListarMinhasUnidadesCasoDeUso {
            <<CasoDeUso>>
            +executar(ListarMinhasUnidadesComando) List~UnidadeSaude~
        }
    }
    namespace unidade_consultarvisaoagregada {
        class ConsultarVisaoAgregadaEndpoint {
            <<Adaptador>>
            +consultar(ConsultarVisaoAgregadaRequisicao) ConsultarVisaoAgregadaResposta
        }
        class ConsultarVisaoAgregadaRequisicao {
            <<Adaptador>>
            +String unidadeId
        }
        class ConsultarVisaoAgregadaResposta {
            <<Adaptador>>
            +String unidadeId
            +Integer totalPresente
            +Integer totalACaminho
            +Integer tempoMedioEsperaMin
            +LocalDateTime atualizadoEm
        }
        class ConsultarVisaoAgregadaComando {
            <<CasoDeUso>>
            +String unidadeId
        }
        class ConsultarVisaoAgregadaCasoDeUso {
            <<CasoDeUso>>
            +executar(ConsultarVisaoAgregadaComando) VisaoAgregada
        }
    }
    namespace unidade {
        class UnidadeSaude {
            <<Entidade>>
            +calcularDistancia(Double, Double) Double
        }
        class VisaoAgregada {
            <<Entidade>>
            +recalcular(List~RegistroFila~)
        }
        class RepositorioUnidadeSaude {
            <<Porta>>
            +buscarPorId(String) Optional~UnidadeSaude~
            +listarTodas() List~UnidadeSaude~
            +listarPorIds(List~String~) List~UnidadeSaude~
        }
        class RepositorioVisaoAgregada {
            <<Porta>>
            +buscarPorUnidade(String) Optional~VisaoAgregada~
            +salvar(VisaoAgregada)
        }
        class RepositorioUnidadeSaudeMongo {
            <<Adaptador>>
            -MongoTemplate mongo
        }
        class RepositorioVisaoAgregadaMongo {
            <<Adaptador>>
            -MongoTemplate mongo
        }
    }
    namespace frameworks {
        class MongoTemplate {
            <<Framework>>
        }
        class RestClient {
            <<Framework>>
        }
    }

    ListarUnidadesProximasEndpoint ..> CasoDeUso : usa
    ListarUnidadesProximasEndpoint ..> ListarUnidadesProximasRequisicao
    ListarUnidadesProximasEndpoint ..> ListarUnidadesProximasResposta
    ListarUnidadesProximasCasoDeUso ..|> CasoDeUso
    ListarUnidadesProximasCasoDeUso ..> ListarUnidadesProximasComando
    ListarUnidadesProximasCasoDeUso ..> UnidadeProxima
    ListarUnidadesProximasCasoDeUso ..> RepositorioUnidadeSaude
    ListarUnidadesProximasCasoDeUso ..> RepositorioVisaoAgregada
    ListarUnidadesProximasCasoDeUso ..> GatewayGeolocalizacao
    GatewayGeolocalizacaoAzureMaps ..|> GatewayGeolocalizacao
    GatewayGeolocalizacaoAzureMaps ..> RestClient
    UnidadeProxima ..> UnidadeSaude
    UnidadeProxima ..> VisaoAgregada

    ListarMinhasUnidadesEndpoint ..> CasoDeUso : usa
    ListarMinhasUnidadesEndpoint ..> ListarMinhasUnidadesRequisicao
    ListarMinhasUnidadesEndpoint ..> ListarMinhasUnidadesResposta
    ListarMinhasUnidadesCasoDeUso ..|> CasoDeUso
    ListarMinhasUnidadesCasoDeUso ..> ListarMinhasUnidadesComando
    ListarMinhasUnidadesCasoDeUso ..> RepositorioUnidadeSaude

    ConsultarVisaoAgregadaEndpoint ..> CasoDeUso : usa
    ConsultarVisaoAgregadaEndpoint ..> ConsultarVisaoAgregadaRequisicao
    ConsultarVisaoAgregadaEndpoint ..> ConsultarVisaoAgregadaResposta
    ConsultarVisaoAgregadaCasoDeUso ..|> CasoDeUso
    ConsultarVisaoAgregadaCasoDeUso ..> ConsultarVisaoAgregadaComando
    ConsultarVisaoAgregadaCasoDeUso ..> RepositorioVisaoAgregada

    RepositorioUnidadeSaudeMongo ..|> RepositorioUnidadeSaude
    RepositorioVisaoAgregadaMongo ..|> RepositorioVisaoAgregada
    RepositorioUnidadeSaudeMongo ..> MongoTemplate
    RepositorioVisaoAgregadaMongo ..> MongoTemplate
```

### 1.5 Módulo `consentimento`

```mermaid
classDiagram
    direction LR
    namespace compartilhado {
        class CasoDeUso~E,S~ {
            <<Porta>>
            +executar(E entrada) S
        }
    }
    namespace consentimento_concederconsentimento {
        class ConcederConsentimentoEndpoint {
            <<Adaptador>>
            +conceder(ConcederConsentimentoRequisicao, UsuarioAtual) ConcederConsentimentoResposta
        }
        class ConcederConsentimentoRequisicao {
            <<Adaptador>>
            +String unidadeId
        }
        class ConcederConsentimentoResposta {
            <<Adaptador>>
            +String consentimentoId
            +String unidadeId
            +Boolean ativo
            +LocalDateTime concedidoEm
        }
        class ConcederConsentimentoComando {
            <<CasoDeUso>>
            +String pacienteId
            +String unidadeId
        }
        class ConcederConsentimentoCasoDeUso {
            <<CasoDeUso>>
            +executar(ConcederConsentimentoComando) Consentimento
        }
    }
    namespace consentimento_revogarconsentimento {
        class RevogarConsentimentoEndpoint {
            <<Adaptador>>
            +revogar(RevogarConsentimentoRequisicao, UsuarioAtual)
        }
        class RevogarConsentimentoRequisicao {
            <<Adaptador>>
            +String consentimentoId
        }
        class RevogarConsentimentoComando {
            <<CasoDeUso>>
            +String consentimentoId
            +String pacienteId
        }
        class RevogarConsentimentoCasoDeUso {
            <<CasoDeUso>>
            +executar(RevogarConsentimentoComando) Void
        }
    }
    namespace consentimento_listarconsentimentos {
        class ListarConsentimentosEndpoint {
            <<Adaptador>>
            +listar(ListarConsentimentosRequisicao, UsuarioAtual) List~ListarConsentimentosResposta~
        }
        class ListarConsentimentosRequisicao {
            <<Adaptador>>
        }
        class ListarConsentimentosResposta {
            <<Adaptador>>
            +String consentimentoId
            +String unidadeId
            +Boolean ativo
            +LocalDateTime concedidoEm
            +LocalDateTime revogadoEm
        }
        class ListarConsentimentosComando {
            <<CasoDeUso>>
            +String pacienteId
        }
        class ListarConsentimentosCasoDeUso {
            <<CasoDeUso>>
            +executar(ListarConsentimentosComando) List~Consentimento~
        }
    }
    namespace consentimento {
        class Consentimento {
            <<Entidade>>
            +conceder(String, String)$ Consentimento
            +estaValido() Boolean
            +revogar()
        }
        class RepositorioConsentimento {
            <<Porta>>
            +salvar(Consentimento)
            +buscarPorId(String) Optional~Consentimento~
            +listarPorPaciente(String) List~Consentimento~
            +buscarAtivo(String, String) Optional~Consentimento~
        }
        class RepositorioConsentimentoMongo {
            <<Adaptador>>
            -MongoTemplate mongo
        }
    }
    namespace frameworks {
        class MongoTemplate {
            <<Framework>>
        }
    }

    ConcederConsentimentoEndpoint ..> CasoDeUso : usa
    ConcederConsentimentoEndpoint ..> ConcederConsentimentoRequisicao
    ConcederConsentimentoEndpoint ..> ConcederConsentimentoResposta
    ConcederConsentimentoCasoDeUso ..|> CasoDeUso
    ConcederConsentimentoCasoDeUso ..> ConcederConsentimentoComando
    ConcederConsentimentoCasoDeUso ..> Consentimento
    ConcederConsentimentoCasoDeUso ..> RepositorioConsentimento

    RevogarConsentimentoEndpoint ..> CasoDeUso : usa
    RevogarConsentimentoEndpoint ..> RevogarConsentimentoRequisicao
    RevogarConsentimentoCasoDeUso ..|> CasoDeUso
    RevogarConsentimentoCasoDeUso ..> RevogarConsentimentoComando
    RevogarConsentimentoCasoDeUso ..> Consentimento
    RevogarConsentimentoCasoDeUso ..> RepositorioConsentimento

    ListarConsentimentosEndpoint ..> CasoDeUso : usa
    ListarConsentimentosEndpoint ..> ListarConsentimentosRequisicao
    ListarConsentimentosEndpoint ..> ListarConsentimentosResposta
    ListarConsentimentosCasoDeUso ..|> CasoDeUso
    ListarConsentimentosCasoDeUso ..> ListarConsentimentosComando
    ListarConsentimentosCasoDeUso ..> RepositorioConsentimento

    RepositorioConsentimentoMongo ..|> RepositorioConsentimento
    RepositorioConsentimentoMongo ..> MongoTemplate
```

### 1.6 Módulos `prontuario` e `auditoria`

```mermaid
classDiagram
    direction LR
    namespace compartilhado {
        class CasoDeUso~E,S~ {
            <<Porta>>
            +executar(E entrada) S
        }
    }
    namespace prontuario_consultarproprioprontuario {
        class ConsultarProprioProntuarioEndpoint {
            <<Adaptador>>
            +consultar(ConsultarProprioProntuarioRequisicao, UsuarioAtual) ConsultarProprioProntuarioResposta
        }
        class ConsultarProprioProntuarioRequisicao {
            <<Adaptador>>
        }
        class ConsultarProprioProntuarioResposta {
            <<Adaptador>>
            +String prontuarioId
            +List~String~ alergias
            +List~String~ medicacoes
            +List~ItemAtendimento~ atendimentos
        }
        class ConsultarProprioProntuarioComando {
            <<CasoDeUso>>
            +String pacienteId
        }
        class ConsultarProprioProntuarioCasoDeUso {
            <<CasoDeUso>>
            +executar(ConsultarProprioProntuarioComando) Prontuario
        }
    }
    namespace prontuario_consultarprontuario {
        class ConsultarProntuarioEndpoint {
            <<Adaptador>>
            +consultar(ConsultarProntuarioRequisicao, UsuarioAtual) ConsultarProntuarioResposta
        }
        class ConsultarProntuarioRequisicao {
            <<Adaptador>>
            +String pacienteId
            +String unidadeId
        }
        class ConsultarProntuarioResposta {
            <<Adaptador>>
            +String prontuarioId
            +String pacienteId
            +List~String~ alergias
            +List~String~ medicacoes
            +List~ItemAtendimento~ atendimentos
        }
        class ConsultarProntuarioComando {
            <<CasoDeUso>>
            +String pacienteId
            +String unidadeId
            +UsuarioAtual usuario
        }
        class ConsultarProntuarioCasoDeUso {
            <<CasoDeUso>>
            +executar(ConsultarProntuarioComando) Prontuario
        }
    }
    namespace auditoria_listarauditoriaprontuario {
        class ListarAuditoriaProntuarioEndpoint {
            <<Adaptador>>
            +listar(ListarAuditoriaProntuarioRequisicao) List~ListarAuditoriaProntuarioResposta~
        }
        class ListarAuditoriaProntuarioRequisicao {
            <<Adaptador>>
            +String prontuarioId
        }
        class ListarAuditoriaProntuarioResposta {
            <<Adaptador>>
            +String usuarioId
            +String unidadeId
            +LocalDateTime acessadoEm
        }
        class ListarAuditoriaProntuarioComando {
            <<CasoDeUso>>
            +String prontuarioId
        }
        class ListarAuditoriaProntuarioCasoDeUso {
            <<CasoDeUso>>
            +executar(ListarAuditoriaProntuarioComando) List~LogAuditoria~
        }
    }
    namespace raizes_dos_modulos {
        class Prontuario {
            <<Entidade>>
        }
        class RepositorioProntuario {
            <<Porta>>
            +buscarPorPaciente(String) Optional~Prontuario~
        }
        class RepositorioProntuarioMongo {
            <<Adaptador>>
        }
        class RepositorioConsentimento {
            <<Porta>>
            +buscarAtivo(String, String) Optional~Consentimento~
        }
        class LogAuditoria {
            <<Entidade>>
        }
        class RepositorioLogAuditoria {
            <<Porta>>
            +registrar(LogAuditoria)
            +listarPorProntuario(String) List~LogAuditoria~
        }
        class RepositorioLogAuditoriaMongo {
            <<Adaptador>>
        }
    }
    namespace frameworks {
        class MongoTemplate {
            <<Framework>>
        }
    }

    ConsultarProprioProntuarioEndpoint ..> CasoDeUso : usa
    ConsultarProprioProntuarioEndpoint ..> ConsultarProprioProntuarioRequisicao
    ConsultarProprioProntuarioEndpoint ..> ConsultarProprioProntuarioResposta
    ConsultarProprioProntuarioCasoDeUso ..|> CasoDeUso
    ConsultarProprioProntuarioCasoDeUso ..> ConsultarProprioProntuarioComando
    ConsultarProprioProntuarioCasoDeUso ..> RepositorioProntuario

    ConsultarProntuarioEndpoint ..> CasoDeUso : usa
    ConsultarProntuarioEndpoint ..> ConsultarProntuarioRequisicao
    ConsultarProntuarioEndpoint ..> ConsultarProntuarioResposta
    ConsultarProntuarioCasoDeUso ..|> CasoDeUso
    ConsultarProntuarioCasoDeUso ..> ConsultarProntuarioComando
    ConsultarProntuarioCasoDeUso ..> Prontuario
    ConsultarProntuarioCasoDeUso ..> RepositorioProntuario
    ConsultarProntuarioCasoDeUso ..> RepositorioConsentimento : valida consentimento
    ConsultarProntuarioCasoDeUso ..> RepositorioLogAuditoria : registra acesso

    ListarAuditoriaProntuarioEndpoint ..> CasoDeUso : usa
    ListarAuditoriaProntuarioEndpoint ..> ListarAuditoriaProntuarioRequisicao
    ListarAuditoriaProntuarioEndpoint ..> ListarAuditoriaProntuarioResposta
    ListarAuditoriaProntuarioCasoDeUso ..|> CasoDeUso
    ListarAuditoriaProntuarioCasoDeUso ..> ListarAuditoriaProntuarioComando
    ListarAuditoriaProntuarioCasoDeUso ..> RepositorioLogAuditoria

    RepositorioProntuarioMongo ..|> RepositorioProntuario
    RepositorioLogAuditoriaMongo ..|> RepositorioLogAuditoria
    RepositorioProntuarioMongo ..> MongoTemplate
    RepositorioLogAuditoriaMongo ..> MongoTemplate
    LogAuditoria ..> Prontuario
```

### 1.7 Módulo `agregacao` (Worker)

```mermaid
classDiagram
    direction LR
    namespace compartilhado {
        class CasoDeUso~E,S~ {
            <<Porta>>
            +executar(E entrada) S
        }
    }
    namespace agregacao_atualizarvisaoagregada {
        class AtualizarVisaoAgregadaEndpoint {
            <<Adaptador>>
            +receber(AtualizarVisaoAgregadaRequisicao, ExecutionContext)
        }
        class AtualizarVisaoAgregadaRequisicao {
            <<Adaptador>>
            +String evento
            +String registroId
            +String unidadeId
            +String status
            +String prioridade
            +String ocorridoEm
        }
        class AtualizarVisaoAgregadaComando {
            <<CasoDeUso>>
            +String unidadeId
            +EventoFila evento
        }
        class AtualizarVisaoAgregadaCasoDeUso {
            <<CasoDeUso>>
            +executar(AtualizarVisaoAgregadaComando) Void
        }
    }
    namespace raizes_dos_modulos {
        class RegistroFila {
            <<Entidade>>
        }
        class RepositorioRegistroFila {
            <<Porta>>
            +listarAtivosPorUnidade(String) List~RegistroFila~
        }
        class VisaoAgregada {
            <<Entidade>>
            +recalcular(List~RegistroFila~)
        }
        class RepositorioVisaoAgregada {
            <<Porta>>
            +buscarPorUnidade(String) Optional~VisaoAgregada~
            +salvar(VisaoAgregada)
        }
    }
    namespace frameworks {
        class ServiceBusTopicTrigger {
            <<Framework>>
        }
    }

    AtualizarVisaoAgregadaEndpoint ..> CasoDeUso : usa
    AtualizarVisaoAgregadaEndpoint ..> AtualizarVisaoAgregadaRequisicao
    AtualizarVisaoAgregadaEndpoint ..> ServiceBusTopicTrigger
    AtualizarVisaoAgregadaCasoDeUso ..|> CasoDeUso
    AtualizarVisaoAgregadaCasoDeUso ..> AtualizarVisaoAgregadaComando
    AtualizarVisaoAgregadaCasoDeUso ..> VisaoAgregada
    AtualizarVisaoAgregadaCasoDeUso ..> RepositorioRegistroFila
    AtualizarVisaoAgregadaCasoDeUso ..> RepositorioVisaoAgregada
    VisaoAgregada ..> RegistroFila : agrega
```

---

## 2. Diagramas de Componentes — cada fatia é uma coluna

Cada coluna, de cima para baixo: **Endpoint** (Adaptador) → **«interface» CasoDeUso‹Comando, Saída›** (Porta de entrada)
→ **CasoDeUso** → portas da raiz do módulo. Embaixo ficam a raiz do módulo (entidades, portas e adaptadores
compartilhados) e os sistemas externos. Notação: `requer` = consome a interface; `provê` = implementa a interface.

### 2.1 Módulo `fila`

```mermaid
flowchart TB
    Web["Painel Web (React JS)"]:::externo
    Mobile["App Mobile (React Native)"]:::externo
    Spring["Spring Web MVC + Spring Security<br/>(ConfiguracaoSeguranca → Microsoft Entra ID)"]:::framework
    Web -->|HTTPS/JSON| Spring
    Mobile -->|HTTPS/JSON| Spring

    subgraph Fila["módulo fila"]
        direction LR
        subgraph S1["«fatia» AvisarChegada"]
            direction TB
            E1["AvisarChegadaEndpoint<br/>Requisicao · Resposta"]:::adaptador
            I1{{"«interface» CasoDeUso‹AvisarChegadaComando, RegistroFila›"}}:::porta
            C1["AvisarChegadaCasoDeUso<br/>Comando"]:::casodeuso
            E1 -.->|requer| I1
            I1 <-.-|provê| C1
        end
        subgraph S2["«fatia» ListarFilaPorPrioridade"]
            direction TB
            E2["ListarFilaPorPrioridadeEndpoint<br/>Requisicao · Resposta"]:::adaptador
            I2{{"«interface» CasoDeUso‹ListarFilaPorPrioridadeComando, List‹RegistroFila››"}}:::porta
            C2["ListarFilaPorPrioridadeCasoDeUso<br/>Comando"]:::casodeuso
            E2 -.->|requer| I2
            I2 <-.-|provê| C2
        end
        subgraph S3["«fatia» ConfirmarChegada"]
            direction TB
            E3["ConfirmarChegadaEndpoint<br/>Requisicao · Resposta"]:::adaptador
            I3{{"«interface» CasoDeUso‹ConfirmarChegadaComando, RegistroFila›"}}:::porta
            C3["ConfirmarChegadaCasoDeUso<br/>Comando"]:::casodeuso
            E3 -.->|requer| I3
            I3 <-.-|provê| C3
        end
        subgraph S4["«fatia» AlterarPrioridade"]
            direction TB
            E4["AlterarPrioridadeEndpoint<br/>Requisicao · Resposta"]:::adaptador
            I4{{"«interface» CasoDeUso‹AlterarPrioridadeComando, RegistroFila›"}}:::porta
            C4["AlterarPrioridadeCasoDeUso<br/>Comando"]:::casodeuso
            E4 -.->|requer| I4
            I4 <-.-|provê| C4
        end
        subgraph S5["«fatia» IniciarAtendimento"]
            direction TB
            E5["IniciarAtendimentoEndpoint<br/>Requisicao · Resposta"]:::adaptador
            I5{{"«interface» CasoDeUso‹IniciarAtendimentoComando, RegistroFila›"}}:::porta
            C5["IniciarAtendimentoCasoDeUso<br/>Comando"]:::casodeuso
            E5 -.->|requer| I5
            I5 <-.-|provê| C5
        end
        subgraph S6["«fatia» RemoverDaFila"]
            direction TB
            E6["RemoverDaFilaEndpoint<br/>Requisicao"]:::adaptador
            I6{{"«interface» CasoDeUso‹RemoverDaFilaComando, Void›"}}:::porta
            C6["RemoverDaFilaCasoDeUso<br/>Comando"]:::casodeuso
            E6 -.->|requer| I6
            I6 <-.-|provê| C6
        end
    end
    Spring --> E1 & E2 & E3 & E4 & E5 & E6

    subgraph Raiz["raiz do módulo fila (compartilhada pelas fatias)"]
        direction LR
        RF["RegistroFila"]:::entidade
        PRepo{{"«interface» RepositorioRegistroFila"}}:::porta
        PPub{{"«interface» PublicadorEventoFila"}}:::porta
        MRepo["RepositorioRegistroFilaMongo<br/>RegistroFilaDocumento"]:::adaptador
        SBPub["PublicadorEventoFilaServiceBus<br/>ConfiguracaoServiceBus"]:::adaptador
        PRepo <-.-|provê| MRepo
        PPub <-.-|provê| SBPub
    end
    C1 & C3 & C4 & C5 & C6 --> RF
    C1 & C2 & C3 & C4 & C5 & C6 -.->|requer| PRepo
    C1 & C3 & C4 & C5 & C6 -.->|requer| PPub

    Cosmos[("Azure Cosmos DB — API MongoDB")]:::externo
    Bus[["Azure Service Bus — tópico eventos-atendimento + DLQ"]]:::externo
    Worker["Worker — agregacao/atualizarvisaoagregada (Azure Functions)"]:::externo
    MRepo -->|Mongo Wire| Cosmos
    SBPub -->|AMQP| Bus
    Bus -->|Service Bus Trigger| Worker

    subgraph Legenda["legenda"]
        direction LR
        L1["«Entidade»"]:::entidade
        L2["«CasoDeUso»"]:::casodeuso
        L3{{"«Porta»"}}:::porta
        L4["«Adaptador»"]:::adaptador
        L5["«Framework»"]:::framework
    end

    classDef entidade fill:#fff4c2,stroke:#b38f00,color:#000
    classDef casodeuso fill:#ffd6d6,stroke:#b30000,color:#000
    classDef porta fill:#f5f5f5,stroke:#333,color:#000
    classDef adaptador fill:#d6f5d6,stroke:#2d862d,color:#000
    classDef framework fill:#d6e6ff,stroke:#1a53ff,color:#000
    classDef externo fill:#ffffff,stroke:#666,stroke-dasharray:4 3,color:#000
```

### 2.2 Módulos `unidade` e `agregacao` (Worker)

```mermaid
flowchart TB
    Web["Painel Web (React JS)"]:::externo
    Mobile["App Mobile (React Native)"]:::externo
    Spring["Spring Web MVC + Spring Security<br/>(ConfiguracaoSeguranca → Microsoft Entra ID)"]:::framework
    Bus[["Azure Service Bus — tópico eventos-atendimento + DLQ"]]:::externo
    Func["Azure Functions — ServiceBusTopicTrigger"]:::framework
    Web -->|HTTPS/JSON| Spring
    Mobile -->|HTTPS/JSON| Spring
    Bus -->|AMQP| Func

    subgraph Unidade["módulo unidade"]
        direction LR
        subgraph S1["«fatia» ListarUnidadesProximas"]
            direction TB
            E1["ListarUnidadesProximasEndpoint<br/>Requisicao · Resposta"]:::adaptador
            I1{{"«interface» CasoDeUso‹ListarUnidadesProximasComando, List‹UnidadeProxima››"}}:::porta
            C1["ListarUnidadesProximasCasoDeUso<br/>Comando · UnidadeProxima"]:::casodeuso
            G1{{"«interface» GatewayGeolocalizacao"}}:::porta
            A1["GatewayGeolocalizacaoAzureMaps"]:::adaptador
            E1 -.->|requer| I1
            I1 <-.-|provê| C1
            C1 -.->|requer| G1
            G1 <-.-|provê| A1
        end
        subgraph S2["«fatia» ListarMinhasUnidades"]
            direction TB
            E2["ListarMinhasUnidadesEndpoint<br/>Requisicao · Resposta"]:::adaptador
            I2{{"«interface» CasoDeUso‹ListarMinhasUnidadesComando, List‹UnidadeSaude››"}}:::porta
            C2["ListarMinhasUnidadesCasoDeUso<br/>Comando"]:::casodeuso
            E2 -.->|requer| I2
            I2 <-.-|provê| C2
        end
        subgraph S3["«fatia» ConsultarVisaoAgregada"]
            direction TB
            E3["ConsultarVisaoAgregadaEndpoint<br/>Requisicao · Resposta"]:::adaptador
            I3{{"«interface» CasoDeUso‹ConsultarVisaoAgregadaComando, VisaoAgregada›"}}:::porta
            C3["ConsultarVisaoAgregadaCasoDeUso<br/>Comando"]:::casodeuso
            E3 -.->|requer| I3
            I3 <-.-|provê| C3
        end
    end
    subgraph Agregacao["módulo agregacao"]
        subgraph S4["«fatia» AtualizarVisaoAgregada"]
            direction TB
            E4["AtualizarVisaoAgregadaEndpoint<br/>Requisicao"]:::adaptador
            I4{{"«interface» CasoDeUso‹AtualizarVisaoAgregadaComando, Void›"}}:::porta
            C4["AtualizarVisaoAgregadaCasoDeUso<br/>Comando"]:::casodeuso
            E4 -.->|requer| I4
            I4 <-.-|provê| C4
        end
    end
    Spring --> E1 & E2 & E3
    Func --> E4

    subgraph Raiz["raiz do módulo unidade (+ porta da raiz do módulo fila)"]
        direction LR
        US["UnidadeSaude"]:::entidade
        VA["VisaoAgregada"]:::entidade
        PUni{{"«interface» RepositorioUnidadeSaude"}}:::porta
        PVis{{"«interface» RepositorioVisaoAgregada"}}:::porta
        PRF{{"«interface» RepositorioRegistroFila (fila)"}}:::porta
        MUni["RepositorioUnidadeSaudeMongo"]:::adaptador
        MVis["RepositorioVisaoAgregadaMongo"]:::adaptador
        PUni <-.-|provê| MUni
        PVis <-.-|provê| MVis
    end
    C1 --> US & VA
    C2 --> US
    C3 --> VA
    C4 --> VA
    C1 -.->|requer| PUni & PVis
    C2 -.->|requer| PUni
    C3 -.->|requer| PVis
    C4 -.->|requer| PVis & PRF

    Cosmos[("Azure Cosmos DB — API MongoDB")]:::externo
    Maps["Azure Maps"]:::externo
    MUni -->|Mongo Wire| Cosmos
    MVis -->|Mongo Wire| Cosmos
    A1 -->|REST/HTTPS| Maps

    classDef entidade fill:#fff4c2,stroke:#b38f00,color:#000
    classDef casodeuso fill:#ffd6d6,stroke:#b30000,color:#000
    classDef porta fill:#f5f5f5,stroke:#333,color:#000
    classDef adaptador fill:#d6f5d6,stroke:#2d862d,color:#000
    classDef framework fill:#d6e6ff,stroke:#1a53ff,color:#000
    classDef externo fill:#ffffff,stroke:#666,stroke-dasharray:4 3,color:#000
```

### 2.3 Módulo `consentimento`

```mermaid
flowchart TB
    Mobile["App Mobile (React Native)"]:::externo
    Spring["Spring Web MVC + Spring Security<br/>(ConfiguracaoSeguranca → Microsoft Entra ID)"]:::framework
    Mobile -->|HTTPS/JSON| Spring

    subgraph Consent["módulo consentimento"]
        direction LR
        subgraph S1["«fatia» ConcederConsentimento"]
            direction TB
            E1["ConcederConsentimentoEndpoint<br/>Requisicao · Resposta"]:::adaptador
            I1{{"«interface» CasoDeUso‹ConcederConsentimentoComando, Consentimento›"}}:::porta
            C1["ConcederConsentimentoCasoDeUso<br/>Comando"]:::casodeuso
            E1 -.->|requer| I1
            I1 <-.-|provê| C1
        end
        subgraph S2["«fatia» RevogarConsentimento"]
            direction TB
            E2["RevogarConsentimentoEndpoint<br/>Requisicao"]:::adaptador
            I2{{"«interface» CasoDeUso‹RevogarConsentimentoComando, Void›"}}:::porta
            C2["RevogarConsentimentoCasoDeUso<br/>Comando"]:::casodeuso
            E2 -.->|requer| I2
            I2 <-.-|provê| C2
        end
        subgraph S3["«fatia» ListarConsentimentos"]
            direction TB
            E3["ListarConsentimentosEndpoint<br/>Requisicao · Resposta"]:::adaptador
            I3{{"«interface» CasoDeUso‹ListarConsentimentosComando, List‹Consentimento››"}}:::porta
            C3["ListarConsentimentosCasoDeUso<br/>Comando"]:::casodeuso
            E3 -.->|requer| I3
            I3 <-.-|provê| C3
        end
    end
    Spring --> E1 & E2 & E3

    subgraph Raiz["raiz do módulo consentimento"]
        direction LR
        CO["Consentimento"]:::entidade
        PCon{{"«interface» RepositorioConsentimento"}}:::porta
        MCon["RepositorioConsentimentoMongo"]:::adaptador
        PCon <-.-|provê| MCon
    end
    C1 & C2 --> CO
    C1 & C2 & C3 -.->|requer| PCon

    Cosmos[("Azure Cosmos DB — API MongoDB")]:::externo
    MCon -->|Mongo Wire| Cosmos

    classDef entidade fill:#fff4c2,stroke:#b38f00,color:#000
    classDef casodeuso fill:#ffd6d6,stroke:#b30000,color:#000
    classDef porta fill:#f5f5f5,stroke:#333,color:#000
    classDef adaptador fill:#d6f5d6,stroke:#2d862d,color:#000
    classDef framework fill:#d6e6ff,stroke:#1a53ff,color:#000
    classDef externo fill:#ffffff,stroke:#666,stroke-dasharray:4 3,color:#000
```

### 2.4 Módulos `prontuario` e `auditoria`

```mermaid
flowchart TB
    Web["Painel Web (React JS)"]:::externo
    Mobile["App Mobile (React Native)"]:::externo
    Spring["Spring Web MVC + Spring Security<br/>(ConfiguracaoSeguranca → Microsoft Entra ID)"]:::framework
    Web -->|HTTPS/JSON| Spring
    Mobile -->|HTTPS/JSON| Spring

    subgraph Modulos["módulos prontuario e auditoria"]
        direction LR
        subgraph S1["«fatia» ConsultarProprioProntuario"]
            direction TB
            E1["ConsultarProprioProntuarioEndpoint<br/>Requisicao · Resposta"]:::adaptador
            I1{{"«interface» CasoDeUso‹ConsultarProprioProntuarioComando, Prontuario›"}}:::porta
            C1["ConsultarProprioProntuarioCasoDeUso<br/>Comando"]:::casodeuso
            E1 -.->|requer| I1
            I1 <-.-|provê| C1
        end
        subgraph S2["«fatia» ConsultarProntuario"]
            direction TB
            E2["ConsultarProntuarioEndpoint<br/>Requisicao · Resposta"]:::adaptador
            I2{{"«interface» CasoDeUso‹ConsultarProntuarioComando, Prontuario›"}}:::porta
            C2["ConsultarProntuarioCasoDeUso<br/>Comando"]:::casodeuso
            E2 -.->|requer| I2
            I2 <-.-|provê| C2
        end
        subgraph S3["«fatia» ListarAuditoriaProntuario"]
            direction TB
            E3["ListarAuditoriaProntuarioEndpoint<br/>Requisicao · Resposta"]:::adaptador
            I3{{"«interface» CasoDeUso‹ListarAuditoriaProntuarioComando, List‹LogAuditoria››"}}:::porta
            C3["ListarAuditoriaProntuarioCasoDeUso<br/>Comando"]:::casodeuso
            E3 -.->|requer| I3
            I3 <-.-|provê| C3
        end
    end
    Spring --> E1 & E2 & E3

    subgraph Raiz["raízes dos módulos prontuario, auditoria e consentimento"]
        direction LR
        PR["Prontuario · Atendimento"]:::entidade
        LA["LogAuditoria"]:::entidade
        PPro{{"«interface» RepositorioProntuario"}}:::porta
        PAud{{"«interface» RepositorioLogAuditoria"}}:::porta
        PCon{{"«interface» RepositorioConsentimento"}}:::porta
        MPro["RepositorioProntuarioMongo"]:::adaptador
        MAud["RepositorioLogAuditoriaMongo"]:::adaptador
        MCon["RepositorioConsentimentoMongo"]:::adaptador
        PPro <-.-|provê| MPro
        PAud <-.-|provê| MAud
        PCon <-.-|provê| MCon
    end
    C2 --> PR & LA
    C1 -.->|requer| PPro
    C2 -.->|requer| PPro & PCon & PAud
    C3 -.->|requer| PAud

    Cosmos[("Azure Cosmos DB — API MongoDB")]:::externo
    MPro & MAud & MCon -->|Mongo Wire| Cosmos

    classDef entidade fill:#fff4c2,stroke:#b38f00,color:#000
    classDef casodeuso fill:#ffd6d6,stroke:#b30000,color:#000
    classDef porta fill:#f5f5f5,stroke:#333,color:#000
    classDef adaptador fill:#d6f5d6,stroke:#2d862d,color:#000
    classDef framework fill:#d6e6ff,stroke:#1a53ff,color:#000
    classDef externo fill:#ffffff,stroke:#666,stroke-dasharray:4 3,color:#000
```

---

## 3. Fluxo da fatia de exemplo (AvisarChegada)

```mermaid
sequenceDiagram
    participant App as App Mobile
    participant EP as AvisarChegadaEndpoint «Adaptador»
    participant CU as AvisarChegadaCasoDeUso «CasoDeUso»
    participant RF as RegistroFila «Entidade»
    participant R as RepositorioRegistroFila «Porta»
    participant M as RepositorioRegistroFilaMongo «Adaptador»
    participant P as PublicadorEventoFila «Porta»
    participant SB as PublicadorEventoFilaServiceBus «Adaptador»

    App->>EP: POST /fila/{unidadeId}/aviso-chegada {sintomas}
    EP->>CU: executar(AvisarChegadaComando)
    CU->>RF: avisarChegada(pacienteId, unidadeId, sintomas, agora)
    RF-->>CU: RegistroFila (A_CAMINHO, NAO_URGENTE)
    CU->>R: salvar(registro)
    R->>M: (implementação)
    M->>M: MongoTemplate.save(RegistroFilaDocumento)
    CU->>P: publicar(PACIENTE_A_CAMINHO, registro)
    P->>SB: (implementação)
    SB->>SB: ServiceBusSenderClient.sendMessage(...)
    CU-->>EP: RegistroFila
    EP-->>App: 201 Created AvisarChegadaResposta
```

## Imagens

```bash
npx @mermaid-js/mermaid-cli -i docs/arquitetura-vertical-slice.md -o docs/img/arquitetura.png --size 4000
```

Um PNG por bloco, na ordem do documento: `arquitetura-1.png` (entidades), `-2` a `-7` (classes por fatia),
`-8` a `-11` (componentes) e `-12` (sequência).
