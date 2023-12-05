package br.com.adegagatopreto.model;

import br.com.adegagatopreto.enums.ActiveStatus;
import br.com.adegagatopreto.enums.EmployeeRole;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActiveStatus status;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeRole role;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private String address;

    public Employee() {
    }

    public Employee(Long id, ActiveStatus status, String username, String password, EmployeeRole role, String name, String cpf, String email, String phone, String cep, String address) {
        this.id = id;
        this.status = status;
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

    public ActiveStatus getStatus() {
        return status;
    }

    public void setStatus(ActiveStatus status) {
        this.status = status;
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
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(getId(), employee.getId()) && getStatus() == employee.getStatus() && Objects.equals(getUsername(), employee.getUsername()) && Objects.equals(getPassword(), employee.getPassword()) && getRole() == employee.getRole() && Objects.equals(getName(), employee.getName()) && Objects.equals(getCpf(), employee.getCpf()) && Objects.equals(getEmail(), employee.getEmail()) && Objects.equals(getPhone(), employee.getPhone()) && Objects.equals(getCep(), employee.getCep()) && Objects.equals(getAddress(), employee.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getUsername(), getPassword(), getRole(), getName(), getCpf(), getEmail(), getPhone(), getCep(), getAddress());
    }
}
