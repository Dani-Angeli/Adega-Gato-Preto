package br.com.adegagatopreto.repositories;

import br.com.adegagatopreto.model.Client;
import br.com.adegagatopreto.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT p FROM Product p WHERE p.status = 'ACTIVE'")
    List<Product> findAllActive();

    @Query(value = "SELECT p FROM Product p WHERE p.id = :id AND p.status = 'ACTIVE'")
    Product findByIdActive(@Param("id") Long id);

    @Query(value = "SELECT p FROM Product p WHERE p.barcode = :barcode")
    Product findByBarcode(@Param("barcode") String barcode);

    @Query(value = "SELECT p FROM Product p WHERE p.barcode = :barcode AND p.status = 'ACTIVE'")
    Client findByBarcodeActive(@Param("barcode") String barcode);

    @Query(value = "SELECT p FROM Product p " +
            "WHERE (LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "       OR LOWER(p.barcode) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "AND p.status = 'ACTIVE'")
    List<Product> searchProduct(@Param("search") String search);

    boolean existsProductById(Long id);
    boolean existsProductByBarcode(String barcode);
}
