package com.gusta.template.controllers;

import com.gusta.template.models.vo.*;
import com.gusta.template.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/product/")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("add")
    public ProductVO addProductToStore(ProductVO vo) {
        return service.addProductToStore(vo);
    }

    @GetMapping("get-all")
    public List<ProductVO> getAllProducts() {
        return service.getAllProducts();
    }

    @PutMapping("update")
    public ProductVO updateProduct(ProductVO vo) {
        return service.updateProduct(vo);
    }

    @DeleteMapping("delete")
    public void deleteProductById(Long id) {
        service.deleteProductById(id);
    }
}
