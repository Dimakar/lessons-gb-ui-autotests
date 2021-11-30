package ru.gb.lesson.lesson4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import ru.gb.lesson.lesson4.providers.InvalidTriangleProvider;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class TriangleTest {

    @BeforeEach
    void setUp() {
        System.out.println("Before each test");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each test");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all tests");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all tests");
    }

    @RepeatedTest(2)
    @DisplayName("Периметр египетского треугольника")
    @Disabled("Перенесены в параметризированные")
    public void egyptTriangleTest() {
        Triangle triangle = new Triangle(3, 4, 5); // Arrange
        double v = triangle.calculateTrianglePerimeter(); // Act
        assertEquals(12, v); //Assert
    }

    @Test
    @DisplayName("Периметр правильного треугольника")
    @Disabled("Перенесены в параметризированные")
    public void equalSidesTriangleTest() {
        Triangle triangle = new Triangle(3, 3, 3); // Arrange
        double v = triangle.calculateTrianglePerimeter(); // Act
        assertEquals(9, v); //Assert
    }

    @Test
    @DisplayName("Периметр тупоугольного треугольника")
    @Disabled("Перенесены в параметризированные")
    void withAngleGreaterThan90Test() {
        Triangle triangle = new Triangle(3, 4, 6); // Arrange
        double v = triangle.calculateTrianglePerimeter(); // Act
        assertEquals(13, v); //Assert
    }

    //много одинаковых тестов заменяем параметризированными с разными способами передачи параметров

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
        assumeFalse(triangle.getColour().equals(colour), "Новый цвет не должен совпадать со старым");
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

    @ParameterizedTest(name = "Перекрасить треугольник из {0} в {1}")
    @CsvSource({"RED, GREEN", "BLUE, RED"})
    void paintToStringValuesFromWhiteTest(Triangle.Colour beforeColour, Triangle.Colour afterColour) {
        Triangle triangle = new Triangle(1, 1, 1, beforeColour); // Arrange
        triangle.paint(afterColour); // Act
        assertEquals(afterColour, triangle.getColour()); //Assert
    }

    @ParameterizedTest(name = "Невалидный треугольник {0}")
    @ArgumentsSource(InvalidTriangleProvider.class)
    void validTriangleTest(Triangle triangle) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, triangle::calculateTrianglePerimeter);
        assertEquals("The sum of any two sides must be greater than any other side", illegalArgumentException.getMessage());
    }

    @ParameterizedTest(name = "Перекрасить треугольник из одного цвета в тот же {0}")
    @CsvSource({"RED, RED", "GREEN, GREEN"})
    void paintInTheSameColourTest(Triangle.Colour beforeColour, Triangle.Colour afterColour) {
        Triangle triangle = new Triangle(1, 1, 1, beforeColour); // Arrange
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> triangle.paint(afterColour));
        assertEquals("The new colour must not be the same", illegalArgumentException.getMessage()); //Assert
    }

    public static Stream<Arguments> similarTriangles() {
        return Stream.of(Arguments.of(new Triangle(1, 1, 1), 2, new Triangle(2, 2, 2)),
                Arguments.of(new Triangle(3, 4, 5), 1, new Triangle(3, 4, 5)));
    }

    @ParameterizedTest(name = "Создание подобного треугольника из {0} с коэффициентом {1}")
    @MethodSource("similarTriangles")
    void similarTriangleTest(Triangle triangle, int coefficient, Triangle expectedTriangle) {
        Triangle newTriangle = triangle.createSimilarTriangle(coefficient);
        assertEquals(expectedTriangle, newTriangle);

        //soft Assertions
        assertAll(() -> assertEquals(expectedTriangle.getA(), newTriangle.getA()),
                () -> assertEquals(expectedTriangle.getB(), newTriangle.getB())
        );
    }


    @Nested
    class PaintTriangleTest {

        Triangle triangle;

        @BeforeEach
        void setUp() {
            System.out.println("Create triangle for test");
            triangle = new Triangle(1, 1, 1);
        }

        @ParameterizedTest(name = "Перекрасить треугольник из WHITE в {0}: параметры string")
        @ValueSource(strings = {"RED", "GREEN"})
        void paintToStringValuesFromWhiteTest(String colour) {
            triangle.paint(colour); // Act
            assertEquals(Triangle.Colour.valueOf(colour), triangle.getColour()); //Assert
        }
    }
}
