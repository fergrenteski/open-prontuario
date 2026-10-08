package br.edu.tde.prontuariofilas.compartilhado;

public class AcessoNegadoExcecao extends RuntimeException {

    public AcessoNegadoExcecao(String mensagem) {
        super(mensagem);
    }
}
