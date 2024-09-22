package com.emazon.transaccional_api.domain.interfaces;

import java.util.function.Supplier;
import java.util.function.Function;

public interface ICircuitBreakerService {
    <T> T executeWithCircuitBreaker(String name, Supplier<T> action, Function<Throwable, T> fallbackMethod);
}
