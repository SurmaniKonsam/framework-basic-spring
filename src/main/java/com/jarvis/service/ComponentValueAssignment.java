//package com.jarvis.service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ComponentValueAssignment {
//    int age;
//    String name;
//    int right = 99;
//    String convertedRight;
//
//    $ -> the value is read from application.properties, but how?
//    @Value("${user.age}")
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//   And here you are directly giving the value of the parameter for the setName, or should we say the argument
//    The value is defined under @Value
//    @Value("Sample practice")
//    public void setName( String name) {
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//
//        return "ComponentValueAssignment{" +
//                "age=" + age +
//                ", name='" + name + '\'' +
//                '}';
//    }
//}
