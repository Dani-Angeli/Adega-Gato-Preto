package br.com.adegagatopreto.controllers;

import br.com.adegagatopreto.data.vo.v1.ClientVO;
import br.com.adegagatopreto.data.vo.v1.SupplierVO;
import br.com.adegagatopreto.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SupplierVO> findAll() {
        return supplierService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SupplierVO findById(@PathVariable(value = "id")Long id) {
        return supplierService.findById(id);
    }

    @GetMapping("/search")
    public List<SupplierVO> searchSupplier(@RequestParam String keyword) {
        return supplierService.searchSupplier(keyword);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public SupplierVO createSupplier(@RequestBody SupplierVO supplier) {
        return supplierService.createSupplier(supplier);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SupplierVO updateSupplier(@RequestBody SupplierVO supplier) {
        return supplierService.updateSupplier(supplier);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable(value = "id") Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}
