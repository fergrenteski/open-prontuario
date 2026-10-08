package br.edu.tde.prontuariofilas.funcionalidades.fila;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** Framework — modelo de persistência (coleção registro_fila). Mantém a Entidade livre de anotações. */
@Document("registro_fila")
record RegistroFilaDocumento(@Id String id, String pacienteId, String unidadeId, Prioridade prioridade,
                             StatusFila status, List<String> sintomas, LocalDateTime horarioAviso,
                             LocalDateTime horarioChegada, LocalDateTime horarioInicioAtendimento,
                             MotivoSaida motivoSaida) {

    static RegistroFilaDocumento de(RegistroFila r) {
        return new RegistroFilaDocumento(r.id(), r.pacienteId(), r.unidadeId(), r.prioridade(), r.status(),
                r.sintomas(), r.horarioAviso(), r.horarioChegada(), r.horarioInicioAtendimento(), r.motivoSaida());
    }

    RegistroFila paraDominio() {
        return RegistroFila.reconstituir(id, pacienteId, unidadeId, prioridade, status, sintomas, horarioAviso,
                horarioChegada, horarioInicioAtendimento, motivoSaida);
    }
}
