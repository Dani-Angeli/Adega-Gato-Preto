package br.com.adegagatopreto.data.vo.v1;

import br.com.adegagatopreto.enums.ActiveStatus;
import br.com.adegagatopreto.enums.EmployeeRole;
import br.com.adegagatopreto.enums.UserType;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeVO extends UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private ActiveStatus status;
    private EmployeeRole role;
    private String name;
    private String cpf;
    private String phone;
    private String cep;
    private String address;

    public EmployeeVO() {
    }

    public EmployeeVO(String email, String password, UserType type, Long id, ActiveStatus status, EmployeeRole role, String name, String cpf, String phone, String cep, String address) {
        super(email, password, type);
        this.id = id;
        this.status = status;
        this.role = role;
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

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
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
        if (!(o instanceof EmployeeVO that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId(), that.getId()) && getStatus() == that.getStatus() && getRole() == that.getRole() && Objects.equals(getName(), that.getName()) && Objects.equals(getCpf(), that.getCpf()) && Objects.equals(getPhone(), that.getPhone()) && Objects.equals(getCep(), that.getCep()) && Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getStatus(), getRole(), getName(), getCpf(), getPhone(), getCep(), getAddress());
    }
}
