package ru.gb.lesson.lesson4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public enum Colour {
        RED, GREEN, BLUE, WHITE
    }
}
