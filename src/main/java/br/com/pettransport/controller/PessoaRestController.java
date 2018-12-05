package br.com.pettransport.controller;

import br.com.pettransport.json.PessoaJson;
import br.com.pettransport.json.PessoaSearchJson;
import br.com.pettransport.json.TipoPessoa;
import br.com.pettransport.service.PessoaService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PessoaRestController {

    @Autowired
    private PessoaService pessoaService;

    @RequestMapping(value = "/pessoas", method = RequestMethod.GET)
    public String getPessoas(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("tipo") TipoPessoa tipoPessoa) {
        PessoaJson pessoaJson = this.pessoaService.find(new PessoaSearchJson().username(username).password(password).tipoPessoa(tipoPessoa));
        return new Gson().toJson(pessoaJson);
    }

    @RequestMapping(value = "/pessoas", method = RequestMethod.POST)
    public String salvarPessoa(@RequestParam("pessoa") String clienteJson) {
        PessoaJson pessoaJson = this.pessoaService.salvarPessoa(new Gson().fromJson(clienteJson, PessoaJson.class));
        return new Gson().toJson(pessoaJson);
    }

}
