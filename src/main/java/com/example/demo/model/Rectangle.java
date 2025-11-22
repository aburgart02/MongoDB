package com.example.demo.model;

import org.springframework.data.annotation.TypeAlias;

@TypeAlias("rectangle")
public class Rectangle extends Shape {

    private double width;
    private double height;

    public Rectangle() {
        super();
        this.setType("rectangle");
    }

    public Rectangle(String color, double width, double height) {
        super(color, "rectangle");
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                "} " + super.toString();
    }
}
