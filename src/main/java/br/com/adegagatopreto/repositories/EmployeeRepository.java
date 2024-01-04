package br.com.adegagatopreto.repositories;

import br.com.adegagatopreto.model.Client;
import br.com.adegagatopreto.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository <Employee, Long> {

    @Query(value = "SELECT e FROM Employee e WHERE e.status = 'ACTIVE'")
    List<Employee> findAllActive();

    @Query(value = "SELECT e FROM Employee e WHERE e.id = :id AND e.status = 'ACTIVE'")
    Employee findByIdActive(@Param("id") Long id);

    @Query(value = "SELECT e FROM Employee e WHERE e.cpf = :cpf")
    Employee findByCpf(@Param("cpf") String cpf);

    @Query(value = "SELECT e FROM Employee e WHERE e.cpf = :cpf AND e.status = 'ACTIVE'")
    Employee findByCpfActive(@Param("cpf") String cpf);

    @Query(value = "SELECT e FROM Employee e WHERE e.email = :email")
    Employee findByEmail(@Param("email") String email);
    @Query(value = "SELECT e FROM Employee e WHERE e.email = :email AND e.status = 'ACTIVE'")
    Employee findByEmailActive(@Param("email") String email);

    @Query(value = "SELECT e FROM Employee e " +
            "WHERE (LOWER(e.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "       OR LOWER(e.cpf) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "       OR LOWER(e.email) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "AND e.status = 'ACTIVE'")
    List<Employee> searchClient(@Param("search") String search);

    boolean existsEmployeeById(Long id);
    boolean existsEmployeeByCpf(String cpf);
    boolean existsEmployeeByEmail(String email);
}
