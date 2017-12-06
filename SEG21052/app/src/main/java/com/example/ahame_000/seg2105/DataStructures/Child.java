package com.example.ahame_000.seg2105.DataStructures;

import java.util.List;


public class Child extends Profile {

    /**
     * Constructor
     */
    public Child(String name, String password, int points, Account account, List<Chore> chores) {
        super(name, password, points, account, chores);
    }

    /**
     * Compressed Constructor
     */
    public Child(String name, String password, Account account) {
        super(name, password, account);
    }
}
