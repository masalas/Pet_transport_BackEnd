package br.com.pettransport.service;

import br.com.pettransport.entidade.Cliente;
import br.com.pettransport.entidade.Pet;
import br.com.pettransport.json.PetJson;
import br.com.pettransport.repository.ClienteRepository;
import br.com.pettransport.repository.PetRepository;
import br.com.pettransport.json.PetSearchJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;

@Component
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public Pet salvaPet(PetJson json) {
        Pet pet = new Pet();
        pet.setId(json.id());
        pet.setNome(json.nome());
        pet.setIdade(json.idade());
        pet.setRaca(json.raca());

        Optional<Cliente> clienteOptional = this.clienteRepository.findById(json.cliente());
        if (clienteOptional.isPresent()) {
            pet.setCliente(clienteOptional.get());
        }
        return this.petRepository.save(pet);
    }

    public List<Pet> find(PetSearchJson searchJson) {
        if (searchJson.id() != null) {
            Optional<Pet> pet = this.petRepository.findById(searchJson.id());
            if (pet.isPresent()) {
                return newArrayList(pet.get());
            } else {
                return newArrayList();
            }
        } else if (searchJson.cliente() != null) {
            return this.petRepository.findByClienteId(searchJson.cliente());
        }
        return newArrayList(this.petRepository.findAll());
    }
}
