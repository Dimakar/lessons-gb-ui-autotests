package ru.gb.lesson.lesson4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Triangle {
    private int a;
    private int b;
    private int c;
    private Colour colour = Colour.WHITE;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int calculateTrianglePerimeter() {
        if (!hasPositiveSides()) {
            throw new IllegalArgumentException("The sides must be positive");
        }
        if (!isValid()) {
            throw new IllegalArgumentException("The sum of any two sides must be greater than any other side");
        }
        return a + b + c;
    }

    public boolean hasPositiveSides() {
        return (a > 0 && b > 0 && c > 0);
    }

    public boolean isValid() {
        int maxSide = Math.max(Math.max(a, b), c);
        return maxSide < (a + b + c - maxSide);
    }

    public double calculateTriangleArea() {
        //todo написать метод - ДЗ (формула Герона)
        return 0;
    }

    public void paint(Colour colour) {
        if (colour == this.colour) {
            throw new IllegalArgumentException("The new colour must not be the same");
        }
        this.setColour(colour);
    }

    public void paint(String colour) {
        this.paint(Colour.valueOf(colour));
    }

    public Triangle createSimilarTriangle(int coefficient) {
        if (!hasPositiveSides()) {
            throw new IllegalArgumentException("The sides must be positive");
        }
        if (!isValid()) {
            throw new IllegalArgumentException("The sum of any two sides must be greater than any other side");
        }
        if (coefficient <= 0) {
            throw new IllegalArgumentException("The coefficient must be positive");
        }
        return new Triangle(a * coefficient, b * coefficient, c * coefficient, colour);
    }

    public List<Triangle> createSimilarTriangles(int sinceCoefficient, int toCoefficient) {
        if (!hasPositiveSides()) {
            throw new IllegalArgumentException("The sides must be positive");
        }
        if (!isValid()) {
            throw new IllegalArgumentException("The sum of any two sides must be greater than any other side");
        }
        if (sinceCoefficient <= 0 || toCoefficient <= 0) {
            throw new IllegalArgumentException("The coefficients must be positive");
        }

        if (sinceCoefficient > toCoefficient) {
            throw new IllegalArgumentException("The toCoefficient must be greater than sinceCoefficient");
        }
        List<Triangle> triangleList = new ArrayList<>();
        for (int i = sinceCoefficient; i < toCoefficient + 1; i++) {
            triangleList.add(new Triangle(a * i, b * i, c * i, colour));
        }
        return triangleList;
    }

    public enum Colour {
        RED, GREEN, BLUE, WHITE
    }
}
