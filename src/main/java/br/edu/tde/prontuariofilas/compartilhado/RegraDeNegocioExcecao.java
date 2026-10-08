package br.edu.tde.prontuariofilas.compartilhado;

public class RegraDeNegocioExcecao extends RuntimeException {

    public RegraDeNegocioExcecao(String mensagem) {
        super(mensagem);
    }
}
