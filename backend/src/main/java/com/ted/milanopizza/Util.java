package com.ted.milanopizza;

import java.util.function.Consumer;

public class Util {
    public static <T> void updateIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {setter.accept(value);}
    }
}
