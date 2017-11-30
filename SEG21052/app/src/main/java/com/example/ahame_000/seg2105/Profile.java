package com.example.ahame_000.seg2105;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Profile {

    // Instance Variables
    private String name;
    private String password;
    private int points;
    private Account account;
    private List<Chore> chores;

    /**
     * Constructor
     */
    protected Profile(String name, String password, int points, Account account, List<Chore> chores) {
        this.name = name;
        this.password = password;
        this.points = points;
        this.account = account;
        this.chores = chores;
    }

    protected Profile(String name, String password,  Account account) {
        this.name = name;
        this.password = password;
        this.points = 0;
        this.account = account;
        this.chores =  new ArrayList<>();
    }

    /**
     * @return a String object containing the name of the account
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name a String object containing the name in which the profile name will be set to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return a String object containing the password of the account
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password a String object containing the password in which the profile password will be set to
     */
    private void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the amount of points that the profile has
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * @param points the amount of points the profile will be set to
     */
    private void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return an Account object, the account associated with the Profile
     */
    public Account getAccount() {
        return this.account;
    }

    /**
     * @param account an Account object in which you want to associate with the Profile
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * @return a List containing the Profile's Chores
     */
    public List<Chore> getAllChores() {
        return this.chores;
    }


    /**
     * @return a List containing the Profile's Chores that have not been completed yet
     */
    public List<Chore> getTodoChores() {
        LinkedList<Chore> todoChores = new LinkedList<Chore>();
        for (Chore chore:chores){
            chore.isLate();//check and update if chore is late
            if(chore.getState()==ChoreState.TODO ||chore.getState()==ChoreState.PASTDUE)
              todoChores.addLast(chore);

        }
        Collections.sort(todoChores);
        return todoChores;
    }

    /**
     * @return a List containing the Profile's Chores that have been completed
     */
    public List<Chore> getCompletedChores() {
        LinkedList<Chore> completedChores = new LinkedList<Chore>();
        for (Chore chore:chores){
            chore.isLate();//check and update if chore is late
            if(chore.getState()==ChoreState.COMPLETED)
                completedChores.addLast(chore);

        }
        return completedChores;
    }

    /**
     * @param chores a List of chores that you want to set as the Profile's Chores
     */
    public void setChores(List<Chore> chores) {
        this.chores = chores;
    }

    /**
     * @param chore the Chore that you want to add to the Profile
     */
    public void addChore(Chore chore) {
        if(!(this.chores.contains(chore))) this.chores.add(chore);
    }

    /**
     * @param chore the Chore you wish to remove from the Profile
     */
    public void removeChore(Chore chore) {
        this.chores.remove(chore);
    }

    /**
     * @return a String version of the profile summarizing important information
     */
}
