package ru.gb.lesson;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.gb.lesson.lesson4.Colour;
import ru.gb.lesson.lesson4.Triangle;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AssertJTriangleTest {

    public static Stream<Arguments> triangleForPerimeter() {
        return Stream.of(Arguments.of(new Triangle(3, 4, 5), 12),
                Arguments.of(new Triangle(3, 3, 3), 9),
                Arguments.of(new Triangle(3, 4, 6), 13)
        );
    }

    @ParameterizedTest(name = "Рассчитать периметр: для {0} периметр = {1}")
    @MethodSource("triangleForPerimeter")
    void trianglePerimeterTest(Triangle triangle, int expectedResult) {
        int actualResult = triangle.calculatePerimeter(); // Act
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Создание подобного треугольника")
    void createSimilarTriangleTest() {
        Triangle triangle = new Triangle(1, 1, 1);
        Triangle expectedTriangle = new Triangle(2, 2, 2);
        Triangle similarTriangle = triangle.createSimilarTriangle(2);

        similarTriangle.setColour(Colour.RED);

        assertThat(similarTriangle).as("У треугольника "
                        + triangle +
                        " подобный треугольник с коэффициентом 2 равен" +
                        expectedTriangle)
                .usingRecursiveComparison()
                .ignoringFieldsOfTypes(Colour.class)
                .isEqualTo(expectedTriangle);
    }

    @Test
    @DisplayName("Создание подобных треугольников")
    void createSimilarTrianglesTest() {
        Triangle triangle = new Triangle(1, 1, 1);
        List<Triangle> similarTriangles = triangle.createSimilarTriangles(2, 3);

        assertThat(similarTriangles)
                .containsExactlyInAnyOrderElementsOf(List.of(new Triangle(2, 2, 2), new Triangle(3, 3, 3)));
    }

    @Test
    void catchExceptionByAssertJTest() {
        assertThatThrownBy(() -> new Triangle(1, 1, 50).calculatePerimeter())
                .hasMessage("The any two sides must be greater than other side")
                .isInstanceOf(IllegalArgumentException.class);
    }

}
