package br.edu.tde.prontuariofilas.funcionalidades.fila;

import java.util.List;
import java.util.Optional;

/** Porta — persistência de {@link RegistroFila}. Usada por todas as fatias do módulo e pelo Worker. */
public interface RepositorioRegistroFila {

    void salvar(RegistroFila registro);

    Optional<RegistroFila> buscarPorId(String registroId);

    /** Registros não FINALIZADOS da unidade (A_CAMINHO, AGUARDANDO, EM_ATENDIMENTO). */
    List<RegistroFila> listarAtivosPorUnidade(String unidadeId);
}
