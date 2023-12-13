package br.com.adegagatopreto.model;

import br.com.adegagatopreto.enums.ActiveStatus;
import br.com.adegagatopreto.enums.EmployeeRole;
import br.com.adegagatopreto.enums.UserType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActiveStatus status;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeRole role;
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

    public Employee() {
    }

    public Employee(String email, String password, UserType type, Long id, ActiveStatus status, EmployeeRole role, String name, String cpf, String phone, String cep, String address) {
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
        if (!(o instanceof Employee employee)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId(), employee.getId()) && getStatus() == employee.getStatus() && getRole() == employee.getRole() && Objects.equals(getName(), employee.getName()) && Objects.equals(getCpf(), employee.getCpf()) && Objects.equals(getPhone(), employee.getPhone()) && Objects.equals(getCep(), employee.getCep()) && Objects.equals(getAddress(), employee.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getStatus(), getRole(), getName(), getCpf(), getPhone(), getCep(), getAddress());
    }
}
