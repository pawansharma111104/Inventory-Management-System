
package com.pawan.DAO;

import com.pawan.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private final JdbcTemplate jdbcTemplate;

    public CustomerDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM Customer";
        return jdbcTemplate.query(sql, new CustomerRowMapper());
    }

    @Override
    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM Customer WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new CustomerRowMapper());
    }

    @Override
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO Customer (cust_name, cust_info) VALUES (?, ?)";
        jdbcTemplate.update(sql, customer.getCust_name(), customer.getCust_info());
    }

    @Override
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE Customer SET cust_name = ?, cust_info = ? WHERE id = ?";
        jdbcTemplate.update(sql, customer.getCust_name(), customer.getCust_info(), customer.getId());
    }

    @Override
    public void deleteCustomer(int id) {
        String sql = "DELETE FROM Customer WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Customer> getCustomersSortedByName() {
        String sql = "SELECT * FROM Customer ORDER BY cust_name";
        return jdbcTemplate.query(sql, new CustomerRowMapper());
    }

    private static class CustomerRowMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setCust_name(rs.getString("cust_name"));
            customer.setCust_info(rs.getString("cust_info"));
            return customer;
        }
    }
}
