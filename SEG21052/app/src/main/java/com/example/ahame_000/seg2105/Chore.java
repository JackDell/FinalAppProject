package com.example.ahame_000.seg2105;


import android.support.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

public class Chore implements Comparable {

    // Instance variables
    private String name;
    private String description;
    private ChoreState state;
    private Date deadline;
    private int penalty;
    private int reward;
    private Date completedDate;


    private Adult creator;
    private Profile assignedTo;
    private Account account;
    private UUID id;

    /**
     * Constructor
     */
    public Chore(String name, String description, ChoreState state, Date completedDate, Date deadline, Adult creator, Profile assignedTo, int reward,
                 int penalty, Account account, UUID id) {
        this.name = name;
        this.description = description;
        this.state = state;
        this.deadline = deadline;
        this.creator = creator;
        this.assignedTo = assignedTo;
        this.penalty = penalty;
        this.reward = reward;
        this.account = account;
        this.completedDate = completedDate;
        this.id = id;
    }

    /**
     * Compressed Constructor
     *
     */
    public Chore(String name, String description, Date deadline, int reward, Account account) {
        this( name, description, ChoreState.UNASSIGNED, null, deadline, null, null, reward, 0, account, UUID.randomUUID());
    }

    /**
     * Getters
     */

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ChoreState getState() {
        return state;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public Date getDeadline(){
        return deadline;
    }

    public int getPenalty(){
        return penalty;
    }

    public int getReward(){
        return reward;
    }

    public UUID getId() {
        return id;
    }

    public String getStringId() {
        return this.id.toString();
    }

    public Adult getCreator(){
        return creator;
    }

    public Profile getAssignedTo(){
        return assignedTo;
    }

    public Account getAccount() {
        return this.account;
    }


    /**
     * Setters
     *
     */


    public void setState(ChoreState aState) {
        state = aState;
    }

    public void setName(String aName){
        name = aName;
    }

    public void setDescription(String aDescription) {
        description = aDescription;
    }
    public boolean setDeadline(Date aDeadline) {
        boolean wasSet = false;
        deadline = aDeadline;
        wasSet = true;
        return wasSet;
    }

    public void setPenalty(int aPenalty) {
        penalty = aPenalty;
    }

    public void setReward(int aReward) {
        reward = aReward;
    }

    public void setCompletedDate(Date aCompletedDate){
        completedDate = aCompletedDate;
        state = ChoreState.COMPLETED;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public void setCreator(Adult creator) {
        this.creator = creator;
    }



    public void setAssignedTo(Profile aProfile){
        assignedTo = aProfile;
    }




    //------------------------
    // METHOD
    //------------------------

    /**
     * Only if Chore state is UNASSIGNED, the state of chore can be changed to TO-DO
     * @return true
     * else if the Chore state is anything else
     * @return false
     */
    public boolean assign(){
        if (state == ChoreState.UNASSIGNED){
            state = ChoreState.TODO;

            return true;
        }
        return false;
    }



    /**
     * Only if the Chore state is TO-DO or PAST DUE
     * the Chore state can be changed to COMPLETED
     * @return true
     * Otherwise,
     * @return false;
     */

    public boolean complete(){
        if (state == ChoreState.TODO || state == ChoreState.PASTDUE){
            state = ChoreState.COMPLETED;
            return true;
        }
        return false;
    }

    //changing state to pastdue when late
    public  boolean isLate() {
        return isLate(new Date());
    }


    /**
     * When Chore state is PASTDUE, the method
     * @return true
     * When Chore state is today and the today's date is after the deadline
     * Chore state is changed  to PASTDUE
     * @param today
     * @return true
     * in any other case,
     * @return false
     *
     */

    private boolean isLate(Date today){
        if (state == ChoreState.PASTDUE)
            return true;
        if (state == ChoreState.TODO && today.after(deadline)){
            state = ChoreState.PASTDUE;
            return true;
        }

        return false;

    }
    @Override
    public int compareTo(@NonNull Object o) {
        Chore oo = (Chore) o;
        if(this.getDeadline().before(oo.getDeadline()))
            return -1;
        if (this.getDeadline().after(oo.getDeadline()))
            return 1;
        return 0;

    }

    // Determines the amount of reward points to give depending on today's date
    public int getTodaysReward() {
        if(isLate()) {
            return (reward - penalty);
        }

        return reward;
    }

}