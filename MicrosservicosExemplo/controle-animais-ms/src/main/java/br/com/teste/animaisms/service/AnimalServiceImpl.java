package br.com.teste.animaisms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.animaisms.compartilhado.AnimalDto;
import br.com.teste.animaisms.model.Animal;
import br.com.teste.animaisms.repository.AnimalRepositorio;

@Service
public class AnimalServiceImpl implements AnimalService {
    @Autowired
    private AnimalRepositorio repo;

    @Override
    public AnimalDto criarAnimal(AnimalDto animal) {
        return salvarAnimal(animal);
    }

    @Override
    public List<AnimalDto> obterTodos() {
        List<Animal> animais = repo.findAll();

        return animais.stream()
            .map(animal -> new ModelMapper().map(animal, AnimalDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<AnimalDto> obterPorId(Integer id) {
        Optional<Animal> animal = repo.findById(id);

       if(animal.isPresent()) {
           return Optional.of(new ModelMapper().map(animal.get(), AnimalDto.class));
       }

       return Optional.empty();
    }

    @Override
    public List<AnimalDto> obterPorDono(Integer dono) {
        List<Animal> animais = repo.findByDono(dono);

        return animais.stream()
            .map(animal -> new ModelMapper().map(animal, AnimalDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public void removerAnimal(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public boolean definirComoMorto(Integer id) {
        Optional<Animal> animal = repo.findById(id);
        if(animal.isPresent()) {
            animal.get().setVivo(false);
            repo.save(animal.get());

            return true;
        }

        return false;
    }

    @Override
    public AnimalDto atualizarAnimal(Integer id, AnimalDto animal) {
        animal.setId(id);
        return salvarAnimal(animal);
    }

    private AnimalDto salvarAnimal(AnimalDto animal) {
        ModelMapper mapper = new ModelMapper();
        Animal animalEntidade = mapper.map(animal, Animal.class);
        animalEntidade = repo.save(animalEntidade);

        return mapper.map(animalEntidade, AnimalDto.class);
    }
    
}
