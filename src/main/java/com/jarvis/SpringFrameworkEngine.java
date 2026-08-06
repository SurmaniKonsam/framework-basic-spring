//package com.jarvis;
//
//import com.jarvis.Beans.ConstructorInjectionRide;
//import com.jarvis.Beans.MyRide;
//import com.jarvis.config.AppConfig;
//import com.jarvis.service.ComponentValueAssignment;
//import com.jarvis.service.UserService;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//

//public class Main {
//
//    public static void main(String[] args) {
//        ApplicationContext context =
//                new AnnotationConfigApplicationContext(AppConfig.class);
//        //context = new ClassPathXmlApplicationContextontext("beans.xml");
//
//        /**
//         * Bean definition, to define what is the return type of the bean being called or retrieved.
//         */
//        /*
//        MyRide myRide =
//                context.getBean(MyRide.class);
//
//         */
//        UserService userService = context.getBean(UserService.class);
//        System.out.println(userService);
//
//        /**
//         * Regular setter method.
//         */
//        ComponentValueAssignment componentValueAssignment = context.getBean(ComponentValueAssignment.class);
//        System.out.println(componentValueAssignment);
//
//
//        /*
//        ConstructorInjectionRide constructorInjectionRide = context.getBean(ConstructorInjectionRide.class);
//         */
//
//        /**
//         * This is pure bean configuration
//         */
//
//
//    }
//}