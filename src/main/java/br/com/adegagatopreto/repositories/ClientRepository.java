package br.com.adegagatopreto.repositories;

import br.com.adegagatopreto.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
