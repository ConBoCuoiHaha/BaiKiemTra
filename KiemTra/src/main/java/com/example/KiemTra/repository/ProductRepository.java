package com.example.KiemTra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.KiemTra.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}