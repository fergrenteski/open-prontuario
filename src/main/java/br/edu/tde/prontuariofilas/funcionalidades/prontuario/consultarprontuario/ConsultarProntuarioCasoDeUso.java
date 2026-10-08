package br.edu.tde.prontuariofilas.funcionalidades.prontuario.consultarprontuario;

import br.edu.tde.prontuariofilas.compartilhado.CasoDeUso;
import br.edu.tde.prontuariofilas.funcionalidades.auditoria.RepositorioLogAuditoria;
import br.edu.tde.prontuariofilas.funcionalidades.consentimento.RepositorioConsentimento;
import br.edu.tde.prontuariofilas.funcionalidades.prontuario.Prontuario;
import br.edu.tde.prontuariofilas.funcionalidades.prontuario.RepositorioProntuario;
import org.springframework.stereotype.Service;

/**
 * CasoDeUso — RF12 + RF15. Exige usuário lotado na unidade e consentimento válido
 * (senão AcessoNegadoExcecao → 403); todo acesso concedido gera LogAuditoria.
 */
@Service
class ConsultarProntuarioCasoDeUso implements CasoDeUso<ConsultarProntuarioComando, Prontuario> {

    private final RepositorioProntuario prontuarios;
    private final RepositorioConsentimento consentimentos;
    private final RepositorioLogAuditoria auditoria;

    ConsultarProntuarioCasoDeUso(RepositorioProntuario prontuarios, RepositorioConsentimento consentimentos,
                                 RepositorioLogAuditoria auditoria) {
        this.prontuarios = prontuarios;
        this.consentimentos = consentimentos;
        this.auditoria = auditoria;
    }

    @Override
    public Prontuario executar(ConsultarProntuarioComando comando) {
        throw new UnsupportedOperationException("TODO");
    }
}
