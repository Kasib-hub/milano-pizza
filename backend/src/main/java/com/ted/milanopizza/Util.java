package com.ted.milanopizza;

import java.util.function.Consumer;

public class Util {
    // Consumer represents an operation that accepts a single input argument and returns no result
    public static <T> void updateIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {setter.accept(value);}
    }
}
