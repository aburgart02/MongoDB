package com.example.demo;

import com.example.demo.model.Circle;
import com.example.demo.model.Rectangle;
import com.example.demo.model.Triangle;
import com.example.demo.repository.ShapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private ShapeRepository shapeRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("MongoDB Shape Repository Demo\n");

        shapeRepository.deleteAll();
        System.out.println("Database cleared\n");

        Circle c1 = new Circle("Red", 10.5);
        Circle c2 = new Circle("Blue", 5.0);
        Circle c3 = new Circle("Green", 15.0);

        Rectangle r1 = new Rectangle("Red", 20, 30);
        Rectangle r2 = new Rectangle("Green", 15, 15);
        Rectangle r3 = new Rectangle("Yellow", 10, 25);

        Triangle t1 = new Triangle("Blue", 3, 4, 5);
        Triangle t2 = new Triangle("Red", 5, 5, 5);
        Triangle t3 = new Triangle("Green", 7, 7, 10);
        Triangle t4 = new Triangle("Purple", 6, 8, 10);

        shapeRepository.save(c1);
        shapeRepository.save(c2);
        shapeRepository.save(c3);
        shapeRepository.save(r1);
        shapeRepository.save(r2);
        shapeRepository.save(r3);
        shapeRepository.save(t1);
        shapeRepository.save(t2);
        shapeRepository.save(t3);
        shapeRepository.save(t4);

        System.out.println("Saved 10 shapes\n");

        System.out.println("All Shapes");
        shapeRepository.findAll().forEach(System.out::println);

        System.out.println("\nShapes by Color (Red)");
        shapeRepository.findByColor("Red").forEach(System.out::println);

        System.out.println("\nShapes by Type (triangle)");
        shapeRepository.findByType("triangle").forEach(System.out::println);

        System.out.println("\nCircles with Radius > 6");
        shapeRepository.findCirclesWithRadiusGreaterThan(6.0).forEach(System.out::println);

        System.out.println("\nRed Triangles (Sorted by Color and Type)");
        shapeRepository.findByColorAndTypeSorted("Red", "triangle").forEach(System.out::println);

        System.out.println("\nShapes Excluding Red and Blue Colors (Sorted by Type)");
        shapeRepository.findByColorNotIn(Arrays.asList("Red", "Blue")).forEach(System.out::println);

        System.out.println("\nLarge Circles (radius >= 10) OR Square-like Rectangles (width >= 15, height >= 15)");
        shapeRepository.findLargeCirclesOrSquareRectangles(10.0, 15.0).forEach(System.out::println);

        System.out.println("\nRectangles with Area >= 300");
        shapeRepository.findRectanglesByMinArea(300.0).forEach(System.out::println);

        System.out.println("\nRectangles with Area <= 250");
        shapeRepository.findRectanglesByMaxArea(250.0).forEach(System.out::println);

        System.out.println("\nTriangles with sideA = 5 (Sorted by Sides)");
        shapeRepository.findTrianglesBySideA(5.0).forEach(System.out::println);

        System.out.println("\nStatistics by Type");
        System.out.println("Circles count: " + shapeRepository.countByType("circle"));
        System.out.println("Rectangles count: " + shapeRepository.countByType("rectangle"));
        System.out.println("Triangles count: " + shapeRepository.countByType("triangle"));

        System.out.println("\nUpdate Operations");
        c2.setColor("Purple");
        c2.setRadius(7.5);
        shapeRepository.save(c2);
        System.out.println("Updated Circle c2 to Purple with radius 7.5");
        System.out.println(shapeRepository.findById(c2.getId()).orElse(null));

        t1.setSideA(5);
        t1.setSideB(12);
        t1.setSideC(13);
        shapeRepository.save(t1);
        System.out.println("\nUpdated Triangle t1 sides to 5-12-13");
        System.out.println(shapeRepository.findById(t1.getId()).orElse(null));

        System.out.println("\nDelete Operations");
        shapeRepository.delete(r2);
        System.out.println("Deleted Green Rectangle r2");
        System.out.println("Total count after deletion: " + shapeRepository.count());

        shapeRepository.delete(t3);
        System.out.println("Deleted Green Triangle t3");
        System.out.println("Total count after deletion: " + shapeRepository.count());

        System.out.println("\nFinal Shape List");
        shapeRepository.findAll().forEach(System.out::println);

        System.out.println("\nDemo Completed");
    }
}
