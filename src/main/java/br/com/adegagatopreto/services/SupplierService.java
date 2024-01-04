package br.com.adegagatopreto.services;

import br.com.adegagatopreto.data.vo.v1.ClientVO;
import br.com.adegagatopreto.data.vo.v1.SupplierVO;
import br.com.adegagatopreto.enums.ActiveStatus;
import br.com.adegagatopreto.exceptions.InvalidRequestException;
import br.com.adegagatopreto.exceptions.ResourceNotFoundException;
import br.com.adegagatopreto.mapper.GatoPretoMapper;
import br.com.adegagatopreto.model.Client;
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

    public List<SupplierVO> searchSupplier(String keyword) {
        List<Supplier> searchList = supplierRepository.searchSupplier(keyword);
        if (!searchList.isEmpty()) {
            return GatoPretoMapper.parseListObjects(searchList, SupplierVO.class);
        } else {
            throw new ResourceNotFoundException("ERROR: No records found for this Supplier!");
        }
    }

    public SupplierVO createSupplier(SupplierVO supplier) {
        logger.info("Creating a Supplier!");

        if (supplierRepository.existsSupplierByCnpj(supplier.getCnpj())) {
            return validateInactiveExistingCnpj(supplier);
        } else if (supplierRepository.existsSupplierByEmail(supplier.getEmail())) {
            return validateInactiveExistingEmail(supplier);
        } else {
            return createNewSupplier(supplier);
        }
    }

    public SupplierVO updateSupplier(SupplierVO supplier) {
        logger.info("Updating requested Supplier!");

        var entity = validateActiveId(supplier.getId());
        validateUniqueSupplier(supplier, entity);

        return activateAndUpdateSupplier(entity, supplier);
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

    private SupplierVO validateInactiveExistingCnpj(SupplierVO supplier) {
        var entity = supplierRepository.findByCnpj(supplier.getCnpj());

        if (entity.getStatus() == ActiveStatus.INACTIVE) {
            return inactiveCnpjHandler(entity, supplier);
        } else {
            throw new InvalidRequestException("ERROR: This CNPJ is already registered!");
        }
    }

    private SupplierVO inactiveCnpjHandler(Supplier entity, SupplierVO supplier) {
        if (supplierRepository.existsSupplierByEmail(supplier.getEmail())) {
            return validateExistingEmailForInactiveCnpj(entity, supplier);
        } else {
            return activateAndUpdateSupplier(entity, supplier);
        }
    }

    private SupplierVO validateExistingEmailForInactiveCnpj(Supplier entity, SupplierVO supplier) {
        var entity2 = supplierRepository.findByEmail(supplier.getEmail());

        if (entity.getId() != entity2.getId()) {
            throw new InvalidRequestException("ERROR: This E-mail is already registered!");
        } else {
            return activateAndUpdateSupplier(entity, supplier);
        }
    }

    private SupplierVO validateInactiveExistingEmail(SupplierVO supplier) {
        var entity = supplierRepository.findByEmail(supplier.getEmail());

        if (entity.getStatus() == ActiveStatus.INACTIVE) {
            return inactiveEmailHandler(entity, supplier);
        } else {
            throw new InvalidRequestException("ERROR: This E-mail is already registered!");
        }
    }

    private SupplierVO inactiveEmailHandler(Supplier entity, SupplierVO supplier) {
        if (supplierRepository.existsSupplierByCnpj(supplier.getCnpj())) {
            return validateExistingCnpjForInactiveEmail(entity, supplier);
        } else {
            return activateAndUpdateSupplier(entity, supplier);
        }
    }

    private SupplierVO validateExistingCnpjForInactiveEmail(Supplier entity, SupplierVO supplier) {
        var entity2 = supplierRepository.findByCnpj(supplier.getCnpj());

        if (entity.getId() != entity2.getId()) {
            throw new InvalidRequestException("ERROR: This CNPJ is already registered!");
        } else {
            return activateAndUpdateSupplier(entity, supplier);
        }
    }

    private SupplierVO activateAndUpdateSupplier(Supplier entity, SupplierVO supplier) {
        entity.setStatus(ActiveStatus.ACTIVE);
        entity.setName(supplier.getName());
        entity.setCnpj((supplier.getCnpj()));
        entity.setEmail(supplier.getEmail());
        entity.setPhone(supplier.getPhone());
        entity.setCep(supplier.getCep());
        entity.setAddress(supplier.getAddress());

        var vo = GatoPretoMapper.parseObject(supplierRepository.save(entity), SupplierVO.class);
        return vo;
    }

    private SupplierVO createNewSupplier(SupplierVO supplier) {
        var entity = GatoPretoMapper.parseObject(supplier, Supplier.class);
        entity.setStatus(ActiveStatus.ACTIVE);

        var vo = GatoPretoMapper.parseObject(supplierRepository.save(entity), SupplierVO.class);
        return vo;
    }

    private Supplier validateActiveId(Long id) {
        var entity = supplierRepository.findByIdActive(id);
        if (entity == null) {
            throw new ResourceNotFoundException("ERROR: No records found for this ID!");
        }
        return entity;
    }

    private void validateUniqueSupplier(SupplierVO supplier, Supplier entity) {
        if (supplierRepository.existsSupplierByCnpj(supplier.getCnpj())) {
            validateUniqueCnpj(supplier, entity);
        }

        if (supplierRepository.existsSupplierByEmail(supplier.getEmail())) {
            validateUniqueEmail(supplier, entity);
        }
    }

    private void validateUniqueCnpj(SupplierVO supplier, Supplier entity) {
        var checkCnpj = supplierRepository.findByCnpj(supplier.getCnpj());
        if (!entity.getId().equals(checkCnpj.getId())) {
            throw new InvalidRequestException("ERROR: This CPF is already registered!");
        }
    }

    private void validateUniqueEmail(SupplierVO supplier, Supplier entity) {
        var checkEmail = supplierRepository.findByEmail(supplier.getEmail());
        if (!entity.getId().equals(checkEmail.getId())) {
            throw new InvalidRequestException("ERROR: This E-mail is already registered!");
        }
    }
}
