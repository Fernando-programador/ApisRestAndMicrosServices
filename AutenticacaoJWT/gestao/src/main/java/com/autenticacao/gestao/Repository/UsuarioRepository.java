package com.autenticacao.gestao.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autenticacao.gestao.Model.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //Optional<Usuario> findbyId(Long id);
    Optional<Usuario> findbyEmail(String email);
    
}
