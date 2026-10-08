package br.edu.tde.prontuariofilas.funcionalidades.unidade;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/** Adaptador — coleção unidades (Cosmos DB, API MongoDB). */
@Repository
class RepositorioUnidadeSaudeMongo implements RepositorioUnidadeSaude {

    private final MongoTemplate mongo;

    RepositorioUnidadeSaudeMongo(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    @Override
    public Optional<UnidadeSaude> buscarPorId(String unidadeId) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public List<UnidadeSaude> listarTodas() {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public List<UnidadeSaude> listarPorIds(List<String> unidadeIds) {
        throw new UnsupportedOperationException("TODO");
    }
}
