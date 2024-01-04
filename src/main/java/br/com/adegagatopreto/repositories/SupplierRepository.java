package br.com.adegagatopreto.repositories;

import br.com.adegagatopreto.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends JpaRepository <Supplier, Long> {
    @Query(value = "SELECT s FROM Supplier s WHERE s.status = 'ACTIVE'")
    List<Supplier> findAllActive();
    @Query(value = "SELECT s FROM Supplier s WHERE s.id = :id AND s.status = 'ACTIVE'")
    Supplier findByIdActive(@Param("id") Long id);
    @Query(value = "SELECT s FROM Supplier s WHERE s.cnpj = :cnpj")
    Supplier findByCnpj(@Param("cnpj") String cnpj);

    @Query(value = "SELECT s FROM Supplier s WHERE s.cnpj = :cnpj AND s.status = 'ACTIVE'")
    Supplier findByCnpjActive(@Param("cnpj") String cnpj);

    @Query(value = "SELECT s FROM Supplier s WHERE s.email = :email")
    Supplier findByEmail(@Param("email") String email);
    @Query(value = "SELECT s FROM Supplier s WHERE s.email = :email AND s.status = 'ACTIVE'")
    Supplier findByEmailActive(@Param("email") String email);

    @Query(value = "SELECT s FROM Supplier s " +
            "WHERE (LOWER(s.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "       OR LOWER(s.cnpj) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "       OR LOWER(s.email) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "AND s.status = 'ACTIVE'")
    List<Supplier> searchSupplier(@Param("search") String search);

    boolean existsSupplierById(Long id);
    boolean existsSupplierByCnpj(String cnpj);
    boolean existsSupplierByEmail(String email);
}
