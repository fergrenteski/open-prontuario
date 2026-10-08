package br.edu.tde.prontuariofilas.funcionalidades.auditoria;

import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/** Adaptador — coleção auditoria (Cosmos DB, API MongoDB). */
@Repository
class RepositorioLogAuditoriaMongo implements RepositorioLogAuditoria {

    private final MongoTemplate mongo;

    RepositorioLogAuditoriaMongo(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    @Override
    public void registrar(LogAuditoria log) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public List<LogAuditoria> listarPorProntuario(String prontuarioId) {
        throw new UnsupportedOperationException("TODO");
    }
}
