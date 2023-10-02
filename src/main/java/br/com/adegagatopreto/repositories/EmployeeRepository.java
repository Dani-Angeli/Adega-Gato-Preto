package br.com.adegagatopreto.repositories;

import br.com.adegagatopreto.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository <Employee, Long> {
}
