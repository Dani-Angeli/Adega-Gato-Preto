package br.com.adegagatopreto.services;

import br.com.adegagatopreto.data.vo.v1.ClientVO;
import br.com.adegagatopreto.enums.ActiveStatus;
import br.com.adegagatopreto.enums.UserType;
import br.com.adegagatopreto.exceptions.InvalidRequestException;
import br.com.adegagatopreto.exceptions.ResourceNotFoundException;
import br.com.adegagatopreto.mapper.GatoPretoMapper;
import br.com.adegagatopreto.model.Client;
import br.com.adegagatopreto.repositories.ClientRepository;
import br.com.adegagatopreto.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ClientService {

    private Logger logger = Logger.getLogger(ProductService.class.getName());

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public List<ClientVO> findAll() {
        logger.info("Finding all Clients!");

        return GatoPretoMapper.parseListObjects(clientRepository.findAllActive(), ClientVO.class);
    }

    public ClientVO findById(Long id) {
        logger.info("Finding requested Client!");

        try {
            var entity = clientRepository.findByIdActive(id);
            return GatoPretoMapper.parseObject(entity, ClientVO.class);
        } catch (Exception e) {
            throw new ResourceNotFoundException("ERROR: No records found for this ID!");
        }
    }

    public ClientVO createClient(ClientVO client) {
        logger.info("Creating a Client!");

        if (clientRepository.existsClientByCpf(client.getCpf())) {
            return validateInactiveExistingCPF(client);
        } else if (clientRepository.existsClientByEmail(client.getEmail()) || employeeRepository.existsEmployeeByEmail(client.getEmail())) {
            return validateInactiveExistingEmail(client);
        } else {
            return createNewClient(client);
        }
    }

    public ClientVO updateClient(ClientVO client) {
        logger.info("Updating requested Client!");

        var entity = validateActiveId(client.getId());
        validateUniqueClient(client, entity);

        return activateAndUpdateClient(entity, client);
    }

    public void deleteClient(Long id) {
        logger.info("Deleting requested Client!");

        try {
            var entity = clientRepository.findByIdActive(id);
            entity.setStatus(ActiveStatus.INACTIVE);
            clientRepository.save(entity);
        } catch (Exception e) {
            throw new ResourceNotFoundException("ERROR: No records found for this ID!");
        }
    }

    private ClientVO validateInactiveExistingCPF(ClientVO client) {
        var entity = clientRepository.findByCpf(client.getCpf());

        if (entity.getStatus() == ActiveStatus.INACTIVE) {
            return inactiveCpfHandler(entity, client);
        } else {
            throw new InvalidRequestException("ERROR: This CPF is already registered!");
        }
    }

    private ClientVO inactiveCpfHandler(Client entity, ClientVO client) {
        if (clientRepository.existsClientByEmail(client.getEmail())) {
            return validateExistingEmailForInactiveCpf(entity, client);
        } else if (employeeRepository.existsEmployeeByEmail(client.getEmail())) {
            throw new InvalidRequestException("ERROR: This E-mail is already registered!");
        } else {
            return activateAndUpdateClient(entity, client);
        }
    }

    private ClientVO validateExistingEmailForInactiveCpf(Client entity, ClientVO client) {
        var checkClientEmail = clientRepository.findByEmail(client.getEmail());

        if (entity.getId() != checkClientEmail.getId() || employeeRepository.existsEmployeeByEmail(client.getEmail())) {
            throw new InvalidRequestException("ERROR: This E-mail is already registered!");
        } else {
            return activateAndUpdateClient(entity, client);
        }
    }

    private ClientVO validateInactiveExistingEmail(ClientVO client) {
        var entity = clientRepository.findByEmail(client.getEmail());

        if(employeeRepository.existsEmployeeByEmail(client.getEmail())) {
            throw new InvalidRequestException("ERROR: This E-mail is already registered!");
        } else if (entity.getStatus() == ActiveStatus.INACTIVE) {
            return inactiveEmailHandler(entity, client);
        } else {
            throw new InvalidRequestException("ERROR: This E-mail is already registered!");
        }
    }

    private ClientVO inactiveEmailHandler(Client entity, ClientVO client) {
        if (clientRepository.existsClientByCpf(client.getCpf())) {
            return validateExistingCpfForInactiveEmail(entity, client);
        } else {
            return activateAndUpdateClient(entity, client);
        }
    }

    private ClientVO validateExistingCpfForInactiveEmail(Client entity, ClientVO client) {
        var entity2 = clientRepository.findByCpf(client.getCpf());

        if (entity.getId() != entity2.getId()) {
            throw new InvalidRequestException("ERROR: This CPF is already registered!");
        } else {
            return activateAndUpdateClient(entity, client);
        }
    }

    private ClientVO activateAndUpdateClient(Client entity, ClientVO client) {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        entity.setStatus(ActiveStatus.ACTIVE);
        entity.setPassword(bCrypt.encode(client.getPassword()));
        entity.setName(client.getName());
        entity.setCpf((client.getCpf()));
        entity.setEmail(client.getEmail());
        entity.setPhone(client.getPhone());
        entity.setCep(client.getCep());
        entity.setAddress(client.getAddress());

        var vo = GatoPretoMapper.parseObject(clientRepository.save(entity), ClientVO.class);
        return vo;
    }

    private ClientVO createNewClient(ClientVO client) {
        var entity = GatoPretoMapper.parseObject(client, Client.class);
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        entity.setStatus(ActiveStatus.ACTIVE);
        entity.setType(UserType.CLIENT);
        entity.setPassword(bCrypt.encode(client.getPassword()));

        var vo = GatoPretoMapper.parseObject(clientRepository.save(entity), ClientVO.class);
        return vo;
    }

    private Client validateActiveId(Long id) {
        var entity = clientRepository.findByIdActive(id);
        if (entity == null) {
            throw new ResourceNotFoundException("ERROR: No records found for this ID!");
        }
        return entity;
    }

    private void validateUniqueClient(ClientVO client, Client entity) {
        if (clientRepository.existsClientByCpf(client.getCpf())) {
            validateUniqueCpf(client, entity);
        }

        if (clientRepository.existsClientByEmail(client.getEmail())) {
            validateUniqueEmail(client, entity);
        }

        if (employeeRepository.existsEmployeeByEmail(client.getEmail())) {
            throw new InvalidRequestException("ERROR: This E-mail is already registered!");
        }
    }

    private void validateUniqueCpf(ClientVO client, Client entity) {
        var checkCpf = clientRepository.findByCpf(client.getCpf());
        if (!entity.getId().equals(checkCpf.getId())) {
            throw new InvalidRequestException("ERROR: This CPF is already registered!");
        }
    }

    private void validateUniqueEmail(ClientVO client, Client entity) {
        var checkEmail = clientRepository.findByEmail(client.getEmail());
        if (!entity.getId().equals(checkEmail.getId())) {
            throw new InvalidRequestException("ERROR: This E-mail is already registered!");
        }
    }
}
