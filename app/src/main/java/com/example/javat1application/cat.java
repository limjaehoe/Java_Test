package com.example.javat1application;

public class cat {

    static String hotel = "Hilton";


    String name;
    int age;
    String color;

    public cat(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public void meow() {
        System.out.println("Meow!");
    }

    public void sleep() {
        System.out.println("Zzz...");
    }

    public void eat() {
        System.out.println("Nom nom...");
    }

    public void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Color: " + color);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
