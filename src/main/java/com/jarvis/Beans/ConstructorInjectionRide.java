package com.jarvis.Beans;

public class ConstructorInjectionRide {
    private final String rideName;
    private final int cc_of_engine;
    private final int mileage_Of_engine;
    private final String color;

    public ConstructorInjectionRide(String rideName, int cc_of_engine, int mileage_Of_engine, String color) {
        this.rideName = rideName;
        this.cc_of_engine = cc_of_engine;
        this.mileage_Of_engine = mileage_Of_engine;
        this.color = color;
    }

    @Override
    public String toString() {
        return "ConstructorInjectionRide{" +
                "rideName='" + rideName + '\'' +
                ", cc_of_engine=" + cc_of_engine +
                ", mileage_Of_engine=" + mileage_Of_engine +
                ", color='" + color + '\'' +
                '}';
    }
}
