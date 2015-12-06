package com.tuniu.understand.library;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nuc on 2015/12/6.
 */
public class InternalExternal {
    // 传统方式--外部迭代
    private static void external(List<Shape> shapes) {
        for (Shape s : shapes) {
            s.setColor(Color.RED);
        }
    }

    // 内部迭代
    private static void internal(List<Shape> shapes) {
        shapes.forEach(s -> s.setColor(Color.RED));
    }

    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        external(shapes);
        internal(shapes);
    }
}
