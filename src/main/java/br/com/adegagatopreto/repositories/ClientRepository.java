package br.com.adegagatopreto.repositories;

import br.com.adegagatopreto.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT * FROM adega_gato_preto.client WHERE client.status = 'ACTIVE'", nativeQuery = true)
    List<Client> findAllActive();

    @Query(value = "SELECT * FROM adega_gato_preto.client WHERE client.id = ? AND client.status = 'ACTIVE'", nativeQuery = true)
    Client findByIdActive(Long id);


    Client findClientById(Long id);

    @Query(value = "SELECT * FROM adega_gato_preto.client WHERE client.cpf = ?", nativeQuery = true)
    Client findByCpf(String cpf);

    @Query(value = "SELECT * FROM adega_gato_preto.client WHERE client.cpf = ? AND client.status = 'ACTIVE'", nativeQuery = true)
    Client findByCpfActive(String cpf);

    @Query(value = "SELECT * FROM adega_gato_preto.client WHERE client.email = ?", nativeQuery = true)
    Client findByEmail(String cpf);
    @Query(value = "SELECT * FROM adega_gato_preto.client WHERE client.email = ? AND client.status = 'ACTIVE'", nativeQuery = true)
    Client findByEmailActive(String cpf);

    boolean existsClientById(Long id);
    boolean existsClientByCpf(String cpf);
    boolean existsClientByEmail(String email);
}
