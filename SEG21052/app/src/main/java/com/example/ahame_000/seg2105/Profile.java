package com.example.ahame_000.seg2105;
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/


import android.graphics.drawable.Icon;

import java.util.*;
import java.sql.Date;

// line 14 "model.ump"
// line 74 "model.ump"
public class Profile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Profile Attributes
  private String name;
  private Icon icon;
  private String password;

  //Profile Associations
  private List<Chore> chores;
  private Account account;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Profile(String aName, Icon aIcon, String aPassword, Account aAccount)
  {
    name = aName;
    icon = aIcon;
    password = aPassword;
    chores = new ArrayList<Chore>();
    boolean didAddAccount = setAccount(aAccount);
    if (!didAddAccount)
    {
      throw new RuntimeException("Unable to create profile due to account");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setIcon(Icon aIcon)
  {
    boolean wasSet = false;
    icon = aIcon;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public Icon getIcon()
  {
    return icon;
  }

  public String getPassword()
  {
    return password;
  }

  public Chore getChore(int index)
  {
    Chore aChore = chores.get(index);
    return aChore;
  }

  public List<Chore> getChores()
  {
    List<Chore> newChores = Collections.unmodifiableList(chores);
    return newChores;
  }

  public int numberOfChores()
  {
    int number = chores.size();
    return number;
  }

  public boolean hasChores()
  {
    boolean has = chores.size() > 0;
    return has;
  }

  public int indexOfChore(Chore aChore)
  {
    int index = chores.indexOf(aChore);
    return index;
  }

  public Account getAccount()
  {
    return account;
  }

  public static int minimumNumberOfChores()
  {
    return 0;
  }

  public Chore addChore( String aName, String aDescription, Date aDeadline, int aPenalty, int aReward, Date aCompletedDate, Parent aParent, Account aAccount)
  {
    return new Chore( aName, aDescription, aDeadline, aPenalty, aReward, aCompletedDate, aParent, aAccount, this);
  }

  public boolean addChore(Chore aChore)
  {
    boolean wasAdded = false;
    if (chores.contains(aChore)) { return false; }
    Profile existingProfile = aChore.getAssignedTo();
    boolean isNewProfile = existingProfile != null && !this.equals(existingProfile);
    if (isNewProfile)
    {
      aChore.setAssignedTo(this);
    }
    else
    {
      chores.add(aChore);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeChore(Chore aChore)
  {
    boolean wasRemoved = false;
    //Unable to remove aChore, as it must always have a profile
    if (!this.equals(aChore.getAssignedTo()))
    {
      chores.remove(aChore);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addChoreAt(Chore aChore, int index)
  {  
    boolean wasAdded = false;
    if(addChore(aChore))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfChores()) { index = numberOfChores() - 1; }
      chores.remove(aChore);
      chores.add(index, aChore);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveChoreAt(Chore aChore, int index)
  {
    boolean wasAdded = false;
    if(chores.contains(aChore))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfChores()) { index = numberOfChores() - 1; }
      chores.remove(aChore);
      chores.add(index, aChore);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addChoreAt(aChore, index);
    }
    return wasAdded;
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
      existingAccount.removeProfile(this);
    }
    account.addProfile(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=chores.size(); i > 0; i--)
    {
      Chore aChore = chores.get(i - 1);
      aChore.delete();
    }
    Account placeholderAccount = account;
    this.account = null;
    placeholderAccount.removeProfile(this);
  }



  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "icon" + "=" + (getIcon() != null ? !getIcon().equals(this)  ? getIcon().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "account = "+(getAccount()!=null?Integer.toHexString(System.identityHashCode(getAccount())):"null");
  }
}