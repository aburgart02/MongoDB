package com.example.demo.model;

import org.springframework.data.annotation.TypeAlias;

@TypeAlias("circle")
public class Circle extends Shape {

    private double radius;

    public Circle() {
        super();
        this.setType("circle");
    }

    public Circle(String color, double radius) {
        super(color, "circle");
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                "} " + super.toString();
    }
}
