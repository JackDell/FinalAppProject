package com.example.ahame_000.seg2105.DataStructures;

import java.util.List;



public class Adult extends Profile {

    /**
     * Constructor
     */
    public Adult(String name, String password, int points, Account account, List<Chore> chores) {
        super(name, password, points, account, chores);
    }

    /**
     * Compressed Constructor
     */
    public Adult(String name, String password, Account account) {
        super(name, password, account );
    }
}
