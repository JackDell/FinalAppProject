package com.example.ahame_000.seg2105;
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/


import android.view.View;

// line 56 "model.ump"
// line 96 "model.ump"
public class DBmangment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

    private static DBmangment instance;

  //DBmangment Associations
  private Account account;

  //------------------------
  // CONSTRUCTOR
  //------------------------
    public DBmangment(){

    }

  public DBmangment(Account aAccount)
  {
    if (aAccount == null || aAccount.getDBmangment() != null)
    {
      throw new RuntimeException("Unable to create DBmangment due to aAccount");
    }
    account = aAccount;
  }

  public DBmangment( String aEmailForAccount, String aPasswordForAccount)
  {
    account = new Account( aEmailForAccount, aPasswordForAccount, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Account getAccount(String email)
  {

    return account;
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



  public static boolean verifyAccount(String email, String password) {
    return false;
  }

  public static DBmangment getInstance(){
      if (instance == null){
        instance = new DBmangment();
      }
      return instance;
  }
}