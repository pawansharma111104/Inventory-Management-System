package com.pawan.DAO;

import com.pawan.model.Payment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PaymentDAOImpl implements PaymentDAO {

    private final JdbcTemplate jdbcTemplate;

    public PaymentDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Payment> getAllPayments() {
        String sql = "SELECT * FROM Payment";
        return jdbcTemplate.query(sql, new PaymentRowMapper());
    }

    @Override
    public Payment getPaymentById(int payment_id) {
        String sql = "SELECT * FROM Payment WHERE Payment_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{payment_id}, new PaymentRowMapper());
    }

    @Override
    public void addPayment(Payment payment) {
        String sql = "INSERT INTO Payment (Transaction_date, cust_id, order_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, payment.getTransaction_date(), payment.getCust_id(), payment.getOrder_id());
    }

    @Override
    public void updatePayment(Payment payment) {
        String sql = "UPDATE Payment SET Transaction_date = ?, cust_id = ?, order_id = ? WHERE Payment_id = ?";
        jdbcTemplate.update(sql, payment.getTransaction_date(), payment.getCust_id(), payment.getOrder_id(), payment.getPayment_id());
    }

    @Override
    public void deletePayment(int payment_id) {
        String sql = "DELETE FROM Payment WHERE Payment_id = ?";
        jdbcTemplate.update(sql, payment_id);
    }

    private static class PaymentRowMapper implements RowMapper<Payment> {
        @Override
        public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Payment payment = new Payment();
            payment.setPayment_id(rs.getInt("Payment_id"));
            payment.setTransaction_date(rs.getDate("Transaction_date"));
            payment.setCust_id(rs.getInt("cust_id"));
            payment.setOrder_id(rs.getInt("order_id"));
            return payment;
        }
    }
}
