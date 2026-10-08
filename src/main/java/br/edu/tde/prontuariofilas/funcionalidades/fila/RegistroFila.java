package br.edu.tde.prontuariofilas.funcionalidades.fila;

import br.edu.tde.prontuariofilas.compartilhado.RegraDeNegocioExcecao;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Entidade — registro de um paciente na fila de uma unidade. Usada por todas as fatias do módulo.
 * Java puro: sem Spring, sem Mongo. Concentra a máquina de estados (arc42 §6.3).
 */
public class RegistroFila {

    private final String id;
    private final String pacienteId;
    private final String unidadeId;
    private Prioridade prioridade;
    private StatusFila status;
    private final List<String> sintomas;
    private final LocalDateTime horarioAviso;
    private LocalDateTime horarioChegada;
    private LocalDateTime horarioInicioAtendimento;
    private MotivoSaida motivoSaida;

    private RegistroFila(String id, String pacienteId, String unidadeId, Prioridade prioridade, StatusFila status,
                         List<String> sintomas, LocalDateTime horarioAviso, LocalDateTime horarioChegada,
                         LocalDateTime horarioInicioAtendimento, MotivoSaida motivoSaida) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.unidadeId = unidadeId;
        this.prioridade = prioridade;
        this.status = status;
        this.sintomas = sintomas;
        this.horarioAviso = horarioAviso;
        this.horarioChegada = horarioChegada;
        this.horarioInicioAtendimento = horarioInicioAtendimento;
        this.motivoSaida = motivoSaida;
    }

    /** RF03/RF04 — [*] → A_CAMINHO. Prioridade inicial NAO_URGENTE até a triagem (RF11). */
    public static RegistroFila avisarChegada(String pacienteId, String unidadeId, List<String> sintomas,
                                             LocalDateTime agora) {
        if (sintomas == null || sintomas.isEmpty()) {
            throw new RegraDeNegocioExcecao("Informe ao menos um sintoma");
        }
        return new RegistroFila(UUID.randomUUID().toString(), pacienteId, unidadeId, Prioridade.NAO_URGENTE,
                StatusFila.A_CAMINHO, List.copyOf(sintomas), agora, null, null, null);
    }

    /** Reidrata a entidade a partir da persistência (usado pelo adaptador Mongo). */
    public static RegistroFila reconstituir(String id, String pacienteId, String unidadeId, Prioridade prioridade,
                                            StatusFila status, List<String> sintomas, LocalDateTime horarioAviso,
                                            LocalDateTime horarioChegada, LocalDateTime horarioInicioAtendimento,
                                            MotivoSaida motivoSaida) {
        return new RegistroFila(id, pacienteId, unidadeId, prioridade, status, sintomas, horarioAviso,
                horarioChegada, horarioInicioAtendimento, motivoSaida);
    }

    /** RF10 — A_CAMINHO → AGUARDANDO. */
    public void confirmarChegada() {
        throw new UnsupportedOperationException("TODO");
    }

    /** RF11 — permitido enquanto não FINALIZADO. */
    public void alterarPrioridade(Prioridade novaPrioridade) {
        throw new UnsupportedOperationException("TODO");
    }

    /** RF13 — AGUARDANDO → EM_ATENDIMENTO. */
    public void iniciarAtendimento() {
        throw new UnsupportedOperationException("TODO");
    }

    /** RF14 — A_CAMINHO | AGUARDANDO | EM_ATENDIMENTO → FINALIZADO. */
    public void encerrar(MotivoSaida motivo) {
        throw new UnsupportedOperationException("TODO");
    }

    public String id() { return id; }
    public String pacienteId() { return pacienteId; }
    public String unidadeId() { return unidadeId; }
    public Prioridade prioridade() { return prioridade; }
    public StatusFila status() { return status; }
    public List<String> sintomas() { return sintomas; }
    public LocalDateTime horarioAviso() { return horarioAviso; }
    public LocalDateTime horarioChegada() { return horarioChegada; }
    public LocalDateTime horarioInicioAtendimento() { return horarioInicioAtendimento; }
    public MotivoSaida motivoSaida() { return motivoSaida; }
}
