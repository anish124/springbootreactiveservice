package com.example.webflux.controller;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.webflux.model.Hotel;
import com.example.webflux.repository.HotelRepository;

import reactor.core.publisher.Mono;
@Component
public class HotelHandler {
	private HotelRepository hotelRepository;

	public HotelHandler(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}
	public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("id");
    
        return  ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(hotelRepository.findById(id),Hotel.class);
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(hotelRepository.findAll(), Hotel.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
    	 
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(request.bodyToMono(Hotel.class).flatMap(hotelRepository::save), Hotel.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(hotelRepository.deleteById(id), Void.class);
    }
    
    	
    	 
}
