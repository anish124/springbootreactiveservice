package com.example.webflux.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class HotelRouter {

    @Bean
    public RouterFunction<ServerResponse> route(HotelHandler handler) {
        return RouterFunctions
                .route(GET("/all").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
                .andRoute(GET("/hotel/{id}").and(accept(MediaType.APPLICATION_STREAM_JSON)), handler::findById)
                .andRoute(POST("/create").and(accept(MediaType.APPLICATION_JSON)), handler::save)
                .andRoute(DELETE("/delete/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::delete);
    }

}
