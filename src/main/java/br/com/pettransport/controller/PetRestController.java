package br.com.pettransport.controller;

import br.com.pettransport.entidade.Pet;
import br.com.pettransport.json.PetJson;
import br.com.pettransport.json.PetSearchJson;
import br.com.pettransport.service.PetService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetRestController {

    @Autowired
    private PetService petService;

    @RequestMapping(value = "/pets", method = RequestMethod.GET)
    public List<Pet> getPets(@RequestParam(value = "id", required = false) Long id, @RequestParam(value = "cliente", required = false) Long cliente) {
        return this.petService.find(new PetSearchJson().id(id).cliente(cliente));
    }

    @RequestMapping(value = "/pets", method = RequestMethod.POST)
    public void salvarPet(@RequestParam(value = "id", required = false) Long id,
                          @RequestParam(value = "nome", required = false) String nome,
                          @RequestParam(value = "idade", required = false) Integer idade,
                          @RequestParam(value = "raca", required = false) String raca,
                          @RequestParam(value = "observacoes", required = false) String observacoes,
                          @RequestParam(value = "cliente", required = false) Long clienteId) {
        PetJson pet = new PetJson()
                .id(id)
                .nome(nome)
                .idade(idade)
                .raca(raca)
                .cliente(clienteId);
        this.petService.salvaPet(pet);
    }

}
