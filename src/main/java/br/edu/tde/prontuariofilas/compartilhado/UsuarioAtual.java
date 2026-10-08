package br.edu.tde.prontuariofilas.compartilhado;

import java.util.List;
import java.util.Set;

/** Usuário autenticado (Entra ID), já traduzido para o vocabulário do domínio. */
public record UsuarioAtual(String id, Set<String> perfis, List<String> unidadeIds) {

    public boolean lotadoEm(String unidadeId) {
        return unidadeIds.contains(unidadeId);
    }
}
