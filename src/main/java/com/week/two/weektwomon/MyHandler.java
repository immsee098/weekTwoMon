package com.week.two.weektwomon;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Component
public class MyHandler {

    public Mono<ServerResponse> hello (ServerRequest req) {
        String name = req.queryParam("name").get();
        HashMap nameMap = new HashMap();
        nameMap.put("to", name);
        nameMap.put("message", "hello "+name);
        Mono<HashMap> mymono = Mono.just(nameMap);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(mymono, HashMap.class));
    }
}
