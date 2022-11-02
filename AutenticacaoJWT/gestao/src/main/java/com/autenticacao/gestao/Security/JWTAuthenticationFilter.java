package com.autenticacao.gestao.Security;

import java.io.IOException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // metodo princiapl onde toda requisição chega antes do endpoint
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // pego o token de dentro da requisição
        String token = obterToken(request);

        // pego o id do usuario que está dentro do token
        Optional<Long> id = jwtService.obterIdUsuario(token);

        if (!id.isPresent()) {
            throw new InputMismatchException("TOKEN INVÁLIDO");
        }

        // pego o usuario dono do token pelo seu id
        UserDetails usuario = customUserDetailsService.obterPorId(id.get());

        // verificando se o usuario esta autenticado ou não
        // aqui tbm poderiamos validar as permissões.
        UsernamePasswordAuthenticationToken autenticacao = new UsernamePasswordAuthenticationToken(usuario, null,
                Collections.emptyList());

        // Mudando a autenticação para a propria requisição
        autenticacao.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // repassando a autenticação para o contexto do security
        // a partir de agora o java faz o restante
        SecurityContextHolder.getContext().setAuthentication(autenticacao);

    }

    private String obterToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        // verifica se veio alguma coisa sem ser espaços em brancos dentro do token
        // ele é importado da classe spring frameworks
        if (StringUtils.hasText(token)) {
            return null;

        }
        return token.substring(7);

    }

}