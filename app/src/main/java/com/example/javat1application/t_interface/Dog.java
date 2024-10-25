package com.example.javat1application.t_interface;

public class Dog implements Animal {
    @Override
    public void sound() {
        System.out.println("멍멍");
    }

    @Override
    public void eat() {
        System.out.println("사료를 먹는다. 왈왈");
    }

    @Override
    public void sleep() {
        System.out.println("쿨쿨 왈왈");
    }
}

