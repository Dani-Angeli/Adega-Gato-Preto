package br.com.adegagatopreto.services;

import br.com.adegagatopreto.data.vo.v1.SupplierVO;
import br.com.adegagatopreto.exceptions.ResourceNotFoundException;
import br.com.adegagatopreto.mapper.GatoPretoMapper;
import br.com.adegagatopreto.model.Supplier;
import br.com.adegagatopreto.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class SupplierService {

    private Logger logger = Logger.getLogger(ProductService.class.getName());

    @Autowired
    SupplierRepository supplierRepository;

    public List<SupplierVO> findAll() {
        logger.info("Finding all Suppliers!");

        return GatoPretoMapper.parseListObjects(supplierRepository.findAll(), SupplierVO.class);
    }

    public SupplierVO findById(Long id) {
        logger.info("Finding requested Supplier!");

        var entity = supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ERROR: No records found for this ID!"));
        return GatoPretoMapper.parseObject(entity, SupplierVO.class);
    }

    public SupplierVO createSupplier(SupplierVO supplier) {
        logger.info("Creating a Supplier!");

        var entity = GatoPretoMapper.parseObject(supplier, Supplier.class);
        var vo = GatoPretoMapper.parseObject(supplierRepository.save(entity), SupplierVO.class);
        return vo;
    }

    public SupplierVO updateSupplier(SupplierVO supplier) {
        logger.info("Updating requested Supplier!");

        var entity = supplierRepository.findById(supplier.getId())
                .orElseThrow(() -> new ResourceNotFoundException("ERROR: No records found for this ID!"));
        entity.setName(supplier.getName());
        entity.setCnpj(supplier.getCnpj());
        entity.setEmail(supplier.getEmail());
        entity.setPhone(supplier.getPhone());
        entity.setCep(supplier.getCep());
        entity.setAddress(supplier.getAddress());

        var vo = GatoPretoMapper.parseObject(supplierRepository.save(entity), SupplierVO.class);
        return vo;
    }

    public void deleteSupplier(Long id) {
        logger.info("Deleting requested Supplier!");

        var entity = supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ERROR: No records found for this ID!"));
        supplierRepository.delete(entity);
    }
}
