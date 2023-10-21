package br.com.adegagatopreto.services;

import br.com.adegagatopreto.data.vo.v1.ProductVO;
import br.com.adegagatopreto.enums.ActiveStatus;
import br.com.adegagatopreto.exceptions.InvalidRequestException;
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

    public ProductVO createProduct(ProductVO product) {
        logger.info("Creating a Product!");

        if(checkExistingProduct(product)) {
            var entity = productRepository.findByBarcode(product.getBarcode());
            entity.setStatus(ActiveStatus.ACTIVE);
            entity.setName(product.getName());
            entity.setType(product.getType());
            entity.setSize(product.getSize());
            entity.setBuyValue(product.getBuyValue());
            entity.setSellValue(product.getSellValue());
            entity.setQuantity(product.getQuantity());
            var vo = GatoPretoMapper.parseObject(productRepository.save(entity), ProductVO.class);
            return vo;
        }else {
            var entity = GatoPretoMapper.parseObject(product, Product.class);
            entity.setStatus(ActiveStatus.ACTIVE);
            var vo = GatoPretoMapper.parseObject(productRepository.save(entity), ProductVO.class);
            return vo;
        }
    }

    public ProductVO updateProduct(ProductVO product) {
        logger.info("Updating requested Product!");

        try {
            var entity = productRepository.findByIdActive(product.getId());

            entity.setBarcode(product.getBarcode());
            entity.setName(product.getName());
            entity.setType(product.getType());
            entity.setSize(product.getSize());
            entity.setBuyValue(product.getBuyValue());
            entity.setSellValue(product.getSellValue());
            entity.setQuantity(product.getQuantity());

            var vo = GatoPretoMapper.parseObject(productRepository.save(entity), ProductVO.class);
            return vo;
        } catch (Exception e){
            throw new ResourceNotFoundException("ERROR: No records found for this ID!");
        }
    }

    public void deleteProduct(Long id) {
        logger.info("Deleting requested Product!");

        try {
            var entity = productRepository.findByIdActive(id);
            entity.setStatus(ActiveStatus.INACTIVE);

            var vo = GatoPretoMapper.parseObject(productRepository.save(entity), ProductVO.class);
        } catch (Exception e){
            throw new ResourceNotFoundException("ERROR: No records found for this ID!");
        }
    }

    public Boolean checkExistingProduct(ProductVO product) {
        var entity = GatoPretoMapper.parseObject(product, Product.class);
        var entityCheck = productRepository.findByBarcode(entity.getBarcode());
        if(entityCheck!=null) {
            if((entityCheck.getStatus().toString().compareTo("INACTIVE")) != 0) {
                throw new InvalidRequestException("ERROR: Product already exists!");
            }
            return true;
        }return false;
    }
}

