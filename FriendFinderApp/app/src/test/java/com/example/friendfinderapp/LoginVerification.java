package com.example.friendfinderapp;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginVerification {
    String email = "coba@gmail.com";
    String password = "123";

    public void validData() {
        if (email.length() > 0 && password.length() > 0) {
            System.out.println("Data valid");
        } else {
            System.out.println("Data tidak valid");
        }
    }
}
