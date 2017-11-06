package ca.uottawa.jackdell.choreapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2017-11-05.
 */

public class Account {

    private String name;
    private String email;
    private String password;
    private List<Profile> profiles;
    private List<Chore> generalChores;

    public Account(String name, String email, String password) {
        this(name, email, password, new ArrayList<Profile>(), new ArrayList<Chore>());
    }

    public Account(String name, String email, String password, List<Profile> profiles, List<Chore> chores) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profiles = profiles;
        this.generalChores = chores;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public List<Profile> getProfiles() {
        return this.profiles;
    }

    public List<Chore> getGeneralChores() {
        return this.generalChores;
    }

    public void addChore(Chore chore) {
        if(!(this.generalChores.contains(chore))) this.generalChores.add(chore);
    }

    public void removeChore(Chore chore) {
        if(this.generalChores.contains(chore)) this.generalChores.remove(chore);
    }

    public String toString() {
        return ("Account(" + this.name + ", " + this.email + ")");
    }
}
