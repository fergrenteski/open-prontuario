package br.edu.tde.prontuariofilas.funcionalidades.unidade.listarunidadesproximas;

import jakarta.validation.constraints.NotNull;

/** Adaptador — parâmetros de consulta (?latitude=&longitude=&raioKm=). */
public record ListarUnidadesProximasRequisicao(@NotNull Double latitude, @NotNull Double longitude, Double raioKm) {
}
