package in.ashokit;

import java.util.List; // Keep this
import java.util.Set; // Keep this

// import org.hibernate.validator.internal.util.logging.LoggerFactory; // Remove this internal import
import org.slf4j.Logger; // Add this for standard logging
import org.slf4j.LoggerFactory; // Add this for standard logging
import org.springframework.cloud.gateway.filter.GatewayFilterChain; // Keep this
import org.springframework.cloud.gateway.filter.GlobalFilter; // Keep this
import org.springframework.http.HttpHeaders; // Fix import
import org.springframework.http.server.reactive.ServerHttpRequest; // Keep this
import org.springframework.stereotype.Component; // Keep this
import org.springframework.web.server.ServerWebExchange; // Keep this

import reactor.core.publisher.Mono; // Keep this

@Component
// @Order(-1) // Optional: Use if you want this filter to run early in the chain
public class AppFilter implements GlobalFilter {

	// Use SLF4J Logger
	private static final Logger logger = LoggerFactory.getLogger(AppFilter.class); // Use AppFilter.class

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		logger.info("AppFilter :: filter () method executed..."); // Updated log message

		// Accessing HTTP Request information
		ServerHttpRequest request = exchange.getRequest();

		// Fix: Use the correct HttpHeaders class
		HttpHeaders headers = request.getHeaders();
		Set<String> keySet = headers.keySet();

		keySet.forEach(key -> {
			// Fix: get() method returns List<String>
			List<String> values = headers.get(key);
			System.out.println(key + " :: " + values);
		});

		// Continue the filter chain
		return chain.filter(exchange);
	}
}