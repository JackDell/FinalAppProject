package com.example.ahame_000.seg2105;
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/


import android.graphics.drawable.Icon;

import java.util.*;
import java.sql.Date;


public abstract class Profile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Profile Attributes
  protected String name;
  protected Icon icon;
  protected String password;

  //Profile Associations
  protected List<Chore> chores;
  protected Account account;

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

  private boolean setPassword(String aPassword)
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

  private String getPassword()
  {
    return password;
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


  public Account getAccount()
  {
    return account;
  }

  public Chore addChore( String aName, String aDescription, Date aDeadline, int aPenalty, int aReward, Date aCompletedDate, Parent aParent, Account aAccount)
  {
    return new Chore( aName, aDescription, aDeadline, aPenalty, aReward, aCompletedDate, aParent, aAccount, this);
  }

  public boolean addChore(Chore aChore)
  {
    boolean wasAdded = false;
    if (chores.contains(aChore)) { return false; }
    Profile existingProfile = aChore.getProfile();
    boolean isNewProfile = existingProfile != null && !this.equals(existingProfile);
    if (isNewProfile)
    {
      aChore.setProfile(this);
    }
    else
    {
      chores.add(aChore);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeChore(Chore aChore) {
    boolean wasRemoved = false;
    if (!this.equals(aChore.getProfile())) {
      chores.remove(aChore);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  private boolean setAccount(Account aAccount)
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
    for(Chore aChore: chores)
    {
      //Todo:
    if(aChore.getState()=State.UNASSIGED) {
    }
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