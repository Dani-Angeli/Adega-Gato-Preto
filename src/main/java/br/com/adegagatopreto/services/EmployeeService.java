package br.com.adegagatopreto.services;

import br.com.adegagatopreto.data.vo.v1.EmployeeVO;
import br.com.adegagatopreto.enums.ActiveStatus;
import br.com.adegagatopreto.enums.UserType;
import br.com.adegagatopreto.exceptions.InvalidRequestException;
import br.com.adegagatopreto.exceptions.ResourceNotFoundException;
import br.com.adegagatopreto.mapper.GatoPretoMapper;
import br.com.adegagatopreto.model.Employee;
import br.com.adegagatopreto.repositories.ClientRepository;
import br.com.adegagatopreto.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class EmployeeService {

    private Logger logger = Logger.getLogger(ProductService.class.getName());

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ClientRepository clientRepository;

    public List<EmployeeVO> findAll() {
        logger.info("Finding all Employees!");

        return GatoPretoMapper.parseListObjects(employeeRepository.findAllActive(), EmployeeVO.class);
    }

    public EmployeeVO findById(Long id) {
        logger.info("Finding requested Employee!");

        try {
            var entity = employeeRepository.findByIdActive(id);
            return GatoPretoMapper.parseObject(entity, EmployeeVO.class);
        } catch (Exception e) {
            throw new ResourceNotFoundException("ERROR: No records found for this ID!");
        }
    }

    public EmployeeVO createEmployee(EmployeeVO employee) {
        logger.info("Creating an Employee!");

        if (employeeRepository.existsEmployeeByCpf(employee.getCpf())) {
            return validateInactiveExistingCPF(employee);
        } else if (employeeRepository.existsEmployeeByEmail(employee.getEmail()) || clientRepository.existsClientByEmail(employee.getEmail())) {
            return validateInactiveExistingEmail(employee);
        }else {
            return createNewEmployee(employee);
        }
    }

    public EmployeeVO updateEmployee(EmployeeVO employee) {
        logger.info("Updating requested Employee!");

        var entity = validateActiveId(employee.getId());
        validateUniqueEmployee(employee, entity);

        return activateAndUpdateEmployee(entity, employee);
    }

    public void deleteEmployee(Long id) {
        logger.info("Deleting requested ID!");

        try {
            var entity = employeeRepository.findByIdActive(id);
            entity.setStatus(ActiveStatus.INACTIVE);
            employeeRepository.save(entity);
        } catch (Exception e) {
            throw new ResourceNotFoundException("ERROR: No records found fot this ID!");
        }
    }

    private EmployeeVO validateInactiveExistingCPF(EmployeeVO employee) {
        var entity = employeeRepository.findByCpf(employee.getCpf());

        if (entity.getStatus() == ActiveStatus.INACTIVE) {
            return inactiveCpfHandler(entity, employee);
        } else {
            throw new InvalidRequestException("ERROR: This CPF is already registered!");
        }
    }

    private EmployeeVO inactiveCpfHandler(Employee entity, EmployeeVO employee) {
        if (employeeRepository.existsEmployeeByEmail(employee.getEmail())) {
            return validateExistingEmailForInactiveCpf(entity, employee);
        } else if (clientRepository.existsClientByEmail(employee.getEmail())) {
            throw new InvalidRequestException("ERROR: This E-mail is already registered!");
        } else {
            return activateAndUpdateEmployee(entity, employee);
        }
    }

    private EmployeeVO validateExistingEmailForInactiveCpf(Employee entity, EmployeeVO employee) {
        var checkEmail = employeeRepository.findByEmail(employee.getEmail());

        if (entity.getId() != checkEmail.getId() || clientRepository.existsClientByEmail(employee.getEmail())) {
            throw new InvalidRequestException("ERROR: This E-mail is already registered!");
        } else {
            return activateAndUpdateEmployee(entity, employee);
        }
    }

    private EmployeeVO validateInactiveExistingEmail(EmployeeVO employee) {
        var entity = employeeRepository.findByEmail(employee.getEmail());

        if(clientRepository.existsClientByEmail(employee.getEmail())) {
            throw new InvalidRequestException("ERROR: This E-mail is already registered!");
        } else if (entity.getStatus() == ActiveStatus.INACTIVE) {
            return inactiveEmailHandler(entity, employee);
        } else {
            throw new InvalidRequestException("ERROR: This E-mail is already registered!");
        }
    }

    private EmployeeVO inactiveEmailHandler(Employee entity, EmployeeVO employee) {
        if (employeeRepository.existsEmployeeByCpf(employee.getCpf())) {
            return validateExistingCpfForInactiveEmail(entity, employee);
        } else {
            return activateAndUpdateEmployee(entity, employee);
        }
    }

    private EmployeeVO validateExistingCpfForInactiveEmail(Employee entity, EmployeeVO employee) {
        var checkCpf = employeeRepository.findByCpf(employee.getCpf());

        if (entity.getId() != checkCpf.getId()) {
            throw new InvalidRequestException("ERROR: This CPF is already registered!");
        } else {
            return activateAndUpdateEmployee(entity, employee);
        }
    }

    private EmployeeVO activateAndUpdateEmployee(Employee entity, EmployeeVO employee) {
        entity.setStatus(ActiveStatus.ACTIVE);
        entity.setPassword(employee.getPassword());
        entity.setRole(employee.getRole());
        entity.setName(employee.getName());
        entity.setCpf(employee.getCpf());
        entity.setEmail(employee.getEmail());
        entity.setPhone(employee.getPhone());
        entity.setCep(employee.getCep());
        entity.setAddress(employee.getAddress());
        var vo = GatoPretoMapper.parseObject(employeeRepository.save(entity), EmployeeVO.class);
        return vo;
    }

    private EmployeeVO createNewEmployee(EmployeeVO employee) {
        var entity = GatoPretoMapper.parseObject(employee, Employee.class);
        entity.setStatus(ActiveStatus.ACTIVE);
        entity.setType(UserType.EMPLOYEE);

        var vo = GatoPretoMapper.parseObject(employeeRepository.save(entity), EmployeeVO.class);
        return vo;
    }

    private Employee validateActiveId(Long id) {
        var entity = employeeRepository.findByIdActive(id);
        if (entity == null) {
            throw new ResourceNotFoundException("ERROR: No records found for this ID!");
        }
        return entity;
    }

    private void validateUniqueEmployee(EmployeeVO employee, Employee entity) {
        if (employeeRepository.existsEmployeeByCpf(employee.getCpf())) {
            validateUniqueCpf(employee, entity);
        }
        if (employeeRepository.existsEmployeeByEmail(employee.getEmail())) {
            validateUniqueEmail(employee, entity);
        }
        if (clientRepository.existsClientByEmail(employee.getEmail())) {
            throw new InvalidRequestException("ERROR: This E-mail is already registered!");
        }
    }

    private void validateUniqueCpf(EmployeeVO employee, Employee entity) {
        var checkCpf = employeeRepository.findByCpf(employee.getCpf());
        if (!entity.getId().equals(checkCpf.getId())) {
            throw new InvalidRequestException("ERROR: This CPF is already registered!");
        }
    }

    private void validateUniqueEmail(EmployeeVO employee, Employee entity) {
        var checkEmail = employeeRepository.findByEmail(employee.getEmail());
        if (!entity.getId().equals(checkEmail.getId())) {
            throw new InvalidRequestException("ERROR: This E-mail is already registered!");
        }
    }

}
