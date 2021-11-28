package ru.gb.lesson;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.gb.lesson.lesson4.Triangle;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleTest {

    @Test
    @DisplayName("Периметр египетского треугольника")
    public void egyptTriangleTest() {
        Triangle triangle = new Triangle(3, 4, 5); // Arrange
        double v = triangle.calculateTrianglePerimeter(); // Act
        assertEquals(12, v); //Assert
    }

    @Test
    @DisplayName("Периметр правильного треугольника")
    public void equalSidesTriangleTest() {
        Triangle triangle = new Triangle(3, 3, 3); // Arrange
        double v = triangle.calculateTrianglePerimeter(); // Act
        assertEquals(9, v); //Assert
    }

    @Test
    @DisplayName("Периметр тупоугольного треугольника")
    void withAngleGreaterThan90Test() {
        Triangle triangle = new Triangle(3, 4, 6); // Arrange
        double v = triangle.calculateTrianglePerimeter(); // Act
        assertEquals(13, v); //Assert
    }

    public static Stream<Arguments> validTriangles() {
        return Stream.of(
                Arguments.of(new Triangle(3, 4, 5), 12),
                Arguments.of(new Triangle(3, 3, 3), 9),
                Arguments.of(new Triangle(3, 4, 6), 13)
        );
    }

    @ParameterizedTest(name = "Периметр треугольника {0} == {1}")
    @MethodSource("validTriangles")
    void validTriangleTest(Triangle triangle, int expectedResult) {
        double v = triangle.calculateTrianglePerimeter(); // Act
        assertEquals(expectedResult, v); //Assert
    }

    @ParameterizedTest(name = "Перекрасить треугольник из WHITE {0}: параметры из enum")
    @EnumSource(Triangle.Colour.class)
    void paintToEnumValuesFromWhiteTest(Triangle.Colour colour) {
        Triangle triangle = new Triangle(1, 1, 1); // Arrange
        triangle.paint(colour); // Act
        assertEquals(colour, triangle.getColour()); //Assert
    }

    @ParameterizedTest(name = "Перекрасить треугольник из WHITE в {0}: параметры string")
    @ValueSource(strings = {"RED", "GREEN"})
    void paintToStringValuesFromWhiteTest(String colour) {
        Triangle triangle = new Triangle(1, 1, 1); // Arrange
        triangle.paint(colour); // Act
        assertEquals(Triangle.Colour.valueOf(colour), triangle.getColour()); //Assert
    }
}
