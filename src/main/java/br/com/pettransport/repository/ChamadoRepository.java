package br.com.pettransport.repository;

import br.com.pettransport.entidade.Chamado;
import br.com.pettransport.entidade.EstadoChamado;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChamadoRepository extends CrudRepository<Chamado, Long> {

    List<Chamado> findByMotoristaId(Long id);
    List<Chamado> findByPetId(Long id);
    List<Chamado> findByEstadoChamado(EstadoChamado estadoChamado);
}
