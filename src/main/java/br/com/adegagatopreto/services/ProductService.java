package br.com.adegagatopreto.services;

import br.com.adegagatopreto.data.vo.v1.ProductVO;
import br.com.adegagatopreto.exceptions.ResourceNotFoundException;
import br.com.adegagatopreto.mapper.GatoPretoMapper;
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

        return GatoPretoMapper.parseListObjects(productRepository.findAll(), ProductVO.class) ;
    }

    public ProductVO findById(Long id) {
        logger.info("Finding requested Product!");

        var entity = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ERROR: No records found for this ID!"));
        return GatoPretoMapper.parseObject(entity, ProductVO.class);
    }

    public ProductVO createProduct(ProductVO product) {
        logger.info("Creating a Product!");

        var entity = GatoPretoMapper.parseObject(product, Product.class);
        var vo = GatoPretoMapper.parseObject(productRepository.save(entity), ProductVO.class);
        return vo;
    }

    public ProductVO updateProduct(ProductVO product) {
        logger.info("Updating requested Product!");

        var entity = productRepository.findById(product.getId())
                .orElseThrow(() -> new ResourceNotFoundException("ERROR: No records found for this ID!"));

        entity.setName(product.getName());
        entity.setType(product.getType());
        entity.setSize(product.getSize());
        entity.setBuyValue(product.getBuyValue());
        entity.setSellValue(product.getSellValue());
        entity.setQuantity(product.getQuantity());

        var vo = GatoPretoMapper.parseObject(productRepository.save(entity), ProductVO.class);

        return vo;
    }

    public void deleteProduct(Long id) {
        logger.info("Deleting requested Product!");

        var entity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR: No records found for this ID!"));

        productRepository.delete(entity);
    }
}

