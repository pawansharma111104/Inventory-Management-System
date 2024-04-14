package com.pawan.DAO;

import com.pawan.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM Product";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    @Override
    public Product getProductById(int p_id) {
        String sql = "SELECT * FROM Product WHERE p_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{p_id}, new ProductRowMapper());
    }

    @Override
    public void addProduct(Product product) {
        String sql = "INSERT INTO Product (p_name, price, in_stocks, supp_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getP_name(), product.getPrice(), product.getIn_stocks(), product.getSupp_id());
    }

    @Override
    public void updateProduct(Product product) {
        String sql = "UPDATE Product SET p_name = ?, price = ?, in_stocks = ?, supp_id = ? WHERE p_id = ?";
        jdbcTemplate.update(sql, product.getP_name(), product.getPrice(), product.getIn_stocks(), product.getSupp_id(), product.getP_id());
    }

    @Override
    public void deleteProduct(int p_id) {
        String sql = "DELETE FROM Product WHERE p_id = ?";
        jdbcTemplate.update(sql, p_id);
    }

    @Override
    public List<Product> getProductsBySupplier(int supp_id) {
        String sql = "SELECT * FROM Product WHERE supp_id = ?";
        return jdbcTemplate.query(sql, new Object[]{supp_id}, new ProductRowMapper());
    }

    @Override
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        String sql = "SELECT * FROM Product WHERE price BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new Object[]{minPrice, maxPrice}, new ProductRowMapper());
    }

    @Override
    public List<Product> getProductsSortedByPrice() {
        String sql = "SELECT * FROM Product ORDER BY price";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    private static class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setP_id(rs.getInt("p_id"));
            product.setP_name(rs.getString("p_name"));
            product.setPrice(rs.getDouble("price"));
            product.setIn_stocks(rs.getInt("in_stocks"));
            product.setSupp_id(rs.getInt("supp_id"));
            return product;
        }
    }
}
