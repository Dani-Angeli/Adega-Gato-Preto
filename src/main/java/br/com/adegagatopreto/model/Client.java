package br.com.adegagatopreto.model;

import br.com.adegagatopreto.enums.ActiveStatus;
import br.com.adegagatopreto.enums.UserType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "client")
public class Client extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActiveStatus status;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private String address;

    public Client() {
    }

    public Client(String email, String password, UserType type, Long id, ActiveStatus status, String name, String cpf, String phone, String cep, String address) {
        super(email, password, type);
        this.id = id;
        this.status = status;
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.cep = cep;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActiveStatus getStatus() {
        return status;
    }

    public void setStatus(ActiveStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId(), client.getId()) && getStatus() == client.getStatus() && Objects.equals(getName(), client.getName()) && Objects.equals(getCpf(), client.getCpf()) && Objects.equals(getPhone(), client.getPhone()) && Objects.equals(getCep(), client.getCep()) && Objects.equals(getAddress(), client.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getStatus(), getName(), getCpf(), getPhone(), getCep(), getAddress());
    }
}
