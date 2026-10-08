package br.edu.tde.prontuariofilas.compartilhado;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/** Framework — permite que um Endpoint receba {@link UsuarioAtual} como parâmetro, sem conhecer o JWT. */
class ResolvedorUsuarioAtual implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parametro) {
        return parametro.getParameterType() == UsuarioAtual.class;
    }

    @Override
    public UsuarioAtual resolveArgument(MethodParameter parametro, ModelAndViewContainer mav,
                                        NativeWebRequest requisicao, WebDataBinderFactory binderFactory) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new UsuarioAtual(
                Objects.requireNonNullElse(jwt.getClaimAsString("oid"), jwt.getSubject()),
                new HashSet<>(Objects.requireNonNullElse(jwt.getClaimAsStringList("roles"), List.of())),
                Objects.requireNonNullElse(jwt.getClaimAsStringList("unidades"), List.of()));
    }
}
