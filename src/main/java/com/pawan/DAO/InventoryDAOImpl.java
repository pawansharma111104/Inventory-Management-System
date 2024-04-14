package com.pawan.DAO;

import com.pawan.model.Inventory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InventoryDAOImpl implements InventoryDAO {

    private final JdbcTemplate jdbcTemplate;

    public InventoryDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Inventory> getAllInventories() {
        String sql = "SELECT * FROM Inventory";
        return jdbcTemplate.query(sql, new InventoryRowMapper());
    }

    @Override
    public Inventory getInventoryByProductId(int pid) {
        String sql = "SELECT * FROM Inventory WHERE pid = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{pid}, new InventoryRowMapper());
    }

    @Override
    public void addInventory(Inventory inventory) {
        String sql = "INSERT INTO Inventory (pid, Quantity_In_Stock, Reorder_quantity, Reorder_level) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, inventory.getPid(), inventory.getQuantityInStock(), inventory.getReorderQuantity(), inventory.getReorderLevel());
    }

    @Override
    public void updateInventory(Inventory inventory) {
        String sql = "UPDATE Inventory SET Quantity_In_Stock = ?, Reorder_quantity = ?, Reorder_level = ? WHERE pid = ?";
        jdbcTemplate.update(sql, inventory.getQuantityInStock(), inventory.getReorderQuantity(), inventory.getReorderLevel(), inventory.getPid());
    }

    @Override
    public void deleteInventory(int pid) {
        String sql = "DELETE FROM Inventory WHERE pid = ?";
        jdbcTemplate.update(sql, pid);
    }

    @Override
    public List<Inventory> getInventoriesSortedByQuantityInStock() {
        String sql = "SELECT * FROM Inventory ORDER BY Quantity_In_Stock";
        return jdbcTemplate.query(sql, new InventoryRowMapper());
    }

    private static class InventoryRowMapper implements RowMapper<Inventory> {
        @Override
        public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
            Inventory inventory = new Inventory();
            inventory.setPid(rs.getInt("pid"));
            inventory.setQuantityInStock(rs.getInt("Quantity_In_Stock"));
            inventory.setReorderQuantity(rs.getInt("Reorder_quantity"));
            inventory.setReorderLevel(rs.getInt("Reorder_level"));
            return inventory;
        }
    }
}
