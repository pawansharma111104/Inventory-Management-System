
package com.pawan.DAO;

import com.pawan.model.Inventory;

import java.util.List;

public interface InventoryDAO {
    List<Inventory> getAllInventories();
    Inventory getInventoryByProductId(int pid);
    void addInventory(Inventory inventory);
    void updateInventory(Inventory inventory);
    void deleteInventory(int pid);
    List<Inventory> getInventoriesSortedByQuantityInStock();
}
