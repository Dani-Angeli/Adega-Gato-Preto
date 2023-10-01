package br.com.adegagatopreto.repositories;

import br.com.adegagatopreto.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository <Supplier, Long> {
}
