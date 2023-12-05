package br.com.adegagatopreto.data.vo.v1;

import br.com.adegagatopreto.enums.ActiveStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

public class ClientVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private ActiveStatus status;
    private String name;

    private String cpf;

    private String email;

    private String phone;

    private String cep;

    private String address;

    public ClientVO() {
    }

    public ClientVO(Long id, ActiveStatus status, String name, String cpf, String email, String phone, String cep, String address) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.cpf = cpf;
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
        if (!(o instanceof ClientVO clientVO)) return false;
        return Objects.equals(getId(), clientVO.getId()) && getStatus() == clientVO.getStatus() && Objects.equals(getName(), clientVO.getName()) && Objects.equals(getCpf(), clientVO.getCpf()) && Objects.equals(getEmail(), clientVO.getEmail()) && Objects.equals(getPhone(), clientVO.getPhone()) && Objects.equals(getCep(), clientVO.getCep()) && Objects.equals(getAddress(), clientVO.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getName(), getCpf(), getEmail(), getPhone(), getCep(), getAddress());
    }
}
