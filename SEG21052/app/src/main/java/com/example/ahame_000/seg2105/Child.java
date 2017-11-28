package com.example.ahame_000.seg2105;

import com.example.ahame_000.seg2105.Account;
import com.example.ahame_000.seg2105.Chore;
import com.example.ahame_000.seg2105.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2017-11-28.
 */

public class Child extends Profile {

    /**
     * Constructor
     */
    public Child(String name, String password, String icon, int points, Account account, List<Chore> chores) {
        super(name, password, icon, points, account, chores);
    }

    /**
     * Compressed Constructor
     */
    public Child(String name, String password, String icon, Account account) {
        super(name, password, icon, 0, account, new ArrayList<Chore>());
    }
}
