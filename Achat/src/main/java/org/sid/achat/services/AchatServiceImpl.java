package org.sid.achat.services;

import jakarta.persistence.EntityNotFoundException;
import org.sid.achat.entities.Achat;
import org.sid.achat.models.AchatRequest;
import org.sid.achat.models.AchatResponse;
import org.sid.achat.models.ExchangeRateData;
import org.sid.achat.models.ProductResponse;
import org.sid.achat.repositories.AchatRepositories;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AchatServiceImpl implements AchatService{
    private final AchatRepositories achatRepositories;
    private final WebClient webClient;
    public AchatServiceImpl(AchatRepositories achatRepositories, WebClient webClient) {
        this.achatRepositories = achatRepositories;
        this.webClient = webClient;
    }
    @Override
    public AchatResponse addAchat(AchatRequest achatRequest) {
        String uri = "https://open.er-api.com/v6/latest/EUR?apikey=8a450dafaaa14834daf2230b";
        RestTemplate restTemplate = new RestTemplate();
        ExchangeRateData exchangeRateData = restTemplate.getForObject(uri, ExchangeRateData.class);
        assert exchangeRateData != null;
        Double exchangeRate = exchangeRateData.getRates().get(achatRequest.getCurrency());

        Achat achat = new Achat(null, new Date(), achatRequest.getCurrency(), 0.0, achatRequest.getProducts());
        List<ProductResponse> productResponses = new ArrayList<>();
        achat.getProducts().forEach(productId -> {
            Mono<ProductResponse> productResponseMono = webClient.get()
                    .uri("http://localhost:8080/api/product/{id}", productId)
                    .retrieve()
                    .bodyToMono(ProductResponse.class);
            ProductResponse pr = productResponseMono.share().block();
            productResponses.add(pr);
            assert pr != null;
            achat.setTotalPrice(achat.getTotalPrice() + pr.getPriceInEuro());
        });
        achat.setTotalPrice(achat.getTotalPrice() * exchangeRate);
        achatRepositories.save(achat);
        return new AchatResponse(achat, productResponses);
    }

    @Override
    public AchatResponse getOneAchat(Long id) {
        Optional<Achat> achatOptional = achatRepositories.findById(id);
        if(achatOptional.isEmpty()) throw new EntityNotFoundException("Not found");
        Achat achat = achatOptional.get();
        List<ProductResponse> productResponses = new ArrayList<>();
        achat.getProducts().forEach(productId -> {
            Mono<ProductResponse> productResponseMono = webClient.get()
                    .uri("http://localhost:8080/api/product/{id}", productId)
                    .retrieve()
                    .bodyToMono(ProductResponse.class);
            productResponses.add(productResponseMono.share().block());
        });
        AchatResponse achatResponse = new AchatResponse(achat, productResponses);
        achatResponse.setProducts(productResponses);
        return achatResponse;
    }
}
