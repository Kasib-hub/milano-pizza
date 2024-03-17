package com.ted.milanopizza.techtalk;

import java.util.List;

public class Lambda {
    public static void main(String[] args) {
        System.out.println("hello world, learn some lambdas");

        // lambdas only used by interfaces
        /*
        allows you to just pass an implementation behind an interface instead of writing the interface
        and instantiating it just to call its own method from the interface.
         */
        List.of(1, 2, 3, 4, 50).forEach(num -> System.out.println(num + 2));
    }
}