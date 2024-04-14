package com.pawan.controller;


import com.pawan.DAO.ProductDao;
import com.pawan.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductDao productDAO;

    public ProductController(ProductDao productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productDAO.getProductById(id);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        productDAO.addProduct(product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable int id, @RequestBody Product product) {
        product.setP_id(id);
        productDAO.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productDAO.deleteProduct(id);
    }

    @GetMapping("/supplier/{supp_id}")
    public List<Product> getProductsBySupplier(@PathVariable int supp_id) {
        return productDAO.getProductsBySupplier(supp_id);
    }

    @GetMapping("/price-range")
    public List<Product> getProductsByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return productDAO.getProductsByPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/sorted-by-price")
    public List<Product> getProductsSortedByPrice() {
        return productDAO.getProductsSortedByPrice();
    }
}
