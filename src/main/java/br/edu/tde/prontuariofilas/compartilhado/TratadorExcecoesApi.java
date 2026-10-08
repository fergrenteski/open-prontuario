package br.edu.tde.prontuariofilas.compartilhado;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Adaptador — traduz exceções dos casos de uso para HTTP (RFC 7807). */
@RestControllerAdvice
class TratadorExcecoesApi {

    @ExceptionHandler(RecursoNaoEncontradoExcecao.class)
    ProblemDetail naoEncontrado(RecursoNaoEncontradoExcecao e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(AcessoNegadoExcecao.class)
    ProblemDetail acessoNegado(AcessoNegadoExcecao e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler(RegraDeNegocioExcecao.class)
    ProblemDetail regraDeNegocio(RegraDeNegocioExcecao e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    }
}
