package br.com.adegagatopreto.repositories;

import br.com.adegagatopreto.model.Client;
import br.com.adegagatopreto.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository <Employee, Long> {

    @Query(value = "SELECT * FROM adega_gato_preto.employee WHERE employee.status = 'ACTIVE'", nativeQuery = true)
    List<Employee> findAllActive();

    @Query(value = "SELECT * FROM adega_gato_preto.employee WHERE employee.id = ? AND employee.status = 'ACTIVE'", nativeQuery = true)
    Employee findByIdActive(Long id);

    @Query(value = "SELECT * FROM adega_gato_preto.employee WHERE employee.cpf = ?", nativeQuery = true)
    Employee findByCpf(String cpf);

    @Query(value = "SELECT * FROM adega_gato_preto.employee WHERE employee.cpf = ? AND employee.status = 'ACTIVE'", nativeQuery = true)
    Employee findByCpfActive(String cpf);

    @Query(value = "SELECT * FROM adega_gato_preto.employee WHERE employee.email = ?", nativeQuery = true)
    Employee findByEmail(String cpf);
    @Query(value = "SELECT * FROM adega_gato_preto.employee WHERE employee.email = ? AND employee.status = 'ACTIVE'", nativeQuery = true)
    Employee findByEmailActive(String cpf);

    boolean existsEmployeeById(Long id);
    boolean existsEmployeeByCpf(String cpf);
    boolean existsEmployeeByEmail(String email);
}
