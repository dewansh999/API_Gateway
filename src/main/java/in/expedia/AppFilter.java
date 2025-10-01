package in.expedia;

import java.util.List; // Keep this
import java.util.Set; // Keep this

// import org.hibernate.validator.internal.util.logging.LoggerFactory; // Remove this internal import


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
