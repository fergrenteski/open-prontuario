package br.edu.tde.prontuariofilas.funcionalidades.prontuario;

import java.util.Optional;

/** Porta — leitura de prontuários (ConsultarProprioProntuario, ConsultarProntuario). */
public interface RepositorioProntuario {

    Optional<Prontuario> buscarPorPaciente(String pacienteId);
}
