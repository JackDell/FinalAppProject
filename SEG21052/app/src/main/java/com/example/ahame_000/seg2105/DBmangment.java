package com.example.ahame_000.seg2105;
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/


import android.view.View;

public class DBmangment
{



  private static Profile profile;
  //------------------------
  // MEMBER VARIABLES
  //------------------------

    private static DBmangment instance;

  //DBmangment Associations
  private static Account account;

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
  public Account getAccount()

  {

    return account;
  }

  public Account getAccount(String email)

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

  public string loadProfiles(Resources res) {
    InputStream is = res.openRawResource(R.raw.profiles);
    Scanner scanner = new Scanner(is);
    StringBuilder builder = new StringBuilder();

    while(scanner.hasNextLine()) {
      builder.append(scanner.nextLine());
    }



    return parseJSON(builder.toString());
  }

  public String loadJSONFile(Resources res) {
    InputStream is = res.openRawResource(R.raw.example_2);
    Scanner scanner = new Scanner(is);
    StringBuilder builder = new StringBuilder();

    while(scanner.hasNextLine()) {
      builder.append(scanner.nextLine());
    }

    StringBuilder b= new StringBuilder();

    try {
      JSONObject root = new JSONObject(builder.toString());
      JSONArray profiles = (JSONArray) root.get("Profiles");
      for(int i = 0; i < profiles.length(); i++) {
        JSONObject name = (JSONObject) profiles
      }
    }
    catch(JSONException e) {
      e.printStackTrace();
    }

    return b.toString();
  }

  private String parseJSON(String s) {
    StringBuilder builder = new StringBuilder();

    try {
      JSONObject root = new JSONObject(s);
      JSONObject quiz = root.getJSONObject("quiz");
      JSONObject holder = quiz.getJSONObject("sport");
      holder = holder.getJSONObject("q1");
      builder.append("Q: ").append(holder.getString("question")).append("\n");
    }
    catch(JSONException e) {
      e.printStackTrace();
    }

    return builder.toString();
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