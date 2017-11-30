package com.example.ahame_000.seg2105;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


public class Account {

    // Instance Variables
    private String email;
    private String password;
    private List<Profile> profiles;
    private List<Chore> chores;

    /**
     * Constructor
     */
    public Account(String email, String password, List<Profile> profiles, List<Chore> chores) {
        this.email = email;
        this.password = password;
        this.profiles = profiles;
        this.chores = chores;
    }

    /**
     * Compressed Constructor
     */
    public Account(String email, String password) {
        this(email, password, new ArrayList<Profile>(), new ArrayList<Chore>());
    }

    /**
     * @return a String object containing the email of the Account
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email a String object containing the email in which you want to set as the Account email
     */
    private void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return a String object containing the password associated with the account
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password a String object containing the password that you want to set as the Account password
     */
    private void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return a List object containing the profiles associated with the Account
     */
    public List<Profile> getProfiles() {
        return this.profiles;
    }

    /**
     * @param profiles a List object containing all the profiles you want the Account to be associated with
     */
    private void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    /**
     * @return a List object containing all of the chores associated with the Account
     */
    public List<Chore> getAllChores() {
        return this.chores;
    }

    /**
     * @return a List object containing all of the 'unassigned chores', the chores associated with the Account
     */
    public List<Chore> getUnassignedChores() {
        ArrayList<Chore> unassignedChores = new ArrayList<Chore>();
        for (Chore chore : chores) {
            if (chore.getState() == ChoreState.UNASSIGNED) {
                unassignedChores.add(chore);
            }
        }
        Collections.sort(unassignedChores);
        return unassignedChores;
    }

    /**
     * @param chores a List object containing all the chores that you want to set as the 'community chores'
     */
    private void setChores(List<Chore> chores) {
        this.chores = chores;
    }

    /**
     * @param chore the Chore that you want to add to the Account
     */
    public void addChore(Chore chore) {
        if (!(this.chores.contains(chore))) this.chores.add(chore);
    }

    /**
     * @param chore the Chore you wish to remove from the Account
     */
    public void removeChore(Chore chore) {
        this.chores.remove(chore);
    }


    public Chore getChore(UUID id) {

        for (Chore chore : chores) {
            if (chore.getId().equals(id)) {
                return chore;
            }
        }

        return null;

    }
     public List<Profile> getChildren(){
        //TODO
         return null;
     }

}
