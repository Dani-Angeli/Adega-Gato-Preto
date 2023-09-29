package br.com.adegagatopreto.services;

import br.com.adegagatopreto.data.vo.v1.ClientVO;
import br.com.adegagatopreto.exceptions.ResourceNotFoundException;
import br.com.adegagatopreto.mapper.GatoPretoMapper;
import br.com.adegagatopreto.model.Client;
import br.com.adegagatopreto.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ClientService {

    private Logger logger = Logger.getLogger(ProductService.class.getName());

    @Autowired
    ClientRepository clientRepository;

    public List<ClientVO> findAll() {
        logger.info("Finding all Clients!");

        return GatoPretoMapper.parseListObjects(clientRepository.findAll(), ClientVO.class);
    }

    public ClientVO findById(Long id) {
        logger.info("Finding requested Client!");

        var entity = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ERROR: No records found for this ID!"));
        return GatoPretoMapper.parseObject(entity, ClientVO.class);
    }

    public ClientVO createClient(ClientVO client) {
        logger.info("Creating a Client!");

        var entity = GatoPretoMapper.parseObject(client, Client.class);
        var vo = GatoPretoMapper.parseObject(clientRepository.save(entity), ClientVO.class);
        return vo;
    }

    public ClientVO updateClient(ClientVO client) {
        logger.info("Updating requested Client!");

        var entity = clientRepository.findById(client.getId())
                .orElseThrow(() -> new ResourceNotFoundException("ERROR: No records found for this ID!"));

        entity.setName(client.getName());
        entity.setCpf(client.getCpf());
        entity.setEmail(client.getEmail());
        entity.setPhone(client.getPhone());
        entity.setCep(client.getCep());
        entity.setAddress(client.getAddress());

        var vo = GatoPretoMapper.parseObject(clientRepository.save(entity), ClientVO.class);
        return vo;
    }

    public void deleteClient(Long id) {
        logger.info("Deleting requested Client!");

        var entity = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ERROR: No records found for this ID!"));
        clientRepository.delete(entity);
    }

}
