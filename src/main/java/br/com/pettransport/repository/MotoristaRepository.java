package br.com.pettransport.repository;

import br.com.pettransport.entidade.Motorista;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface MotoristaRepository extends CrudRepository<Motorista, Long> {

    Motorista findByUsernameAndPassword(String username, String password);
}
