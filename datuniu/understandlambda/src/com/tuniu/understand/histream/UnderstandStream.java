package com.tuniu.understand.histream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by nuc on 2015/12/7.
 */
public class UnderstandStream {

    public static int sum(List<Integer> list) {
        int sum = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        List<String> strings = new ArrayList<>();

        String concatenated = strings.stream().reduce("", String::concat);

        return sum;
    }


    public static void experienceCollector() {
        Collector<Employee, ?, Integer> c = Collectors.summingInt(Employee::getSalary);

        Collector<Employee, ?, Map<Employee.Department, Integer>> c2 =
                Collectors.groupingBy(Employee::getDept, c);
    }


    public static void main(String[] args) {

    }
}
