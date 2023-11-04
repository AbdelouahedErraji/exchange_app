package org.sid.product.services;

import org.sid.product.models.ProductRequest;
import org.sid.product.models.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse getOneProduct(Long id);
    ProductResponse AddProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts();
    ProductResponse UpdateProduct(ProductRequest productRequest, Long id);
    void deleteProduct(Long id);
 }
