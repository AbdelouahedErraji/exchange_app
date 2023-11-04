package org.sid.achat.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AchatRequest {
    private Date dateAchat;
    private String currency;
    private List<Long> products = new ArrayList<>();
}
