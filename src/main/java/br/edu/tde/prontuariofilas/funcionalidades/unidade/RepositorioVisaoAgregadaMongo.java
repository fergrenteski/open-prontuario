package br.edu.tde.prontuariofilas.funcionalidades.unidade;

import java.util.Optional;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/** Adaptador — coleção visao_agregada (Cosmos DB, API MongoDB). */
@Repository
class RepositorioVisaoAgregadaMongo implements RepositorioVisaoAgregada {

    private final MongoTemplate mongo;

    RepositorioVisaoAgregadaMongo(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    @Override
    public Optional<VisaoAgregada> buscarPorUnidade(String unidadeId) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void salvar(VisaoAgregada visao) {
        throw new UnsupportedOperationException("TODO");
    }
}
