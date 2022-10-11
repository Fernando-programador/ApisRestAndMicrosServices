package br.com.teste.animaisms.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.animaisms.compartilhado.AnimalDto;
import br.com.teste.animaisms.service.AnimalService;
import br.com.teste.animaisms.view.model.AnimalModeloAlteracao;
import br.com.teste.animaisms.view.model.AnimalModeloInclusao;
import br.com.teste.animaisms.view.model.AnimalModeloResponse;

@RestController
@RequestMapping("/api/animais")
public class AnimalController {
    @Autowired
    private AnimalService service;

    @GetMapping(value="/status")
    public String statusServico(@Value("${local.server.port}") String porta) {
        return String.format("Serviço ativo e executando na porta %s", porta);
    }    

    @PostMapping
    public ResponseEntity<AnimalModeloResponse> criarAnimal(@RequestBody @Valid AnimalModeloInclusao Animal) {
        ModelMapper mapper = new ModelMapper();
        AnimalDto dto = mapper.map(Animal, AnimalDto.class);
        dto = service.criarAnimal(dto);
        return new ResponseEntity<>(mapper.map(dto, AnimalModeloResponse.class), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<AnimalModeloResponse>> obterTodos() {
        List<AnimalDto> dtos = service.obterTodos();

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<AnimalModeloResponse> resp = dtos.stream()
                    .map(dto -> mapper.map(dto, AnimalModeloResponse.class))
                    .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value="/{dono}/lista")
    public ResponseEntity<List<AnimalModeloResponse>> obterPorDono(@PathVariable Integer dono) {
        List<AnimalDto> dtos = service.obterPorDono(dono);

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<AnimalModeloResponse> resp = dtos.stream()
                    .map(dto -> mapper.map(dto, AnimalModeloResponse.class))
                    .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<AnimalModeloResponse> obterPorId(@PathVariable Integer id) {
        Optional<AnimalDto> Animal = service.obterPorId(id);

        if(Animal.isPresent()) {
            return new ResponseEntity<>(
                new ModelMapper().map(Animal.get(), AnimalModeloResponse.class), 
                HttpStatus.OK
            );
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<AnimalModeloResponse> atualizarAnimal(@PathVariable Integer id,
        @Valid @RequestBody AnimalModeloAlteracao Animal) {
        ModelMapper mapper = new ModelMapper();
        AnimalDto dto = mapper.map(Animal, AnimalDto.class);
        dto = service.atualizarAnimal(id, dto);

        return new ResponseEntity<>(mapper.map(dto, AnimalModeloResponse.class), HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> removerAnimal(@PathVariable Integer id) {
        service.removerAnimal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value="/{id}")
    public ResponseEntity<Void> definirMorto(@PathVariable Integer id) {
        if(service.definirComoMorto(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
