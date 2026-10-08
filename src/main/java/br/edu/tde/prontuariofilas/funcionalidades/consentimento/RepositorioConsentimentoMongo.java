package br.edu.tde.prontuariofilas.funcionalidades.consentimento;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/** Adaptador — coleção consentimentos (Cosmos DB, API MongoDB). */
@Repository
class RepositorioConsentimentoMongo implements RepositorioConsentimento {

    private final MongoTemplate mongo;

    RepositorioConsentimentoMongo(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    @Override
    public void salvar(Consentimento consentimento) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Optional<Consentimento> buscarPorId(String consentimentoId) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public List<Consentimento> listarPorPaciente(String pacienteId) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Optional<Consentimento> buscarAtivo(String pacienteId, String unidadeId) {
        throw new UnsupportedOperationException("TODO");
    }
}
