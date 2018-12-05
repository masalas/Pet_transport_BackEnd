package br.com.pettransport.service;

import br.com.pettransport.entidade.Cliente;
import br.com.pettransport.entidade.Motorista;
import br.com.pettransport.entidade.Pessoa;
import br.com.pettransport.json.PessoaJson;
import br.com.pettransport.json.PessoaSearchJson;
import br.com.pettransport.repository.ClienteRepository;
import br.com.pettransport.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import static br.com.pettransport.json.TipoPessoa.CLIENTE;
import static br.com.pettransport.json.TipoPessoa.MOTORISTA;

@Component
@Transactional
public class PessoaService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private MotoristaRepository motoristaRepository;

    public PessoaJson find(PessoaSearchJson searchJson) {
        Pessoa pessoa;
        PessoaJson pessoaJson = new PessoaJson();
        if (searchJson.tipoPessoa().equals(MOTORISTA)) {
            pessoa = this.motoristaRepository.findByUsernameAndPassword(searchJson.username(), searchJson.password());
            pessoaJson.tipoPessoa(MOTORISTA);
        } else {
            pessoa = this.clienteRepository.findByUsernameAndPassword(searchJson.username(), searchJson.password());
            pessoaJson.tipoPessoa(CLIENTE);
        }
        if (pessoa == null) {
            throw new RuntimeException("Usuário não existente ou senha incorreta");
        }
        return pessoaJson
                .nome(pessoa.getNome())
                .cpf(pessoa.getCpf())
                .dataNascimento(pessoa.getDataNascimento())
                .username(pessoa.getUsername())
                .password(pessoa.getPassword())
                .id(pessoa.getId());
    }

    public PessoaJson salvarPessoa(PessoaJson json) {
        Pessoa pessoa = new Cliente();
        if (json.tipoPessoa() != null && json.tipoPessoa().equals(MOTORISTA)) {
            pessoa = new Motorista();
        }
        pessoa.setId(json.id());
        pessoa.setNome(json.nome());
        pessoa.setCpf(json.cpf());
        pessoa.setDataNascimento(json.dataNascimento());
        pessoa.setUsername(json.username());
        pessoa.setPassword(json.password());
        if (json.tipoPessoa() != null && json.tipoPessoa().equals(MOTORISTA)) {
            Motorista motoristaSalvo = this.motoristaRepository.save((Motorista) pessoa);
            json.id(motoristaSalvo.getId());
        } else {
            Cliente clienteSalvo = this.clienteRepository.save((Cliente) pessoa);
            json.id(clienteSalvo.getId());
            json.tipoPessoa(CLIENTE);
        }
        return json;
    }
}
