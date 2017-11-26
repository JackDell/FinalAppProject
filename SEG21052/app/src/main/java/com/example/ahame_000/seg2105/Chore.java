package com.example.ahame_000.seg2105;
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/


import java.sql.Date;


public class Chore
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  public enum State  {UNASSIGNED , TODO, PASTDUE, COMPLETED, DELETED};

  //Chore Attributes
  public State state;
  public String name;
  public String description;
  public Date deadline;
  public int penalty;
  public int reward;
  public Date completedDate;

  //Chore Associations
  private Parent creator;
  private Account account;
  private Profile assignedTo;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Chore( String aName, String aDescription, Date aDeadline, int aPenalty, int aReward, Date aCompletedDate, Parent aParent, Account aAccount, Profile assignedTo)
  {
    state = State.UNASSIGNED;
    name = aName;
    description = aDescription;
    deadline = aDeadline;
    penalty = aPenalty;
    reward = aReward;
    completedDate = aCompletedDate;
    boolean didAddParent = setParent(aParent);
    if (!didAddParent)
    {
      throw new RuntimeException("Unable to create chore due to creator");
    }
    boolean didAddAccount = setAccount(aAccount);
    if (!didAddAccount)
    {
      throw new RuntimeException("Unable to create chore due to account");
    }
    boolean didAddProfile = setProfile(assignedTo);
    if (!didAddProfile)
    {
      throw new RuntimeException("Unable to create chore due to assignedTo");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  private boolean setState(State aState)
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

  private boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  private boolean setDeadline(Date aDeadline)
  {
    boolean wasSet = false;
    deadline = aDeadline;
    wasSet = true;
    return wasSet;
  }

  private boolean setPenalty(int aPenalty)
  {
    boolean wasSet = false;
    penalty = aPenalty;
    wasSet = true;
    return wasSet;
  }

  private boolean setReward(int aReward)
  {
    boolean wasSet = false;
    reward = aReward;
    wasSet = true;
    return wasSet;
  }

  public boolean setCompletedDate(Date aCompletedDate)
  {
    boolean wasSet = false;
    completedDate = aCompletedDate;
    wasSet = true;
    return wasSet;
  }

  public State getState()
  {
    return state;
  }

  public String getName()
  {
    return name;
  }

  public String getDescription()
  {
    return description;
  }

  public Date getDeadline()
  {
    return deadline;
  }

  public int getPenalty()
  {
    return penalty;
  }

  public int getReward()
  {
    return reward;
  }

  public Date getCompletedDate()
  {
    return completedDate;
  }

  public Parent getCreator()
  {
    return creator;
  }

  public Account getAccount()
  {
    return account;
  }

  public Profile getAssignedTo()
  {
    return assignedTo;
  }

  //------------------------
  // METHOD
  //------------------------

  // changing state to TODO once assigned
  public boolean assign(){
    if (state == State.UNASSIGNED){
         state = State.TODO;

         return true;
          }
          return false;
  }

// changing state from todo and pastdue to completed
  public boolean complete(){
    if (state == State.TODO || state == State.PASTDUE){
      state = State.COMPLETED;
      return true;
    }

    return false;
  }

  //changing state to pastdue when late
  public boolean islate(Date today){

    if (state == State.TODO && today.after(deadline)){
      state = State.PASTDUE;
      return true;
    }

    return false;

  }

   public boolean setParent(Parent aParent)
  {
    boolean wasSet = false;
    if (aParent == null)
    {
      return wasSet;
    }

    Parent existingParent = creator;
    creator = aParent;
    if (existingParent != null && !existingParent.equals(aParent))
    {
      existingParent.removeChore(this);
    }
    creator.addChore(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setAccount(Account aAccount)
  {
    boolean wasSet = false;
    if (aAccount == null)
    {
      return wasSet;
    }

    Account existingAccount = account;
    account = aAccount;
    if (existingAccount != null && !existingAccount.equals(aAccount))
    {
      existingAccount.removeChore(this);
    }
    account.addChore(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setProfile(Profile aProfile)
  {
    boolean wasSet = false;
    if (aProfile == null)
    {
      return wasSet;
    }

    Profile existingProfile = assignedTo;
    assignedTo = aProfile;
    if (existingProfile != null && !existingProfile.equals(aProfile))
    {
      existingProfile.removeChore(this);
    }
    assignedTo.addChore(this);
    wasSet = true;
    return wasSet;
  }

  public boolean delete()
  { if (state == State.COMPLETED){
      state = State.DELETED;
      this.assignedTo = null;
       return true;
  }

    if(state == State.TODO || state == State.PASTDUE){
      state = State.UNASSIGNED;
      this.assignedTo = null;
      return  true;
    }
    return false;

  }


  public String toString()
  {
    return super.toString() + "["+
            "state" + ":" + getState()+ "," +
            "name" + ":" + getName()+ "," +
            "description" + ":" + getDescription()+ "," +
            "penalty" + ":" + getPenalty()+ "," +
            "reward" + ":" + getReward()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "deadline" + "=" + (getDeadline() != null ? !getDeadline().equals(this)  ? getDeadline().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "completedDate" + "=" + (getCompletedDate() != null ? !getCompletedDate().equals(this)  ? getCompletedDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "creator = "+(getCreator()!=null?Integer.toHexString(System.identityHashCode(getCreator())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "account = "+(getAccount()!=null?Integer.toHexString(System.identityHashCode(getAccount())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assignedTo = "+(getAssignedTo()!=null?Integer.toHexString(System.identityHashCode(getAssignedTo())):"null");
  }
}