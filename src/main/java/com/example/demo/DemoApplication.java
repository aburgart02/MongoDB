package com.example.demo;

import com.example.demo.model.Circle;
import com.example.demo.model.Rectangle;
import com.example.demo.repository.ShapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private ShapeRepository shapeRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting MongoDB Demo");

        shapeRepository.deleteAll();
        System.out.println("Cleared database.");

        Circle c1 = new Circle("Red", 10.5);
        Circle c2 = new Circle("Blue", 5.0);
        Rectangle r1 = new Rectangle("Red", 20, 30);
        Rectangle r2 = new Rectangle("Green", 15, 15);

        shapeRepository.save(c1);
        shapeRepository.save(c2);
        shapeRepository.save(r1);
        shapeRepository.save(r2);
        System.out.println("Saved 4 shapes.");

        System.out.println("\nAll Shapes");
        shapeRepository.findAll().forEach(System.out::println);

        System.out.println("\nRed Shapes");
        shapeRepository.findByColor("Red").forEach(System.out::println);

        System.out.println("\nRed Shapes excluding first circle");
        shapeRepository.findByColorAndIdNot("Red", c1.getId()).forEach(System.out::println);

        System.out.println("\nCircles with radius > 6");
        shapeRepository.findCirclesWithRadiusGreaterThan(6.0).forEach(System.out::println);

        c2.setColor("Purple");
        shapeRepository.save(c2);
        System.out.println("\nUpdated Blue Circle to Purple");
        System.out.println(shapeRepository.findById(c2.getId()).orElse(null));

        shapeRepository.delete(r2);
        System.out.println("\nDeleted Green Rectangle");
        System.out.println("Count after delete: " + shapeRepository.count());
    }
}
