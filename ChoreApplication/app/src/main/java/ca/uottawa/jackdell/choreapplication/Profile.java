package ca.uottawa.jackdell.choreapplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jack on 2017-11-05.
 */

public class Profile {

    private String name;
    private String email;
    private String password;
    private Account account;
    private int points;
    private List<Chore> chores;

    public Profile(String name, String email, String password, Account account) {
        this(name, email, password, account, 0, new ArrayList<Chore>());
    }

    public Profile(String name, String email, String password, Account account, int points, List<Chore> chores) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.account = account;
        this.points = points;
        this.chores = chores;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<Chore> getChores() {
        return chores;
    }

    public void setChores(List<Chore> chores) {
        this.chores = chores;
    }

    public void addChore(Chore chore) {
        this.chores.add(chore);
    }

    public void removeChore(Chore chore) {
        this.chores.remove(chore);
    }
}
