package com.example.KiemTra.controller;

import com.example.KiemTra.entity.Product;
import com.example.KiemTra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images";

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "list";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("product", new Product());
        return "form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product,
                              @RequestParam("imageFile") MultipartFile file) throws IOException {
        
        // Nếu user chọn file ảnh mới
        if (!file.isEmpty()) {
            StringBuilder fileName = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
            fileName.append(file.getOriginalFilename());
            
            // Tạo thư mục nếu chưa tồn tại
            if (!Files.exists(Paths.get(UPLOAD_DIRECTORY))) {
                Files.createDirectories(Paths.get(UPLOAD_DIRECTORY));
            }
            
            Files.write(fileNameAndPath, file.getBytes());
            product.setImage("/images/" + fileName.toString());
        } 
        // Nếu không chọn ảnh mới, giữ nguyên giá trị cũ (được bind từ hidden input)
        // Tuy nhiên, logic này cần xử lý chút ở service hoặc form nếu muốn chặt chẽ hơn
        // Ở mức độ bài test này, hidden input trong form đã giúp giữ giá trị cũ rồi.

        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
