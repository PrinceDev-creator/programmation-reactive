package com.reactive.productdeep.config;

import io.netty.channel.ChannelOption;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final static String BASE_URL = "https://dummyjson.com";
    private final static Duration TIMEOUT = Duration.ofSeconds(10);

    @Bean
    WebClient webClient(){
        return WebClient
                .builder()
                .baseUrl(BASE_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON); //Définit le type de contenu que peut envoyer le client: ici du JSON
                    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON) // Définit le type de contenu que peut recevoir le client: du json ici
                    );
                })
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().responseTimeout(TIMEOUT) // Définit le temps maximal pour recevoir une recevoir
                                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000) // Définit le temps maximal pour établir une connexion avec le serveur
                ))
                .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs().maxInMemorySize(1024*1024)) //Définit la taille maximale des données que peut charger l'application spring
                .build();
    }
}
