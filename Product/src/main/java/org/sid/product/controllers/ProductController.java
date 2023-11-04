package org.sid.product.controllers;

import org.sid.product.models.ProductRequest;
import org.sid.product.models.ProductResponse;
import org.sid.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    ProductResponse getOneProduct(@PathVariable("id") Long id) {
        return productService.getOneProduct(id);
    }
    @PostMapping("/add")
    ProductResponse AddProduct(@RequestBody ProductRequest productRequest) {
        return productService.AddProduct(productRequest);
    }
    @GetMapping("/all")
    List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
    @PutMapping("/update/{id}")
    ProductResponse UpdateProduct(@RequestBody ProductRequest productRequest, @PathVariable("id") Long id) {
        return productService.UpdateProduct(productRequest, id);
    }
    @DeleteMapping("/delete/{id}")
    void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}
