package ru.gb.lesson.lesson4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gb.lesson.lesson4.providers.InvalidTriangleProvider;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

public class TriangleByAssertJTest {

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
        assertThat(v).as("Периметр треугольника " + triangle + " должен быть равен " + expectedResult).isEqualTo(expectedResult); // Assert
    }

    @ParameterizedTest(name = "Перекрасить треугольник из WHITE {0}: параметры из enum")
    @EnumSource(Triangle.Colour.class)
    void paintToEnumValuesFromWhiteTest(Triangle.Colour colour) {
        Triangle triangle = new Triangle(1, 1, 1); // Arrange
        assumeFalse(triangle.getColour().equals(colour), "Новый цвет не должен совпадать со старым");
        triangle.paint(colour); // Act
        assertThat(triangle.getColour()).isEqualTo(colour);
    }

    @ParameterizedTest(name = "Перекрасить треугольник из WHITE в {0}: параметры string")
    @ValueSource(strings = {"RED", "GREEN"})
    void paintToStringValuesFromWhiteTest(String colour) {
        Triangle triangle = new Triangle(1, 1, 1); // Arrange
        triangle.paint(colour); // Act
        assertThat(triangle.getColour()).isEqualTo(Triangle.Colour.valueOf(colour));
    }

    @ParameterizedTest(name = "Перекрасить треугольник из {0} в {1}")
    @CsvSource({"RED, GREEN", "BLUE, RED"})
    void paintToStringValuesFromWhiteTest(Triangle.Colour beforeColour, Triangle.Colour afterColour) {
        Triangle triangle = new Triangle(1, 1, 1, beforeColour); // Arrange
        triangle.paint(afterColour); // Act
        assertThat(triangle.getColour()).isEqualTo(afterColour);
    }

    @ParameterizedTest(name = "Невалидный треугольник {0}")
    @ArgumentsSource(InvalidTriangleProvider.class)
    void validTriangleTest(Triangle triangle) {
        assertThatThrownBy(triangle::calculateTrianglePerimeter)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The sum of any two sides must be greater than any other side");
    }

    @ParameterizedTest(name = "Перекрасить треугольник из одного цвета в тот же {0}")
    @CsvSource({"RED, RED", "GREEN, GREEN"})
    void paintInTheSameColourTest(Triangle.Colour beforeColour, Triangle.Colour afterColour) {
        Triangle triangle = new Triangle(1, 1, 1, beforeColour); // Arrange
        assertThatThrownBy(() -> triangle.paint(afterColour))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The new colour must not be the same");
    }

    public static Stream<Arguments> similarTriangle() {
        return Stream.of(Arguments.of(new Triangle(1, 1, 1), 2, new Triangle(2, 2, 2)),
                Arguments.of(new Triangle(3, 4, 5), 1, new Triangle(3, 4, 5)));
    }

    @ParameterizedTest(name = "Создание подобного треугольника из {0} с коэффициентом {1}")
    @MethodSource("similarTriangle")
    void similarTriangleTest(Triangle triangle, int coefficient, Triangle expectedTriangle) {
        Triangle newTriangle = triangle.createSimilarTriangle(coefficient);
        newTriangle.setColour(Triangle.Colour.RED);
        assertThat(newTriangle).as("Подобный треугольник к "
                        + triangle + " с коэффициентом "
                        + coefficient + " равен "
                        + expectedTriangle)
                .usingRecursiveComparison()
                .ignoringFields("colour")
                .isEqualTo(expectedTriangle);
    }

    public static Stream<Arguments> similarTriangles() {
        return Stream.of(Arguments.of(new Triangle(1, 1, 1), 2, 3, List.of(new Triangle(2, 2, 2), new Triangle(3, 3, 3))));
    }

    @ParameterizedTest(name = "Создание подобных треугольников из {0} с коэффициентами от {1} до {2}")
    @MethodSource("similarTriangles")
    void similarTrianglesTest(Triangle triangle, int sinceCoefficient, int toCoefficient, List<Triangle> expectedTriangles) {
        List<Triangle> actualTriangles = triangle.createSimilarTriangles(sinceCoefficient, toCoefficient);

        assertThat(actualTriangles).containsExactlyInAnyOrderElementsOf(expectedTriangles)
                .hasSize(expectedTriangles.size())
                .contains(expectedTriangles.get(0))
                .doesNotHaveDuplicates();

    }

}
