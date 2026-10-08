package br.edu.tde.prontuariofilas.funcionalidades.fila;

/** Porta — publica mudanças de estado da fila (RF17). */
public interface PublicadorEventoFila {

    void publicar(EventoFila evento, RegistroFila registro);
}
