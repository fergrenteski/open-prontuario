package br.edu.tde.prontuariofilas.funcionalidades.prontuario.consultarprontuario;

import br.edu.tde.prontuariofilas.compartilhado.UsuarioAtual;

/** CasoDeUso — entrada do caso de uso. */
public record ConsultarProntuarioComando(String pacienteId, String unidadeId, UsuarioAtual usuario) {
}
