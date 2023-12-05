package br.com.adegagatopreto.repositories;

import br.com.adegagatopreto.model.Client;
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

    @Query(value = "SELECT * FROM adega_gato_preto.supplier WHERE supplier.cnpj = ? AND supplier.status = 'ACTIVE'", nativeQuery = true)
    Supplier findByCnpjActive(String cnpj);

    @Query(value = "SELECT * FROM adega_gato_preto.supplier WHERE supplier.email = ?", nativeQuery = true)
    Supplier findByEmail(String cnpj);
    @Query(value = "SELECT * FROM adega_gato_preto.supplier WHERE supplier.email = ? AND supplier.status = 'ACTIVE'", nativeQuery = true)
    Supplier findByEmailActive(String cnpj);

    boolean existsSupplierById(Long id);
    boolean existsSupplierByCnpj(String cnpj);
    boolean existsSupplierByEmail(String email);
}
