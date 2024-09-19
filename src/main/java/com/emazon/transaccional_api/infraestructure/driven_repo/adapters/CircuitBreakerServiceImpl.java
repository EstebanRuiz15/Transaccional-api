package com.emazon.transaccional_api.infraestructure.driven_repo.adapters;

import com.emazon.transaccional_api.domain.interfaces.ICircuitBreakerService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class CircuitBreakerServiceImpl implements ICircuitBreakerService {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public CircuitBreakerServiceImpl(CircuitBreakerRegistry circuitBreakerRegistry) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
    }

    @Override
    public <T> T executeWithCircuitBreaker(String name, Supplier<T> action, Function<Throwable, T> fallbackMethod) {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(name);

        try {
            return CircuitBreaker.decorateSupplier(circuitBreaker, action).get();
        } catch (Throwable throwable) {
            return fallbackMethod.apply(throwable);
        }
    }
}