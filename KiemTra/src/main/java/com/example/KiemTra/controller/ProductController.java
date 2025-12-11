package com.example.KiemTra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.KiemTra.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "list";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("product", new com.example.KiemTra.entity.Product());
        return "form";
    }

    @org.springframework.web.bind.annotation.PostMapping("/save")
    public String saveProduct(@org.springframework.web.bind.annotation.ModelAttribute("product") com.example.KiemTra.entity.Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@org.springframework.web.bind.annotation.PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

}
