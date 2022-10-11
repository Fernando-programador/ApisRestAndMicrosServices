package br.com.teste.animaisms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.animaisms.model.Animal;

@Repository
public interface AnimalRepositorio extends JpaRepository<Animal, Integer> {

	List<Animal> findByDono(Integer dono);
    
}
