package org.goit.task1.user;

public class MyCustomUser {

    private MyCustomUser() {
    }

    public static final User USER = new User(
            "James May",
            "captain_slow",
            "james.may@email.com",
            new Address("Homemade street 7",
                    "Apt. 007",
                    "London",
                    "1000 50",
                    new Geo(98.77,
                            -15.4960)),
            "+38 999 399 777",
            "daciasandero.com",
            new Company("Sandero Inc.",
                    "That's the Dacia Sandero!",
                    "Good News"));
}