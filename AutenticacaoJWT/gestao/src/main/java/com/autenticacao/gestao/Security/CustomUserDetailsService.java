package com.autenticacao.gestao.Security;

//import java.util.Optional;
//import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import com.autenticacao.gestao.Model.Usuario;
import com.autenticacao.gestao.Service.UsuarioService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    //private Usuario getUser(Supplier<Optional<Usuario>> supplier){
        //esse suplier é como se fosse um metodo if pois ele fica tentando achar alguma coisa
        //return supplier.get().orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    //}

    
    @Override
    public UserDetails loadUserByUsername(String email) {
        //Usuario usuario = getUser(() -> usuarioService.obterPorEmail(email));
        //return usuario;
        return usuarioService.obterPorEmail(email).get();
    
    }
    
    
    public UserDetails obterPorId(Long id) {
        
        return usuarioService.obterPorId(id).get();
    }
    



}
