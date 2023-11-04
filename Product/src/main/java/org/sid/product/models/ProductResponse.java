package org.sid.product.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.product.entities.Product;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String title;
    private Double priceInEuro;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.priceInEuro = product.getPriceInEuro();
    }
}
