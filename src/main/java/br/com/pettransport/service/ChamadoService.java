package br.com.pettransport.service;

import br.com.pettransport.entidade.Chamado;
import br.com.pettransport.entidade.EstadoChamado;
import br.com.pettransport.entidade.Motorista;
import br.com.pettransport.entidade.Pet;
import br.com.pettransport.json.ChamadoJson;
import br.com.pettransport.repository.ChamadoRepository;
import br.com.pettransport.repository.MotoristaRepository;
import br.com.pettransport.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;

@Component
@Transactional
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private MotoristaRepository motoristaRepository;
    @Autowired
    private PetRepository petRepository;

    public ChamadoJson salvarChamado(ChamadoJson chamadoJson) {
        Chamado chamado = new Chamado();
        chamado.setId(chamadoJson.id());
        chamado.setEstadoChamado(chamadoJson.estadoChamado());
        chamado.setLatitude(chamadoJson.latitude());
        chamado.setLongitude(chamadoJson.longitude());
        chamado.setServicos(chamadoJson.tiposServicos());

        if (chamadoJson.motorista() != null) {
            Optional<Motorista> motorista = this.motoristaRepository.findById(chamadoJson.motorista());
            chamado.setMotorista(motorista.get());
        }
        Optional<Pet> pet = this.petRepository.findById(chamadoJson.pet());
        chamado.setPet(pet.get());
        Chamado saved = this.chamadoRepository.save(chamado);
        return chamadoJson.id(saved.getId());
    }

    public List<ChamadoJson> find(Long motorista, Long pet, EstadoChamado estadoChamado, Long cliente) {
        if (motorista != null) {
            return this.toJsonList(this.chamadoRepository.findByMotoristaId(motorista));
        } else if (pet != null) {
            return this.toJsonList(this.chamadoRepository.findByPetId(pet));
        } else if (estadoChamado != null) {
            return this.toJsonList(this.chamadoRepository.findByEstadoChamado(estadoChamado));
        } else if (cliente != null) {
            List<Chamado> chamados = newArrayList();
            List<Pet> pets = this.petRepository.findByClienteId(cliente);
            for (Pet pet1 : pets) {
                chamados.addAll(this.chamadoRepository.findByPetId(pet1.getId()));
            }
            return this.toJsonList(chamados);
        }
        return this.toJsonList(newArrayList(this.chamadoRepository.findAll()));
    }

    private List<ChamadoJson> toJsonList(List<Chamado> chamados){
        List<ChamadoJson> jsonList = newArrayList();
        for (Chamado chamado : chamados) {
            jsonList.add(new ChamadoJson()
                    .id(chamado.getId())
                    .motorista(chamado.getMotorista()!= null ? chamado.getMotorista().getId(): null)
                    .pet(chamado.getPet().getId())
                    .latitude(chamado.getLatitude())
                    .longitude(chamado.getLongitude())
                    .estadoChamado(chamado.getEstadoChamado())
                    .tiposServicos(chamado.getServicos())
            );
        }
        return jsonList;
    }
}
