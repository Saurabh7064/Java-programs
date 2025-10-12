package javaprogramsbook;

import java.awt.*;
import java.util.Objects;

public class Car {
    private final String name;
    private final Color color;

    public Car(String name, Color color) {
//        this.name = Objects.requireNonNull(name, "Car name cannot be null");
//        this.color = Objects.requireNonNull(color, "Car color cannot be null");
        this.name = MyObjects.requireNonNullElseThrow(name,
                new UnsupportedOperationException("Name cannot be set as null"));
        this.color = MyObjects.requireNotNullElseThrow(color, () ->
                new UnsupportedOperationException("Color cannot be set as null"));
    }
//
//    public void assignDriver(String license, Point location) {
//        Objects.requireNonNull(license, "License cannot be null");
//        Objects.requireNonNull(location, "Location cannot be null");
//    }

}
