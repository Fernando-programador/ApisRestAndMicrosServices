package com.autenticacao.gestao.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.autenticacao.gestao.Model.Usuario;
import com.autenticacao.gestao.Repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<Usuario> obterTodos(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obterPorId(Long id){
        return usuarioRepository.findById(id);
    }

    
    public Optional<Usuario> obterPorEmail(String email){
        return usuarioRepository.findbyEmail(email);
    }


    public Usuario adicionar(Usuario usuario){
        usuario.setId(null);

        if (obterPorEmail(usuario.getEmail()).isPresent()){
            //verificando se ja tem um cadaastro de email registrado e vou lançar uma exceção
            throw new InputMismatchException("Já existe um usuário cadastrado com o email: " + usuario.getEmail() + " Operação inválida.");
        }

        //cadastrando uma senha para usuario  codificado gerando um hash da senha
        String senha = passwordEncoder.encode(usuario.getSenha());

        usuario.setSenha(senha);

        return usuarioRepository.save(usuario);
    }





}
