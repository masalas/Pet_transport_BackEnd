package br.com.pettransport.json;

import java.io.Serializable;

public class PessoaSearchJson implements Serializable {
    private String username;
    private String password;
    private TipoPessoa tipoPessoa;

    public String username() {
        return username;
    }

    public PessoaSearchJson username(String username) {
        this.username = username;
        return this;
    }

    public String password() {
        return password;
    }

    public PessoaSearchJson password(String password) {
        this.password = password;
        return this;
    }

    public TipoPessoa tipoPessoa() {
        return tipoPessoa;
    }

    public PessoaSearchJson tipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
        return this;
    }
}
