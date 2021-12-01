package ru.gb.lesson.lesson4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gb.lesson.lesson4.utils.LogTestTime;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(LogTestTime.class)
public class TriangleTest {

    private static final Logger logger = LoggerFactory.getLogger(TriangleTest.class);

    @BeforeAll
    static void beforeAll() {
        logger.info("BeforeAll");
        logger.debug("DEBUG");
        logger.error("ERROR");
    }

    @AfterAll
    static void afterAll() {
        logger.info("AfterAll");
    }

    @AfterEach
    void tearDown() {
        logger.info("AfterEach");
    }

    @BeforeEach
    void setUp() {
        logger.info("BeforeEach");
    }

    @RepeatedTest(20)
    @DisplayName("Рассчитать периметр: египетский треугольник (3, 4, 5)")
    @Disabled("Перенесли в параметризированный тест trianglePerimeterTest")
    void egyptTriangleTest() {
        Triangle triangle = new Triangle(3, 4, 5); // Arrange
        int actualResult = triangle.calculatePerimeter(); // Act
        assertEquals(12, actualResult); //Assert
    }

    @Test
    @DisplayName("Рассчитать периметр: правильный треугольник (3, 3, 3)")
    @Disabled("Перенесли в параметризированный тест trianglePerimeterTest")
    void rightTriangleTest() {
        Triangle triangle = new Triangle(3, 3, 3); // Arrange
        int actualResult = triangle.calculatePerimeter(); // Act
        assertEquals(9, actualResult); //Assert
    }

    @Test
    @DisplayName("Рассчитать периметр: тупоугольный треугольник (3, 4, 6)")
    @Disabled("Перенесли в параметризированный тест trianglePerimeterTest")
    void triangleWithAngleGreaterThan90Test() {
        Triangle triangle = new Triangle(3, 4, 6); // Arrange
        int actualResult = triangle.calculatePerimeter(); // Act
        assertEquals(13, actualResult); //Assert
    }

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
        assertEquals(expectedResult, actualResult); //Assert
    }

    @Test
    @DisplayName("Создание подобного треугольника")
    void createSimilarTriangleTest() {
        Triangle triangle = new Triangle(1, 1, 1);
        Triangle similarTriangle = triangle.createSimilarTriangle(2);
        assertEquals(new Triangle(2, 2, 2), similarTriangle);
    }

    @Test
    @DisplayName("Создание подобных треугольников")
    void createSimilarTrianglesTest() {
        Triangle triangle = new Triangle(1, 1, 1);
        List<Triangle> similarTriangles = triangle.createSimilarTriangles(2, 3);
        assertEquals(List.of(new Triangle(2, 2 ,2), new Triangle(3, 3 ,3)), similarTriangles);
    }

    @Test
    void catchExceptionByJunitTest() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Triangle(1, 1, 50)
                .calculatePerimeter());
        assertEquals("The any two sides must be greater than other side", illegalArgumentException.getMessage());
    }

    @Nested
    public class WithArrangeCreatingTriangleTest {
        Triangle triangle;
        @BeforeEach
        void setUp() {
            triangle = new Triangle(1, 1, 1);
        }

        @ParameterizedTest(name = "Перекраска треугольника в цвет {0} (из энам)")
        @EnumSource(Colour.class)
        void paintInAllPossibleColoursTest(Colour colour) {
            Assumptions.assumeFalse(triangle.getColour().equals(colour), "Старый цвет не должен быть равен новому");
            triangle.paint(colour);
            assertEquals(colour, triangle.getColour());
        }

        @ParameterizedTest(name = "Перекраска треугольника в цвет {0} (из строчки)")
        @ValueSource(strings = {"RED", "YELLOW", "BLUE"})
        void paintInAllPossibleColoursFromStringTest(String colour) {
            triangle.paint(colour);
            assertEquals(colour, triangle.getColour().toString());
        }

        @ParameterizedTest(name = "Перекраска треугольника из цвета {0} в цвет {1} (из энам)")
        @CsvSource(value = {"RED, YELLOW", "YELLOW, RED", "BLUE, WHITE"})
        void paintFromDifferentColoursToAnotherColourTest(Colour oldColour, Colour newColour) {
            triangle.paint(oldColour);
            triangle.paint(newColour);
            assertEquals(newColour, triangle.getColour());
        }
    }


}
