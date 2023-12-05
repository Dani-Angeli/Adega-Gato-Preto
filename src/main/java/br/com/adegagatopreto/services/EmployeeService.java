package br.com.adegagatopreto.services;

import br.com.adegagatopreto.data.vo.v1.EmployeeVO;
import br.com.adegagatopreto.enums.ActiveStatus;
import br.com.adegagatopreto.exceptions.InvalidRequestException;
import br.com.adegagatopreto.exceptions.ResourceNotFoundException;
import br.com.adegagatopreto.mapper.GatoPretoMapper;
import br.com.adegagatopreto.model.Employee;
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

        if(checkExistingEmployee(employee)) {
            var entity = employeeRepository.findByCpf(employee.getCpf());
            entity.setStatus(ActiveStatus.ACTIVE);
            entity.setUsername(employee.getUsername());
            entity.setPassword(employee.getPassword());
            entity.setRole(employee.getRole());
            entity.setName(employee.getName());
            entity.setEmail(employee.getEmail());
            entity.setPhone(employee.getPhone());
            entity.setCep(employee.getCep());
            entity.setAddress(employee.getAddress());
            var vo = GatoPretoMapper.parseObject(employeeRepository.save(entity), EmployeeVO.class);
            return vo;
        }else {
            var entity = GatoPretoMapper.parseObject(employee, Employee.class);
            entity.setStatus(ActiveStatus.ACTIVE);
            var vo = GatoPretoMapper.parseObject(employeeRepository.save(entity), EmployeeVO.class);
            return vo;
        }
    }

    public EmployeeVO updateEmployee(EmployeeVO employee) {
        logger.info("Updating requested Employee!");

        try {
            var entity = employeeRepository.findByIdActive(employee.getId());
            entity.setUsername(employee.getUsername());
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
        } catch (Exception e) {
            throw new ResourceNotFoundException("ERROR: No records found for this ID!");
        }
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

    public Boolean checkExistingEmployee(EmployeeVO employee) {
        var entity = employeeRepository.findByCpf(employee.getCpf());
        if(entity != null) {
            if((entity.getStatus().toString().compareTo("INACTIVE")) != 0) {
                throw new InvalidRequestException("ERROR: Employee already exists!");
            }
            return true;
        } return false;
    }
}
