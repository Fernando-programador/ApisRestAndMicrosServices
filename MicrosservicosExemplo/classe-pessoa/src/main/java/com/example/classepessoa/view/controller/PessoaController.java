package com.example.classepessoa.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.classepessoa.compartilhado.PessoaDto;
import com.example.classepessoa.http.AnimaisFeignClients;
import com.example.classepessoa.model.Pessoa;
import com.example.classepessoa.service.PessoaService;
import com.example.classepessoa.view.model.PessoaModeloRequest;
import com.example.classepessoa.view.model.PessoaModeloResponse;
import com.example.classepessoa.view.model.PessoaModeloResponseDetalhe;




@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService service;


    @GetMapping(value="/status")
    public String statusServico(@Value("${local.server.port}") String porta) {
        return String.format("Serviço ativo e executando na porta %s", porta);
    }
    

    @PostMapping
    public ResponseEntity<PessoaModeloResponse> criarPessoa(@RequestBody @Validated PessoaModeloRequest pessoa) {
        ModelMapper mapper = new ModelMapper();
        PessoaDto dto = mapper.map(pessoa, PessoaDto.class);
        dto = service.criarPessoa(dto);
        return new ResponseEntity<>(mapper.map(dto, PessoaModeloResponse.class), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<PessoaModeloResponse>> obterTodos() {
        List<PessoaDto> dtos = service.obterTodos();

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<PessoaModeloResponse> resp = dtos.stream()
                    .map(dto -> mapper.map(dto, PessoaModeloResponse.class))
                    .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<PessoaModeloResponseDetalhe> obterPorId(@PathVariable Integer id) {
        Optional<PessoaDto> pessoa = service.obterPorId(id);

        if(pessoa.isPresent()) {
            return new ResponseEntity<>(
                new ModelMapper().map(pessoa.get(), PessoaModeloResponseDetalhe.class), 
                HttpStatus.OK
            );
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<PessoaModeloResponse> atualizarPessoa(@PathVariable Integer id,
        @Validated @RequestBody Pessoa pessoa) {
        ModelMapper mapper = new ModelMapper();
        PessoaDto dto = mapper.map(pessoa, PessoaDto.class);
        dto = service.atualizarPessoa(id, dto);

        return new ResponseEntity<>(mapper.map(dto, PessoaModeloResponse.class), HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> removerPessoa(@PathVariable Integer id) {
        service.removerPessoa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
}
