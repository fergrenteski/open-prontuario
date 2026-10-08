package br.edu.tde.prontuariofilas.funcionalidades.fila.avisarchegada;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/** Adaptador — corpo da requisição HTTP. */
public record AvisarChegadaRequisicao(@NotEmpty List<@NotBlank String> sintomas) {
}
