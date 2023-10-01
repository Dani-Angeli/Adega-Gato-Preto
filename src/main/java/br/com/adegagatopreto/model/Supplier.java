package br.com.adegagatopreto.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "supplier")
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String cnpj;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private String address;

    public Supplier() {
    }

    public Supplier(Long id, String name, String cnpj, String email, String phone, String cep, String address) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(o instanceof Supplier supplier)) return false;
        return Objects.equals(getId(), supplier.getId()) && Objects.equals(getName(), supplier.getName()) && Objects.equals(getCnpj(),
                supplier.getCnpj()) && Objects.equals(getEmail(), supplier.getEmail()) && Objects.equals(getPhone(),
                supplier.getPhone()) && Objects.equals(getCep(), supplier.getCep()) && Objects.equals(getAddress(), supplier.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCnpj(), getEmail(), getPhone(), getCep(), getAddress());
    }
}
