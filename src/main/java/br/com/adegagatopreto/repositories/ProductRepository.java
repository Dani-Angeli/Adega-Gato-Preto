package br.com.adegagatopreto.repositories;

import br.com.adegagatopreto.model.Client;
import br.com.adegagatopreto.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM adega_gato_preto.product WHERE product.status = 'ACTIVE'", nativeQuery = true)
    List<Product> findAllActive();

    @Query(value = "SELECT * FROM adega_gato_preto.product WHERE product.id = ? AND product.status = 'ACTIVE'", nativeQuery = true)
    Product findByIdActive(Long id);

    @Query(value = "SELECT * FROM adega_gato_preto.product WHERE product.barcode = ?", nativeQuery = true)
    Product findByBarcode(String barcode);

    @Query(value = "SELECT * FROM adega_gato_preto.product WHERE product.cpf = ? AND client.status = 'ACTIVE'", nativeQuery = true)
    Client findByBarcodeActive(String barcode);

    boolean existsProductById(Long id);
    boolean existsProductByBarcode(String barcode);
}
