package br.com.adegagatopreto.services;

import br.com.adegagatopreto.data.vo.v1.EmployeeVO;
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

        return GatoPretoMapper.parseListObjects(employeeRepository.findAll(), EmployeeVO.class);
    }

    public EmployeeVO findById(Long id) {
        logger.info("Finding requested Employee!");

        var entity = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ERROR: No records found for this ID!"));
        return GatoPretoMapper.parseObject(entity, EmployeeVO.class);
    }

    public EmployeeVO createEmployee(EmployeeVO employee) {
        logger.info("Creating an Employee!");

        var entity = GatoPretoMapper.parseObject(employee, Employee.class);
        var vo = GatoPretoMapper.parseObject(employeeRepository.save(entity), EmployeeVO.class);
        return vo;
    }

    public EmployeeVO updateEmployee(EmployeeVO employee) {
        logger.info("Updating requested Employee!");

        var entity = employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new ResourceNotFoundException("ERROR: No records found for this ID!"));
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
    }

    public void deleteEmployee(Long id) {
        logger.info("Deleting requested ID!");

        var entity = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ERROR: No records found fot this ID!"));
        employeeRepository.delete(entity);
    }
}
