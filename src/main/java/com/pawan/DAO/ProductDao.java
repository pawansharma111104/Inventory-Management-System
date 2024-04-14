package com.pawan.DAO;

import com.pawan.model.Product;

import java.util.List;

public interface ProductDao{
    List<Product> getAllProducts();
    Product getProductById(int p_id);
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int p_id);
    List<Product> getProductsBySupplier(int supp_id);
    List<Product> getProductsByPriceRange(double minPrice, double maxPrice);
    List<Product> getProductsSortedByPrice();
}
