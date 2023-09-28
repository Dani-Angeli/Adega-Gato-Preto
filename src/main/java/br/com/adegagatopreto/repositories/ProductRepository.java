package br.com.adegagatopreto.repositories;

import br.com.adegagatopreto.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
