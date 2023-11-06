package org.sid.achat.models;

import lombok.Getter;

import java.util.Map;

@Getter
public class ExchangeRateData {
    private Map<String, Double> rates;

}
