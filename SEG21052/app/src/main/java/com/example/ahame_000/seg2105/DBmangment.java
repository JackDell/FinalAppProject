package com.example.ahame_000.seg2105;
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/



public class DBmangment
{



  private static Profile profile;
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DBmangment Associations
  private static Account account;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DBmangment(Account aAccount)
  {
    if (aAccount == null || aAccount.getDBmangment() != null)
    {
      throw new RuntimeException("Unable to create DBmangment due to aAccount");
    }
    account = aAccount;
  }

  public DBmangment(String aNameForAccount, String aEmailForAccount, String aPasswordForAccount)
  {
    account = new Account(aNameForAccount, aEmailForAccount, aPasswordForAccount, this);
  }

  //------------------------
  // INTERFACE
  //------------------------


    //getters
  public Account getAccount()
  {
    return account;
  }

    public Profile getProfile() {
        return profile;
    }

    // setters
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

  public void delete()
  {
    Account existingAccount = account;
    account = null;
    if (existingAccount != null)
    {
      existingAccount.delete();
    }
  }


}