package br.edu.tde.prontuariofilas.funcionalidades.unidade.listarminhasunidades;

import java.util.List;

/** CasoDeUso — as unidades vêm do vínculo do usuário autenticado, nunca de parâmetro (arc42 §8). */
public record ListarMinhasUnidadesComando(List<String> unidadeIds) {
}
