package com.pawan.DAO;

import com.pawan.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    private final JdbcTemplate jdbcTemplate;

    public OrderDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM Orders";
        return jdbcTemplate.query(sql, new OrderRowMapper());
    }

    @Override
    public Order getOrderById(int id) {
        String sql = "SELECT * FROM Orders WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new OrderRowMapper());
    }

    @Override
    public void addOrder(Order order) {
        String sql = "INSERT INTO Orders (order_date, status, cust_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, order.getOrder_date(), order.getStatus(), order.getCust_id());
    }

    @Override
    public void updateOrder(Order order) {
        String sql = "UPDATE Orders SET order_date = ?, status = ?, cust_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, order.getOrder_date(), order.getStatus(), order.getCust_id(), order.getId());
    }

    @Override
    public void deleteOrder(int id) {
        String sql = "DELETE FROM Orders WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Order> getOrdersSortedByDate() {
        String sql = "SELECT * FROM Orders ORDER BY order_date";
        return jdbcTemplate.query(sql, new OrderRowMapper());
    }

    private static class OrderRowMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setOrder_date(rs.getDate("order_date"));
            order.setStatus(rs.getString("status"));
            order.setCust_id(rs.getInt("cust_id"));
            return order;
        }
    }
}
