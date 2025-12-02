package com.example.demo.repository;

import com.example.demo.model.Shape;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface ShapeRepository extends MongoRepository<Shape, String> {

    List<Shape> findByColor(String color);

    List<Shape> findByType(String type);

    @Query("{ 'type' : 'circle', 'radius' : { $gt : ?0 } }")
    List<Shape> findCirclesWithRadiusGreaterThan(double radius);

    @Query(value = "{ 'color' : ?0, 'type' : ?1 }", sort = "{ 'color' : 1, 'type' : -1 }")
    List<Shape> findByColorAndTypeSorted(String color, String type);

    @Query(value = "{ 'color' : { $nin : ?0 } }", sort = "{ 'type' : 1 }")
    List<Shape> findByColorNotIn(List<String> excludedColors);

    @Query(value = "{ $or : [ { 'type' : 'circle', 'radius' : { $gte : ?0 } }, { 'type' : 'rectangle', 'width' : { $gte : ?1 }, 'height' : { $gte : ?1 } } ] }", sort = "{ 'type' : 1, 'color' : -1 }")
    List<Shape> findLargeCirclesOrSquareRectangles(double minRadius, double minSide);

    @Query("{ 'type' : 'rectangle', $expr : { $gte : [ { $multiply : ['$width', '$height'] }, ?0 ] } }")
    List<Shape> findRectanglesByMinArea(double minArea);

    @Query("{ 'type' : 'rectangle', $expr : { $lte : [ { $multiply : ['$width', '$height'] }, ?0 ] } }")
    List<Shape> findRectanglesByMaxArea(double maxArea);

    @Query(value = "{ 'type' : 'triangle', 'sideA' : ?0 }", sort = "{ 'sideA' : 1, 'sideB' : 1, 'sideC' : 1 }")
    List<Shape> findTrianglesBySideA(double sideA);

    long countByType(String type);
}