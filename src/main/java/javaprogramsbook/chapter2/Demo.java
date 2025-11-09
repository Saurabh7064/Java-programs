package javaprogramsbook.chapter2;

import java.util.*;
import java.awt.Color;

public class Demo {
//    public static List<Integer> evenIntegers(List<Integer> integers) {
//        if (integers == null) {
//            return Collections.EMPTY_LIST;
//        }
//        List<Integer> evens = new ArrayList<>();
//        for (Integer nr: integers) {
//            if (nr != null && nr % 2 == 0) {
//                evens.add(nr);
//            }
//        }
//        return evens;
//    }


    public static List<Integer> evenIntegers(List<Integer> integers) {
        if (Objects.isNull(integers)) {
            return Collections.EMPTY_LIST;
        }
        List<Integer> evens = new ArrayList<>();
        for (Integer nr: integers) {
            if (Objects.nonNull(nr) && nr % 2 == 0) {
                evens.add(nr);
            }
        }
        return evens;
    }
//    public static int sumIntegers(List<Integer> integers) {
//        if (integers == null) {
//            throw new IllegalArgumentException("List cannot be null");
//        }
//        return integers.stream()
//                .filter(i -> i != null)
//                .mapToInt(Integer::intValue).sum();
//    }
//    public static boolean integersContainsNulls(List<Integer> integers) {
//        if (integers == null) {
//            return false;
//        }
//        return integers.stream()
//                .anyMatch(i -> i == null);
//    }
//    public static int sumIntegers(List<Integer> integers) {
//        if (integers == null) {
//            throw new IllegalArgumentException("List cannot be null");
//        }
//        return integers.stream()
//                .filter(Objects::nonNull)
//                .mapToInt(Integer::intValue).sum();
//    }
//    public static boolean integersContainsNulls(List<Integer> integers) {
//        if (integers == null) {
//            return false;
//        }
//        return integers.stream()
//                .anyMatch(Objects::isNull);
//    }

    public static int sumIntegers(List<Integer> integers) {
        if (Objects.isNull(integers)) {
            throw new IllegalArgumentException("List cannot be null");
        }
        return integers.stream()
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue).sum();
    }
    public static boolean integersContainsNulls(List<Integer> integers) {
        if (Objects.isNull(integers)) {
            return false;
        }
        return integers.stream()
                .anyMatch(Objects::isNull);
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, null, 4, 5, null, 6);
        System.out.println("Even integers: " + evenIntegers(integers));
        System.out.println("Sum of integers: " + sumIntegers(integers));
        System.out.println("List contains nulls: " + integersContainsNulls(integers));

        Car car = new Car("BMW", Color.BLUE);

        Map.of("key1", "value1", "key2", "value2");
        List.of("a", "b", "c");
    }
}
