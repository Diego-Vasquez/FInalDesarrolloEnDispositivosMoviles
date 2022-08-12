package com.example.practicacalificadan05;

import java.util.Objects;

public class Operation {

    private String operation;

    Operation(String operation) {
        this.operation = operation;
    }

    public int compute(int a, int b) {
        if (Objects.equals(operation, "+")) return plus(a, b);
        if (Objects.equals(operation, "-")) return subs(a, b);
        if (Objects.equals(operation, "x")) return mult(a, b);
        return -1;
    }

    private int plus(int a, int b) {
        return a + b;
    }

    private int subs(int a, int b) {
        return a - b;
    }

    private int mult(int a, int b) {
        return a * b;
    }
}
