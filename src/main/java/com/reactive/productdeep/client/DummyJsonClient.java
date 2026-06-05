package com.reactive.productdeep.client;

import com.reactive.productdeep.config.WebClientConfig;
import com.reactive.productdeep.dto.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class DummyJsonClient {

    private final WebClient webClient;

    public Mono<ProductResponse> getProducts(Integer limit, Integer skip){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                            .path("/products")
                            .queryParamIfPresent("limit", Optional.ofNullable(limit))
                            .queryParamIfPresent("skip", Optional.ofNullable(skip))
                            .build()
                )
                .retrieve()
                .bodyToMono(ProductResponse.class);


    }
}
