package com.example.ahame_000.seg2105;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2017-11-28.
 */

public class Parent extends Profile {

    /**
     * Constructor
     */
    public Parent(String name, String password, String icon, int points, Account account, List<Chore> chores) {
        super(name, password, icon, points, account, chores);
    }

    /**
     * Compressed Constructor
     */
    public Parent(String name, String password, String icon, Account account) {
        super(name, password, icon, 0, account, new ArrayList<Chore>());
    }
}
