package org.cashdash;

import org.cashdash.models.User;

public class Main {
    public static void main(String[] args) {
        User user = new User("testing", "testing2", "testing");

        user.register();
        user.login();
    }
}