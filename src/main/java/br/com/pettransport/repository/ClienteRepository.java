package br.com.pettransport.repository;

import br.com.pettransport.entidade.Cliente;
import br.com.pettransport.entidade.Motorista;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    Cliente findByUsernameAndPassword(String username, String password);

}
