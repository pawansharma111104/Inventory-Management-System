package com.pawan.controller;
import com.pawan.DAO.SupplierDAO;
import com.pawan.model.Supplier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierDAO supplierDAO;

    public SupplierController(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierDAO.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable int id) {
        return supplierDAO.getSupplierById(id);
    }

    @PostMapping
    public void addSupplier(@RequestBody Supplier supplier) {
        supplierDAO.addSupplier(supplier);
    }

    @PutMapping("/{id}")
    public void updateSupplier(@PathVariable int id, @RequestBody Supplier supplier) {
        supplier.setId(id);
        supplierDAO.updateSupplier(supplier);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable int id) {
        supplierDAO.deleteSupplier(id);
    }

    @GetMapping("/sorted-by-name")
    public List<Supplier> getSuppliersSortedByName() {
        return supplierDAO.getSuppliersSortedByName();
    }
}
