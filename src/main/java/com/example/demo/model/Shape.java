package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shapes")
public abstract class Shape {

    @Id
    private String id;
    private String color;
    private String type;

    public Shape() {
    }

    public Shape(String color, String type) {
        this.color = color;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract double calculateArea();

    public abstract double calculatePerimeter();

    @Override
    public String toString() {
        return "Shape{" +
                "id='" + id + '\'' +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", area=" + String.format("%.2f", calculateArea()) +
                ", perimeter=" + String.format("%.2f", calculatePerimeter()) +
                '}';
    }
}
