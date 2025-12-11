package com.example.KiemTra.service;
import java.util.List;

import com.example.KiemTra.entity.Product;
public interface ProductService {
    List<Product> getAllProducts();
    void saveProduct(Product product);
    Product getProductById(Long id);
    void deleteProduct(Long id);
    Product findByName(String name);
    List<Product> searchProduct(String keyword);

}