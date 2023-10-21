package br.com.adegagatopreto.data.vo.v1;

import br.com.adegagatopreto.enums.EmployeeRole;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private EmployeeRole role;
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private String cep;
    private String address;

    public EmployeeVO() {
    }

    public EmployeeVO(Long id, String username, String password, EmployeeRole role, String name, String cpf, String email,
                      String phone, String cep, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(o instanceof EmployeeVO that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getPassword(),
                that.getPassword()) && getRole() == that.getRole() && Objects.equals(getName(), that.getName()) && Objects.equals(getCpf(),
                that.getCpf()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPhone(),
                that.getPhone()) && Objects.equals(getCep(), that.getCep()) && Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getRole(), getName(), getCpf(), getEmail(), getPhone(), getCep(),
                getAddress());
    }
}
