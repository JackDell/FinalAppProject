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
    public Chore(String name, String description, Date completedDate, Date deadline, Adult creator, Profile assignedTo, int reward,
                 int penalty, Account account, UUID id) {
        this.name = name;
        this.description = description;
        this.state = ChoreState.UNASSIGNED;
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
     */
    public Chore(String name, String description, Date deadline, int reward, Account account) {
        this( name, description, null, deadline, null, null, reward, 0, account, UUID.randomUUID());
    }

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

    private boolean setState(ChoreState aState)
    {
        boolean wasSet = false;
        state = aState;
        wasSet = true;
        return wasSet;
    }

    private boolean setName(String aName)
    {
        boolean wasSet = false;
        name = aName;
        wasSet = true;
        return wasSet;
    }

    private boolean setDescription(String aDescription) {
        boolean wasSet = false;
        description = aDescription;
        wasSet = true;
        return wasSet;
    }
    private boolean setDeadline(Date aDeadline) {
        boolean wasSet = false;
        deadline = aDeadline;
        wasSet = true;
        return wasSet;
    }

    private boolean setPenalty(int aPenalty) {
        boolean wasSet = false;
        penalty = aPenalty;
        wasSet = true;
        return wasSet;
    }

    private boolean setReward(int aReward) {
        boolean wasSet = false;
        reward = aReward;
        wasSet = true;
        return wasSet;
    }

    public boolean setCompletedDate(Date aCompletedDate){
        boolean wasSet = false;
        completedDate = aCompletedDate;
        wasSet = true;
        return wasSet;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public void setAccount(Account account) {
        this.account = account;
    }


    //------------------------
    // METHOD
    //------------------------

    // changing state to TO-DO once assigned
    public boolean assign(){
        if (state == ChoreState.UNASSIGNED){
            state = ChoreState.TODO;

            return true;
        }
        return false;
    }

    // changing state from todo and pastdue to completed
    public boolean complete(){
        if (state == ChoreState.TODO || state == ChoreState.PASTDUE){
            state = ChoreState.COMPLETED;
            return true;
        }



        return false;
    }

    //changing state to pastdue when late
    public  boolean isLate(){ return isLate(new Date()); }

    private boolean isLate(Date today){
        if (state == ChoreState.PASTDUE)
            return true;
        if (state == ChoreState.TODO && today.after(deadline)){
            state = ChoreState.PASTDUE;
            return true;
        }

        return false;

    }

    public boolean setParent(Adult aAdult) {
        boolean wasSet = false;
        if (aAdult == null) {
            return wasSet;
        }


        Adult existingAdult = creator;
        creator = aAdult;
        if (existingAdult != null && !existingAdult.equals(aAdult)) {
            existingAdult.removeChore(this);
        }
        creator.addChore(this);
        wasSet = true;
        return wasSet;
    }


    public boolean setAssignedTo(Profile aProfile)
    {
        boolean wasSet = false;
        if (aProfile == null)
        {
            return wasSet;
        }
        Profile existingProfile = assignedTo;
        assignedTo = aProfile;
        if (existingProfile != null &&
                !existingProfile.equals(aProfile))
        {

            existingProfile.removeChore(this);

        }
        assignedTo.addChore(this);
        wasSet = true;
        return wasSet;
    }

    public boolean delete() {
        if (state == ChoreState.COMPLETED) {
            state = ChoreState.DELETED;
            this.assignedTo = null;
            return true;
        }
        if (state == ChoreState.TODO || state == ChoreState.PASTDUE) {
            state = ChoreState.UNASSIGNED;
            this.assignedTo = null;
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
}