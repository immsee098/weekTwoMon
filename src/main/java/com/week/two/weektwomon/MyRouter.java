package com.week.two.weektwomon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;


@Configuration
public class MyRouter {

//    public RouterFunction<ServerResponse> route2(MyHandler myHandler) {
//        return RouterFunctions.route()
//                .path("/test1", builder ->
//                        builder.GET("", myHandler::hello))
//                .build();
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> route() {
//        return RouterFunctions.route(
//                RequestPredicates.GET("/hello"),
//                req -> ServerResponse
//                        .ok().body(Flux.just("Hello", "World!"), String.class)
//        );
//    }

    @Bean
    public RouterFunction<ServerResponse> route(MyHandler handler) {
        return RouterFunctions.route(
                RequestPredicates.GET("/hello").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::hello);
    }
}
