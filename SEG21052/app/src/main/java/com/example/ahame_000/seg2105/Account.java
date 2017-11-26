package com.example.ahame_000.seg2105;

/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/


import android.graphics.drawable.Icon;

import java.util.*;
import java.sql.Date;

// line 2 "model.ump"
// line 67 "model.ump"
public class Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Account Attributes
  private String name;
  private String email;
  private String password;

  //Account Associations
  private List<Profile> profiles;
  private List<Chore> chores;
  private DBmangment dBmangment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Account(String aName, String aEmail, String aPassword, DBmangment aDBmangment)
  {
    name = aName;
    email = aEmail;
    password = aPassword;
    profiles = new ArrayList<Profile>();
    chores = new ArrayList<Chore>();
    if (aDBmangment == null || aDBmangment.getAccount() != null)
    {
      throw new RuntimeException("Unable to create Account due to aDBmangment");
    }
    dBmangment = aDBmangment;
  }

  public Account(String aName, String aEmail, String aPassword)
  {
    name = aName;
    email = aEmail;
    password = aPassword;
    profiles = new ArrayList<Profile>();
    chores = new ArrayList<Chore>();
    dBmangment = new DBmangment(this);
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

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
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

  public String getEmail()
  {
    return email;
  }

  private String getPassword()
  {
    return password;
  }

  public Profile getProfile(int index)
  {
    Profile aProfile = profiles.get(index);
    return aProfile;
  }

  public List<Profile> getProfiles()
  {
    List<Profile> newProfiles = Collections.unmodifiableList(profiles);
    return newProfiles;
  }

  public int numberOfProfiles()
  {
    int number = profiles.size();
    return number;
  }

  public boolean hasProfiles()
  {
    boolean has = profiles.size() > 0;
    return has;
  }

  public int indexOfProfile(Profile aProfile)
  {
    int index = profiles.indexOf(aProfile);
    return index;
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

  public DBmangment getDBmangment()
  {
    return dBmangment;
  }

  public static int minimumNumberOfProfiles()
  {
    return 0;
  }

  public Profile addProfile(String aName, Icon aIcon, String aPassword)
  {
    return new Profile(aName, aIcon, aPassword, this);
  }

  public boolean addProfile(Profile aProfile)
  {
    boolean wasAdded = false;
    if (profiles.contains(aProfile)) { return false; }
    Account existingAccount = aProfile.getAccount();
    boolean isNewAccount = existingAccount != null && !this.equals(existingAccount);
    if (isNewAccount)
    {
      aProfile.setAccount(this);
    }
    else
    {
      profiles.add(aProfile);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeProfile(Profile aProfile)
  {
    boolean wasRemoved = false;
    //Unable to remove aProfile, as it must always have a account
    if (!this.equals(aProfile.getAccount()))
    {
      profiles.remove(aProfile);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addProfileAt(Profile aProfile, int index)
  {  
    boolean wasAdded = false;
    if(addProfile(aProfile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProfiles()) { index = numberOfProfiles() - 1; }
      profiles.remove(aProfile);
      profiles.add(index, aProfile);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveProfileAt(Profile aProfile, int index)
  {
    boolean wasAdded = false;
    if(profiles.contains(aProfile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProfiles()) { index = numberOfProfiles() - 1; }
      profiles.remove(aProfile);
      profiles.add(index, aProfile);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addProfileAt(aProfile, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfChores()
  {
    return 0;
  }

  public Chore addChore( String aName, String aDescription, Date aDeadline, int aPenalty, int aReward, Date aCompletedDate, Parent aParent, Profile aProfile)
  {
    return new Chore( aName, aDescription, aDeadline, aPenalty, aReward, aCompletedDate, aParent, this, aProfile);
  }

  public boolean addChore(Chore aChore)
  {
    boolean wasAdded = false;
    if (chores.contains(aChore)) { return false; }
    Account existingAccount = aChore.getAccount();
    boolean isNewAccount = existingAccount != null && !this.equals(existingAccount);
    if (isNewAccount)
    {
      aChore.setAccount(this);
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
    //Unable to remove aChore, as it must always have a account
    if (!this.equals(aChore.getAccount()))
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

  public void delete()
  {
    for(int i=profiles.size(); i > 0; i--)
    {
      Profile aProfile = profiles.get(i - 1);
      aProfile.delete();
    }
    for(int i=chores.size(); i > 0; i--)
    {
      Chore aChore = chores.get(i - 1);
      aChore.delete();
    }
    DBmangment existingDBmangment = dBmangment;
    dBmangment = null;
    if (existingDBmangment != null)
    {
      existingDBmangment.delete();
    }
  }




  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "dBmangment = "+(getDBmangment()!=null?Integer.toHexString(System.identityHashCode(getDBmangment())):"null");
  }
}

