package br.com.pettransport.repository;

import br.com.pettransport.entidade.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PetRepository extends CrudRepository<Pet, Long> {

    List<Pet> findByClienteId(Long cliente);

}
