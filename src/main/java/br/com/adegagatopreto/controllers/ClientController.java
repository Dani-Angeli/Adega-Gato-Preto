package br.com.adegagatopreto.controllers;

import br.com.adegagatopreto.data.vo.v1.ClientVO;
import br.com.adegagatopreto.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientVO> findAll() {
        return clientService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientVO findById(@PathVariable(value = "id") Long id) {
        return clientService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClientVO createClient(@RequestBody ClientVO client) {
        return clientService.createClient(client);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientVO updateClient(@RequestBody ClientVO client) {
        return clientService.updateClient(client);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
