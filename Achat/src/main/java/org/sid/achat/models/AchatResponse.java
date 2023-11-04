package org.sid.achat.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.achat.entities.Achat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AchatResponse {
    private Long id;
    private Date dateAchat;
    private String currency;
    private Double totalPrice;
    private List<ProductResponse> products = new ArrayList<>();

    public AchatResponse(Achat achat, List<ProductResponse> productResponses) {
        this.id = achat.getId();
        this.dateAchat = achat.getDateAchat();
        this.currency = achat.getCurrency();
        this.totalPrice = achat.getTotalPrice();
        this.products = productResponses;
    }
}
