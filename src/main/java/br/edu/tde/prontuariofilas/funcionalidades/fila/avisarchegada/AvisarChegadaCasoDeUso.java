package br.edu.tde.prontuariofilas.funcionalidades.fila.avisarchegada;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.fila.EventoFila;
import br.edu.tde.prontuariofilas.funcionalidades.fila.PublicadorEventoFila;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RegistroFila;
import br.edu.tde.prontuariofilas.funcionalidades.fila.RepositorioRegistroFila;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

/** CasoDeUso — RF03, RF04, RF17. Depende só de portas (DIP). */
@Service
class AvisarChegadaCasoDeUso implements CasoDeUso<AvisarChegadaComando, RegistroFila> {

    private final RepositorioRegistroFila registros;
    private final PublicadorEventoFila eventos;

    AvisarChegadaCasoDeUso(RepositorioRegistroFila registros, PublicadorEventoFila eventos) {
        this.registros = registros;
        this.eventos = eventos;
    }

    @Override
    public RegistroFila executar(AvisarChegadaComando comando) {
        RegistroFila registro = RegistroFila.avisarChegada(
                comando.pacienteId(), comando.unidadeId(), comando.sintomas(), LocalDateTime.now());
        registros.salvar(registro);
        eventos.publicar(EventoFila.PACIENTE_A_CAMINHO, registro);
        return registro;
    }
}
