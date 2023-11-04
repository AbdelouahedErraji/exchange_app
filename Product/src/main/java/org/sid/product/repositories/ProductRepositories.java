package org.sid.product.repositories;

import org.sid.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositories extends JpaRepository<Product, Long> {
}
