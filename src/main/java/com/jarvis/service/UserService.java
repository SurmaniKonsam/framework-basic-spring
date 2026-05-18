package com.jarvis.service;


import org.springframework.stereotype.Component;

@Component
public class UserService {
    public void greet() {
        System.out.println("Hello from UserService");
    }
}