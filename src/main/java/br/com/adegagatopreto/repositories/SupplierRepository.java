package br.com.adegagatopreto.repositories;

import br.com.adegagatopreto.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository <Supplier, Long> {
    @Query(value = "SELECT * FROM adega_gato_preto.supplier WHERE supplier.status = 'ACTIVE'", nativeQuery = true)
    List<Supplier> findAllActive();
    @Query(value = "SELECT * FROM adega_gato_preto.supplier WHERE supplier.id = ? AND supplier.status = 'ACTIVE'", nativeQuery = true)
    Supplier findByIdActive(Long id);
    @Query(value = "SELECT * FROM adega_gato_preto.supplier WHERE supplier.cnpj = ?", nativeQuery = true)
    Supplier findByCnpj(String cnpj);
}
