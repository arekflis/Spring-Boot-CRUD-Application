package com.example.gatewayMicroservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayMicroserviceApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder,
									 @Value("${players.microservice.url}") String playersURL,
									 @Value("${clubs.microservice.url}") String clubsURL,
									 @Value("${gateway.microservice.url}") String gatewayURL){
		return routeLocatorBuilder
				.routes()
				.route("clubs", route -> route
						.host(gatewayURL)
						.and()
						.path("/api/clubs", "/api/clubs/**")
						.uri(clubsURL)
				)
				.route("players", route -> route
						.host(gatewayURL)
						.and()
						.path("/api/players", "/api/players/**")
						.uri(playersURL)
				)
				.build();


	}

}
