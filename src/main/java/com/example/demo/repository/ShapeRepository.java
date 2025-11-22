package com.example.demo.repository;

import com.example.demo.model.Shape;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface ShapeRepository extends MongoRepository<Shape, String> {

    List<Shape> findByColor(String color);

    @Query(value = "{ 'color' : ?0, '_id' : { $ne : ?1 } }", sort = "{ 'type' : 1 }")
    List<Shape> findByColorAndIdNot(String color, String excludeId);

    @Query("{ 'type' : 'circle', 'radius' : { $gt : ?0 } }")
    List<Shape> findCirclesWithRadiusGreaterThan(double radius);
}
