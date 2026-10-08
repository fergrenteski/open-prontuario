package br.edu.tde.prontuariofilas.compartilhado;

/** Porta de entrada de toda fatia: um caso de uso, uma entrada, uma saída. */
public interface CasoDeUso<E, S> {

    S executar(E entrada);
}
