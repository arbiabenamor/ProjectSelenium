package com.qacart.todo.utils;
// on a utiliser le package de faker quinous permet d'utiliser fake username ,passw,email,...randomlyy

import com.github.javafaker.Faker;
import com.qacart.todo.objects.User;

public class UserUtils {
    public /*void*/ static User generateRandomUser() // le role tgenerili random user
    {
        String firstName = new Faker().name().firstName();
        String lastName = new Faker().name().lastName();
        String email = new Faker().internet().emailAddress();
        String password = new Faker().internet().password();

        User user = new User(firstName,lastName, email, password); // ce construcyteur elli 3malneh fil classe User
        return user;
    }

}
