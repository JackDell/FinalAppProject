package com.example.ahame_000.seg2105;
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/


import android.graphics.drawable.Icon;

import java.util.*;
import java.sql.Date;

// line 26 "model.ump"
// line 80 "model.ump"
public class Parent extends Profile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Parent Associations
  private List<Chore> chores;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Parent(String aName, Icon aIcon, String aPassword, Account aAccount)
  {
    super(aName, aIcon, aPassword, aAccount);
    chores = new ArrayList<Chore>();
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public static int minimumNumberOfChores()
  {
    return 0;
  }

  public Chore addChore( String aName, String aDescription, Date aDeadline, int aPenalty, int aReward, Date aCompletedDate, Account aAccount, Profile aProfile)
  {
    return new Chore( aName, aDescription, aDeadline, aPenalty, aReward, aCompletedDate, this, aAccount, aProfile);
  }

  public boolean addChore(Chore aChore)
  {
    boolean wasAdded = false;
    if (chores.contains(aChore)) { return false; }
    Parent existingParent = aChore.getCreator();
    boolean isNewParent = existingParent != null && !this.equals(existingParent);
    if (isNewParent)
    {
      aChore.setParent(this);
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
    //Unable to remove aChore, as it must always have a parent
    if (!this.equals(aChore.getCreator()))
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
    for(int i=chores.size(); i > 0; i--)
    {
      Chore aChore = chores.get(i - 1);
      aChore.delete();
    }
    super.delete();
  }


}