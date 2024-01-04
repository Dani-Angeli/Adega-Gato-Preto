package br.com.adegagatopreto.services;

import br.com.adegagatopreto.data.vo.v1.ClientVO;
import br.com.adegagatopreto.data.vo.v1.ProductVO;
import br.com.adegagatopreto.enums.ActiveStatus;
import br.com.adegagatopreto.exceptions.InvalidRequestException;
import br.com.adegagatopreto.exceptions.ResourceNotFoundException;
import br.com.adegagatopreto.mapper.GatoPretoMapper;
import br.com.adegagatopreto.model.Client;
import br.com.adegagatopreto.model.Product;
import br.com.adegagatopreto.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ProductService {

    private Logger logger = Logger.getLogger(ProductService.class.getName());

    @Autowired
    ProductRepository productRepository;

    public List<ProductVO> findAll() {
        logger.info("Finding all Products!");

        return GatoPretoMapper.parseListObjects(productRepository.findAllActive(), ProductVO.class) ;
    }

    public ProductVO findById(Long id) {
        logger.info("Finding requested Product!");
        try {
            var entity = productRepository.findByIdActive(id);
            return GatoPretoMapper.parseObject(entity, ProductVO.class);
        } catch (Exception e){
            throw new ResourceNotFoundException("ERROR: No records found for this ID!");
        }
    }

    public List<ProductVO> searchProduct(String keyword) {
        List<Product> searchList = productRepository.searchProduct(keyword);
        if (!searchList.isEmpty()) {
            return GatoPretoMapper.parseListObjects(searchList, ProductVO.class);
        } else {
            throw new ResourceNotFoundException("ERROR: No records found for this Product!");
        }
    }

    public ProductVO createProduct(ProductVO product) {
        logger.info("Creating a Client!");

        if (productRepository.existsProductByBarcode(product.getBarcode())) {
            return validateInactiveExistingBarcode(product);
        } else {
            return createNewProduct(product);
        }
    }

    public ProductVO updateProduct(ProductVO product) {
        logger.info("Updating requested Product!");

        var entity = validateActiveId(product.getId());
        validateUniqueProduct(product, entity);

        return activateAndUpdateProduct(entity, product);
    }

    public void deleteProduct(Long id) {
        logger.info("Deleting requested Product!");

        try {
            var entity = productRepository.findByIdActive(id);
            entity.setStatus(ActiveStatus.INACTIVE);
            productRepository.save(entity);
        } catch (Exception e){
            throw new ResourceNotFoundException("ERROR: No records found for this ID!");
        }
    }

    private ProductVO validateInactiveExistingBarcode(ProductVO product) {
        var entity = productRepository.findByBarcode(product.getBarcode());

        if (entity.getStatus() == ActiveStatus.INACTIVE) {
            return activateAndUpdateProduct(entity, product);
        } else {
            throw new InvalidRequestException("ERROR: This Barcode is already registered!");
        }
    }

    private ProductVO activateAndUpdateProduct(Product entity, ProductVO product) {
        entity.setStatus(ActiveStatus.ACTIVE);
        entity.setBarcode(product.getBarcode());
        entity.setName(product.getName());
        entity.setType(product.getType());
        entity.setSize(product.getSize());
        entity.setBuyValue(product.getBuyValue());
        entity.setSellValue(product.getSellValue());
        entity.setQuantity(product.getQuantity());
        var vo = GatoPretoMapper.parseObject(productRepository.save(entity), ProductVO.class);
        return vo;
    }

    private ProductVO createNewProduct(ProductVO product) {
        var entity = GatoPretoMapper.parseObject(product, Product.class);
        entity.setStatus(ActiveStatus.ACTIVE);

        var vo = GatoPretoMapper.parseObject(productRepository.save(entity), ProductVO.class);
        return vo;
    }

    private Product validateActiveId(Long id) {
        var entity = productRepository.findByIdActive(id);
        if (entity == null) {
            throw new ResourceNotFoundException("ERROR: No records found for this ID!");
        }
        return entity;
    }

    private void validateUniqueProduct(ProductVO product, Product entity) {
        if (productRepository.existsProductByBarcode(product.getBarcode())) {
            validateUniqueBarcode(product, entity);
        }
    }

    private void validateUniqueBarcode(ProductVO product, Product entity) {
        var checkBarcode = productRepository.findByBarcode(product.getBarcode());
        if (!entity.getId().equals(checkBarcode.getId())) {
            throw new InvalidRequestException("ERROR: This Barcode is already registered!");
        }
    }
}

