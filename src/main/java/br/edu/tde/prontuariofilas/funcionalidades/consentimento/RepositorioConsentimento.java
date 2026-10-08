package br.edu.tde.prontuariofilas.funcionalidades.consentimento;

import java.util.List;
import java.util.Optional;

/** Porta — persistência de {@link Consentimento}. Usada pelas fatias do módulo e por prontuario/consultarprontuario. */
public interface RepositorioConsentimento {

    void salvar(Consentimento consentimento);

    Optional<Consentimento> buscarPorId(String consentimentoId);

    List<Consentimento> listarPorPaciente(String pacienteId);

    Optional<Consentimento> buscarAtivo(String pacienteId, String unidadeId);
}
