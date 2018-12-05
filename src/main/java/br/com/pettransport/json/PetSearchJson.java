package br.com.pettransport.json;

import java.io.Serializable;
import java.util.Objects;

public class PetSearchJson implements Serializable {

    private Long id;
    private Long cliente;

    public Long id() {
        return id;
    }

    public PetSearchJson id(Long id) {
        this.id = id;
        return this;
    }

    public Long cliente() {
        return cliente;
    }

    public PetSearchJson cliente(Long cliente) {
        this.cliente = cliente;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetSearchJson that = (PetSearchJson) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(cliente, that.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cliente);
    }
}
