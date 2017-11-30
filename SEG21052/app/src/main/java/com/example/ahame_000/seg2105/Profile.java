package com.example.ahame_000.seg2105;
<<<<<<< HEAD
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
    Profile existingProfile = aChore.getAssignedTo();
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


    if (!this.equals(aChore.getAssignedTo()))
    {

      chores.remove(aChore);
      wasRemoved = true;
    }
    return wasRemoved;
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
    for(Chore aChore: chores) {
      aChore.getState();
    }

    Account placeholderAccount = account;
    this.account = null;
    placeholderAccount.removeProfile(this);
  }

=======
>>>>>>> 2052eebb79db5df0fdba4c0a8751795cb7bf0751

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
        this.chores =  new ArrayList<Chore>();
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
