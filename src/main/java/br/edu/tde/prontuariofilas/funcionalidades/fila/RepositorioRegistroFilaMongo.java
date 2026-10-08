package br.edu.tde.prontuariofilas.funcionalidades.fila;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/** Adaptador — implementa {@link RepositorioRegistroFila} sobre Cosmos DB (API MongoDB). */
@Repository
class RepositorioRegistroFilaMongo implements RepositorioRegistroFila {

    private final MongoTemplate mongo;

    RepositorioRegistroFilaMongo(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    @Override
    public void salvar(RegistroFila registro) {
        mongo.save(RegistroFilaDocumento.de(registro));
    }

    @Override
    public Optional<RegistroFila> buscarPorId(String registroId) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public List<RegistroFila> listarAtivosPorUnidade(String unidadeId) {
        throw new UnsupportedOperationException("TODO");
    }
}
