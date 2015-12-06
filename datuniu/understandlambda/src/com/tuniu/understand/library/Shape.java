package com.tuniu.understand.library;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nuc on 2015/12/6.
 */
public class Shape {
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "color=" + color +
                '}';
    }

}
