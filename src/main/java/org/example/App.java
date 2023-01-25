package org.example;

import org.example.Communication.Communication;
import org.example.config.MyConfig;
import org.example.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);



        String cookie = communication.getCookie();
        System.out.println(cookie);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie);
        User user = new User(3l, "James", "Brown", (byte) 10);
        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        communication.saveUser(httpEntity);

        User user1 = new User(3l, "Thomas", "Shelby", (byte) 15);
        HttpEntity<User> httpEntity1 = new HttpEntity<>(user1, headers);
        communication.updateUser(httpEntity1);

        HttpEntity<User> httpEntity2 = new HttpEntity<>(headers);
        communication.deleteUser(3, httpEntity2);
    }
}
