package org.sid.product.services;

import jakarta.persistence.EntityNotFoundException;
import org.sid.product.entities.Product;
import org.sid.product.models.ProductRequest;
import org.sid.product.models.ProductResponse;
import org.sid.product.repositories.ProductRepositories;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class ProductServiceImpl implements ProductService{
    private ProductRepositories productRepositories;
    public ProductServiceImpl(ProductRepositories productRepositories) {
        this.productRepositories = productRepositories;
    }
    @Override
    public ProductResponse getOneProduct(Long id) {
        Optional<Product> productOptional = productRepositories.findById(id);
        if(productOptional.isEmpty()) throw new EntityNotFoundException("Product not found");
        return new ProductResponse(productOptional.get());
    }

    @Override
    public ProductResponse AddProduct(ProductRequest productRequest) {
        Product product = new Product(null, productRequest.getTitle(), productRequest.getPriceInEuro());
        productRepositories.save(product);
        return new ProductResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepositories.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();
        products.forEach(product -> {
            productResponses.add(new ProductResponse(product));
        });
        return productResponses;
    }

    @Override
    public ProductResponse UpdateProduct(ProductRequest productRequest, Long id) {
        Optional<Product> productOptional = productRepositories.findById(id);
        if(productOptional.isEmpty()) throw new EntityNotFoundException("Product not found");
        Product product = productOptional.get();
        product.setTitle(productRequest.getTitle() == null ? product.getTitle() : productRequest.getTitle());
        product.setPriceInEuro(productRequest.getPriceInEuro() == null ? product.getPriceInEuro() : productRequest.getPriceInEuro());
        productRepositories.save(product);
        return new ProductResponse(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepositories.deleteById(id);
        System.out.println("Product deleted successfully");
    }
}
