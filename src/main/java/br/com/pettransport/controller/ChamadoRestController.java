package br.com.pettransport.controller;

import br.com.pettransport.entidade.EstadoChamado;
import br.com.pettransport.json.ChamadoJson;
import br.com.pettransport.service.ChamadoService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChamadoRestController {

    @Autowired
    private ChamadoService chamadoService;

    @RequestMapping(value = "/chamados", method = RequestMethod.GET)
    public String getChamado(@RequestParam(value = "motorista", required = false) Long motorista,
                             @RequestParam(value = "pet", required = false) Long pet,
                             @RequestParam(value = "cliente", required = false) Long cliente,
                             @RequestParam(value = "estadoChamado", required = false) EstadoChamado estadoChamado) {
        List<ChamadoJson> chamadoJsons = this.chamadoService.find(motorista, pet, estadoChamado, cliente);
        return new Gson().toJson(chamadoJsons);
    }

    @RequestMapping(value = "/chamados", method = RequestMethod.POST)
    public String salvarChamado(@RequestParam(value = "chamado") String chamadoJsonString) {
        ChamadoJson chamadoJson = this.chamadoService.salvarChamado(new Gson().fromJson(chamadoJsonString, ChamadoJson.class));
        return new Gson().toJson(chamadoJson);
    }
}
