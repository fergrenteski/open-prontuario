package br.edu.tde.prontuariofilas.funcionalidades.consentimento;

import java.time.LocalDateTime;

/** Entidade — autorização explícita, revogável e por unidade (RF05). */
public class Consentimento {

    private String id;
    private String pacienteId;
    private String unidadeId;
    private Boolean ativo;
    private LocalDateTime concedidoEm;
    private LocalDateTime revogadoEm;

    public static Consentimento conceder(String pacienteId, String unidadeId) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Ativo e não revogado. */
    public Boolean estaValido() {
        throw new UnsupportedOperationException("TODO");
    }

    public void revogar() {
        throw new UnsupportedOperationException("TODO");
    }
}
