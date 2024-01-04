package br.com.adegagatopreto.repositories;

import br.com.adegagatopreto.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT c FROM Client c WHERE c.status = 'ACTIVE'")
    List<Client> findAllActive();

    @Query(value = "SELECT c FROM Client c WHERE c.id = :id AND c.status = 'ACTIVE'")
    Client findByIdActive(@Param("id") Long id);

    @Query(value = "SELECT c FROM Client c WHERE c.cpf = :cpf")
    Client findByCpf(@Param("cpf") String cpf);

    @Query(value = "SELECT c FROM Client c WHERE c.cpf = :cpf AND c.status = 'ACTIVE'")
    Client findByCpfActive(@Param("cpf") String cpf);

    @Query(value = "SELECT c FROM Client c WHERE c.email = :email")
    Client findByEmail(@Param("email") String email);
    @Query(value = "SELECT c FROM Client c WHERE c.email = :email AND c.status = 'ACTIVE'")
    Client findByEmailActive(@Param("email") String email);

    @Query(value = "SELECT c FROM Client c " +
            "WHERE (LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "       OR LOWER(c.cpf) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "       OR LOWER(c.email) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "AND c.status = 'ACTIVE'")
    List<Client> searchClient(@Param("search") String search);
    boolean existsClientById(Long id);
    boolean existsClientByCpf(String cpf);
    boolean existsClientByEmail(String email);
}
