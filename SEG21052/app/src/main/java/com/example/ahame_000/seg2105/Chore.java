package com.example.ahame_000.seg2105;
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/


import java.sql.Date;

// line 42 "model.ump"
// line 90 "model.ump"
public class Chore
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Chore Attributes
  private String state;
  private String name;
  private String description;
  private Date deadline;
  private int penalty;
  private int reward;
  private Date completedDate;

  //Chore Associations
  private Parent parent;
  private Account account;
  private Profile profile;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Chore(String aState, String aName, String aDescription, Date aDeadline, int aPenalty, int aReward, Date aCompletedDate, Parent aParent, Account aAccount, Profile aProfile)
  {
    state = aState;
    name = aName;
    description = aDescription;
    deadline = aDeadline;
    penalty = aPenalty;
    reward = aReward;
    completedDate = aCompletedDate;
    boolean didAddParent = setParent(aParent);
    if (!didAddParent)
    {
      throw new RuntimeException("Unable to create chore due to parent");
    }
    boolean didAddAccount = setAccount(aAccount);
    if (!didAddAccount)
    {
      throw new RuntimeException("Unable to create chore due to account");
    }
    boolean didAddProfile = setProfile(aProfile);
    if (!didAddProfile)
    {
      throw new RuntimeException("Unable to create chore due to profile");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setState(String aState)
  {
    boolean wasSet = false;
    state = aState;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setDeadline(Date aDeadline)
  {
    boolean wasSet = false;
    deadline = aDeadline;
    wasSet = true;
    return wasSet;
  }

  public boolean setPenalty(int aPenalty)
  {
    boolean wasSet = false;
    penalty = aPenalty;
    wasSet = true;
    return wasSet;
  }

  public boolean setReward(int aReward)
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

  public String getState()
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

  public Parent getParent()
  {
    return parent;
  }

  public Account getAccount()
  {
    return account;
  }

  public Profile getProfile()
  {
    return profile;
  }

  public boolean setParent(Parent aParent)
  {
    boolean wasSet = false;
    if (aParent == null)
    {
      return wasSet;
    }

    Parent existingParent = parent;
    parent = aParent;
    if (existingParent != null && !existingParent.equals(aParent))
    {
      existingParent.removeChore(this);
    }
    parent.addChore(this);
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

    Profile existingProfile = profile;
    profile = aProfile;
    if (existingProfile != null && !existingProfile.equals(aProfile))
    {
      existingProfile.removeChore(this);
    }
    profile.addChore(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Parent placeholderParent = parent;
    this.parent = null;
    placeholderParent.removeChore(this);
    Account placeholderAccount = account;
    this.account = null;
    placeholderAccount.removeChore(this);
    Profile placeholderProfile = profile;
    this.profile = null;
    placeholderProfile.removeChore(this);
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
            "  " + "parent = "+(getParent()!=null?Integer.toHexString(System.identityHashCode(getParent())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "account = "+(getAccount()!=null?Integer.toHexString(System.identityHashCode(getAccount())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "profile = "+(getProfile()!=null?Integer.toHexString(System.identityHashCode(getProfile())):"null");
  }
}