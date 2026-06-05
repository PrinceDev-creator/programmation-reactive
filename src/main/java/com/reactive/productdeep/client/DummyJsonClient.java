package com.reactive.productdeep.client;

import com.reactive.productdeep.dto.ProductResponse;
import com.reactive.productdeep.exceptions.ClientException;
import com.reactive.productdeep.exceptions.ServerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.logging.Logger;

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
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new ClientException("Dummy Json API returned 4xx error")))
                .onStatus(HttpStatusCode::is5xxServerError,
                        clientResponse -> Mono.error(new ServerException("An error occured in server with 5xx error")))
                .bodyToMono(ProductResponse.class)
                .doOnSuccess(response -> {
                    assert response != null;
                    log.info("Retrieved {} products", response.getProducts().size());
                })
                .doOnError(error -> log.error("Error while calli ng Dummy Json API", error));


    }
}
