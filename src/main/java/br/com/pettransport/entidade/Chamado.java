package br.com.pettransport.entidade;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "CHAMADO")
public class Chamado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @JoinColumn
    @ManyToOne
    private Pet pet;
    @JoinColumn
    @ManyToOne
    private Motorista motorista;
    @Column
    private Double latitude;
    @Column
    private Double longitude;
    @Column
    private EstadoChamado estadoChamado;
    @ElementCollection(targetClass=TipoServico.class)
    @CollectionTable(name="TipoServicos")
    @Column
    private List<TipoServico> servicos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public EstadoChamado getEstadoChamado() {
        return estadoChamado;
    }

    public void setEstadoChamado(EstadoChamado estadoChamado) {
        this.estadoChamado = estadoChamado;
    }

    public List<TipoServico> getServicos() {
        return servicos;
    }

    public void setServicos(List<TipoServico> servicos) {
        this.servicos = servicos;
    }
}
