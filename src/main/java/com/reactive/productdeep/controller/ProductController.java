package com.reactive.productdeep.controller;

import com.reactive.productdeep.client.DummyJsonClient;
import com.reactive.productdeep.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final DummyJsonClient dummyJsonClient;

   public Mono<ProductResponse> getAllProducts( @RequestParam Integer limit, @RequestParam Integer skip){
       return dummyJsonClient.getProducts(limit,skip);
   }
}
