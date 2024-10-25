package com.example.javat1application.t_interface;

public class Cat implements Animal {
    @Override
    public void sound() {
        System.out.println("야옹");
    }

    @Override
    public void eat() {
        System.out.println("생선을 먹는다. 냥");
    }

    @Override
    public void sleep() {
        System.out.println("새근새근 냥");
    }
}
