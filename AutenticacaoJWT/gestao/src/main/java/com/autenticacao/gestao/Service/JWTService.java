package com.autenticacao.gestao.Service;

import java.io.Serializable;
import java.sql.Date;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.autenticacao.gestao.Model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTService  implements Serializable{
    
    //chave secreta para codificar  e decodificar o token
    private static final String ChavePrivadaJWT = "secretKey";

    /**
     * Metodo para gerar o token JWT
     * @param authentication
     * @return
     */
    public String gerarToken(Authentication authentication){


        // um segundo é igual 1000 milisegundos(um dia  = 86400000)
        //pode variar conforme a regra de negocio
        int tempoExpiracao = 86400000;

        //criando uma data de expiração em base na data que foi criado o token
        Date dataExpiração = new Date(new Date(tempoExpiracao).getTime() + tempoExpiracao);

        //Aqui pegando usuario atua da autenticação
        Usuario usuario = (Usuario) authentication.getPrincipal();


        // aqui pega todos os dados e retorna um token pronto
        return Jwts.builder()
                .setSubject(usuario.getId().toString())
                .setIssuedAt(new Date(tempoExpiracao))
                .setExpiration(dataExpiração)
                .signWith(SignatureAlgorithm.HS512, ChavePrivadaJWT)
                .compact();
                   
    }

    /**
     * metodo para retornar o id do usuario dono do token
     * @param token do usuario
     * @return id do usuario
     */
    public Optional<Long> obterIdUsuario(String token){

        try {
            //retorna as permissões do token
            Claims claims = parse(token).getBody();

            //Retorna o id de dentro do ttoken se entrrar caso contrario retorna null
            return  Optional.ofNullable(Long.parseLong(claims.getSubject()));

        } catch (Exception e) {
            // TODO: handle exception

            //senão encontrar nada retorna vazio
            return Optional.empty();
        }
    }
    
    //metodo que sabe descobrir de dentro do token com base na chave privada qual as permissõs do usuario.
    private Jws<Claims> parse(String token){
        return Jwts.parser().setSigningKey(ChavePrivadaJWT).parseClaimsJws(token);

    }

}
