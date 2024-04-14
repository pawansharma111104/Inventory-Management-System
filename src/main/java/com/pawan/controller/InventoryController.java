package com.pawan.controller;

import com.pawan.DAO.InventoryDAO;
import com.pawan.model.Inventory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryDAO inventoryDAO;

    public InventoryController(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    @GetMapping
    public List<Inventory> getAllInventories() {
        return inventoryDAO.getAllInventories();
    }

    @GetMapping("/{pid}")
    public Inventory getInventoryByProductId(@PathVariable int pid) {
        return inventoryDAO.getInventoryByProductId(pid);
    }

    @PostMapping
    public void addInventory(@RequestBody Inventory inventory) {
        inventoryDAO.addInventory(inventory);
    }

    @PutMapping("/{pid}")
    public void updateInventory(@PathVariable int pid, @RequestBody Inventory inventory) {
        inventory.setPid(pid);
        inventoryDAO.updateInventory(inventory);
    }

    @DeleteMapping("/{pid}")
    public void deleteInventory(@PathVariable int pid) {
        inventoryDAO.deleteInventory(pid);
    }

    @GetMapping("/sorted-by-quantity")
    public List<Inventory> getInventoriesSortedByQuantityInStock() {
        return inventoryDAO.getInventoriesSortedByQuantityInStock();
    }
}
