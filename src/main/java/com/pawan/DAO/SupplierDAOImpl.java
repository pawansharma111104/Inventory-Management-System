package com.pawan.DAO;

import com.pawan.model.Supplier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SupplierDAOImpl implements SupplierDAO {

    private final JdbcTemplate jdbcTemplate;

    public SupplierDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        String sql = "SELECT * FROM Supplier";
        return jdbcTemplate.query(sql, new SupplierRowMapper());
    }

    @Override
    public Supplier getSupplierById(int id) {
        String sql = "SELECT * FROM Supplier WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new SupplierRowMapper());
    }

    @Override
    public void addSupplier(Supplier supplier) {
        String sql = "INSERT INTO Supplier (supp_name, contact_Info, order_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, supplier.getSupp_name(), supplier.getContact_Info(), supplier.getOrder_id());
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        String sql = "UPDATE Supplier SET supp_name = ?, contact_Info = ?, order_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, supplier.getSupp_name(), supplier.getContact_Info(), supplier.getOrder_id(), supplier.getId());
    }

    @Override
    public void deleteSupplier(int id) {
        String sql = "DELETE FROM Supplier WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Supplier> getSuppliersSortedByName() {
        String sql = "SELECT * FROM Supplier ORDER BY supp_name";
        return jdbcTemplate.query(sql, new SupplierRowMapper());
    }

    private static class SupplierRowMapper implements RowMapper<Supplier> {
        @Override
        public Supplier mapRow(ResultSet rs, int rowNum) throws SQLException {
            Supplier supplier = new Supplier();
            supplier.setId(rs.getInt("id"));
            supplier.setSupp_name(rs.getString("supp_name"));
            supplier.setContact_Info(rs.getString("contact_Info"));
            supplier.setOrder_id(rs.getInt("order_id"));
            return supplier;
        }
    }
}
