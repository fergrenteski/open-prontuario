package br.edu.tde.prontuariofilas.funcionalidades.prontuario;

import java.util.Optional;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/** Adaptador — coleções prontuarios + atendimentos (Cosmos DB, API MongoDB). */
@Repository
class RepositorioProntuarioMongo implements RepositorioProntuario {

    private final MongoTemplate mongo;

    RepositorioProntuarioMongo(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    @Override
    public Optional<Prontuario> buscarPorPaciente(String pacienteId) {
        throw new UnsupportedOperationException("TODO");
    }
}
