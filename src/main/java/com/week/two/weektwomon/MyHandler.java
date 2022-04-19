package com.week.two.weektwomon;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyHandler {

    WebClient webClient = WebClient.create("http://localhost:8081");

    public Mono<ServerResponse> hello (ServerRequest req) {
        String name = req.queryParam("name").get();

        //https://stackoverflow.com/questions/56314853/issue-with-spring-webflux-webclient-nothing-happens-when-trying-to-send-post-r
        //retrieve는 subscibe를 해주는 역할이 아닌지? retrieve는 날리기만 하는 기능인 것으로... 섭스크라이브나 블락을 따로 안해주면 섭스크라이브가 안되는 모양...
        Mono result = webClient.get()
                .uri("/getJob?name="+name)
                .retrieve()
                .bodyToMono(HashMap.class);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(result, HashMap.class));
    }
}
