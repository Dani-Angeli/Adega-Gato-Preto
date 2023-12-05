package br.com.adegagatopreto.services;

import br.com.adegagatopreto.data.vo.v1.SupplierVO;
import br.com.adegagatopreto.enums.ActiveStatus;
import br.com.adegagatopreto.exceptions.InvalidRequestException;
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

        return GatoPretoMapper.parseListObjects(supplierRepository.findAllActive(), SupplierVO.class);
    }

    public SupplierVO findById(Long id) {
        logger.info("Finding requested Supplier!");

        try {
            var entity = supplierRepository.findByIdActive(id);
            return GatoPretoMapper.parseObject(entity, SupplierVO.class);
        } catch (Exception e) {
            throw new ResourceNotFoundException("ERROR: No records found for this ID!");
        }
    }

    public SupplierVO createSupplier(SupplierVO supplier) {
        logger.info("Creating a Supplier!");

        if(checkExistingSupplier(supplier)) {
            var entity = supplierRepository.findByCnpj(supplier.getCnpj());
            entity.setStatus(ActiveStatus.ACTIVE);
            entity.setName(supplier.getName());
            entity.setEmail(supplier.getEmail());
            entity.setPhone(supplier.getPhone());
            entity.setCep(supplier.getCep());
            entity.setAddress(supplier.getAddress());
            var vo = GatoPretoMapper.parseObject(supplierRepository.save(entity), SupplierVO.class);
            return vo;
        }else {
            var entity = GatoPretoMapper.parseObject(supplier, Supplier.class);
            entity.setStatus(ActiveStatus.ACTIVE);
            var vo = GatoPretoMapper.parseObject(supplierRepository.save(entity), SupplierVO.class);
            return vo;
        }
    }

    public SupplierVO updateSupplier(SupplierVO supplier) {
        logger.info("Updating requested Supplier!");

        try {
            var entity = supplierRepository.findByIdActive(supplier.getId());
            entity.setName(supplier.getName());
            entity.setCnpj(supplier.getCnpj());
            entity.setEmail(supplier.getEmail());
            entity.setPhone(supplier.getPhone());
            entity.setCep(supplier.getCep());
            entity.setAddress(supplier.getAddress());

            var vo = GatoPretoMapper.parseObject(supplierRepository.save(entity), SupplierVO.class);
            return vo;
        } catch (Exception e) {
            throw new ResourceNotFoundException("ERROR: No records found for this ID!");
        }
    }

    public void deleteSupplier(Long id) {
        logger.info("Deleting requested Supplier!");

        try {
            var entity = supplierRepository.findByIdActive(id);
            entity.setStatus(ActiveStatus.INACTIVE);
            supplierRepository.save(entity);
        } catch (Exception e) {
            throw new ResourceNotFoundException("ERROR: No records found for this ID!");
        }
    }

    public Boolean checkExistingSupplier(SupplierVO supplier) {
        var entity = supplierRepository.findByCnpj(supplier.getCnpj());
        if(entity!=null) {
            if((entity.getStatus().toString().compareTo("INACTIVE")) != 0) {
                throw new InvalidRequestException("ERROR: Supplier already exists!");
            }
            return true;
        }return false;
    }
}
