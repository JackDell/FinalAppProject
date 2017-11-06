package ca.uottawa.jackdell.choreapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2017-11-05.
 */

public class Child extends Profile {

    public Child(String name, String email, String password, int points, Account account) {
        this(name, email, password, account, points, new ArrayList<Chore>());
    }

    public Child(String name, String email, String password, Account account, int points, List<Chore> chores) {
        super(name, email, password, account, points, chores);
    }
}
