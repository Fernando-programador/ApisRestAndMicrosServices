package com.example.classepessoa.http;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.classepessoa.compartilhado.AnimalDto;

@FeignClient(name = "controle-animais-ms")
public interface AnimaisFeignClients {
    
    @GetMapping (path = "/api/animais/{dono}/lista")
    List<AnimalDto> obterAnimais(@PathVariable Integer dono);
}
