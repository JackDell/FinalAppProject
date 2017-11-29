package com.example.ahame_000.seg2105.databasing;

import android.database.Cursor;

import com.example.ahame_000.seg2105.*;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private DatabaseHelper DB_Helper;

    public DatabaseManager(DatabaseHelper DB_Helper) {
        this.DB_Helper = DB_Helper;
    }

    /**
     * This method takes an account object and saves its information to the database
     * @param account   the account object you wish to save to the database
     */
    public void saveAccount(Account account) {
        String[] values = { account.getEmail(),  account.getPassword()};
        DB_Helper.getWritableDatabase().execSQL("INSERT INTO Accounts VALUES (summary, email, pass)", values);
        loginAccount(account.getEmail(),  account.getPassword());
    }

    public Account getAccount(String accountEmail) {
        String select = "SELECT * FROM Accounts WHERE email = '" + accountEmail + "'";
        Cursor c = DB_Helper.getReadableDatabase().rawQuery(select, new String[]{});
        if(c == null) return null;
        if(c.moveToFirst() == false) return null;
        String email = c.getString(c.getColumnIndex("email"));
        String password = c.getString(c.getColumnIndex("pass"));

        for(Account acc : this.getDatabasedAccounts()) {
            if( acc.getEmail() == email && acc.getPassword() == password) return acc;
        }

        return null;
    }

    /**
     * Returns a List of all the accounts in the database
     * @return  a List of all the databased Accounts
     */
    public List<Account> getDatabasedAccounts() {
        // Creating the List object to fill
        List<Account> accounts = new ArrayList<>();

        // Points the SQL cursor at the accounts
        Cursor c = DB_Helper.getReadableDatabase().rawQuery("SELECT * FROM Accounts", new String[]{});

        // If the cursor object we attempted to create is null (db doesn't exist), or has a length of 0 (db exists but has no entries)
        // we want to return our empty list
        if(c == null) return accounts;
        if(c.getCount() == 0) return accounts;

        try {
            while(c.moveToNext()) {
                String email = c.getString(c.getColumnIndex("email"));
                String pass = c.getString(c.getColumnIndex("pass"));
                accounts.add(new Account(email, pass));
            }
        }
        finally {
            c.close();
        }

        return accounts;
    }

    /**
     * This method takes an profile object and saves its information to the database
     * @param profile   the profile object you wish to save to the database
     */
    public void saveProfile(Profile profile) {
        String kind = "";
        if(profile instanceof Adult){
            kind="Adult";
        }
        else if(profile instanceof Child){
            kind="Child";
        }

        String[] values = { profile.getName(), profile.getPassword(), kind, profile.getAccount().getEmail()};
        DB_Helper.getWritableDatabase().execSQL("INSERT INTO Profiles VALUES ( name, pass, kind, account)", values);
    }

    /**
     * Returns a List off all the databased profiles
     * @return  a List object containing all the profiles from the database
     */
    public List<Profile> getDatabasedProfiles() {
        // Creating the List object to fill
        List<Profile> profiles = new ArrayList<>();

        // Points the SQL cursor at the accounts
        Cursor c = DB_Helper.getReadableDatabase().rawQuery("SELECT * FROM Profile", null);

        // If the cursor object we attempted to create is null (db doesn't exist), or has a length of 0 (db exists but has no entries)
        // we want to return our empty list
        if(c == null) return profiles;
        if(c.getCount() == 0) return profiles;

        try {
            while(c.moveToNext()) {
                String name = c.getString(c.getColumnIndex("name"));
                String password = c.getString(c.getColumnIndex("pass"));
                int points = c.getInt(c.getColumnIndex("points"));
                String account = c.getString(c.getColumnIndex("account"));
                String kind = c.getString(c.getColumnIndex("kind"));
                if(kind=="Adult") {
                    profiles.add(new Adult(name, password,  points, this.getAccount(account), new ArrayList<Chore>()));
                }
                else if (kind == "Child"){
                    profiles.add(new Child(name, password, points, this.getAccount(account), new ArrayList<Chore>()));
                }
            }
        }
        finally {
            c.close();
        }

        return profiles;
    }
    public List<Chore> getChore(Account account){
        //TODO:
        return new ArrayList<Chore>();
    }
    public List<Chore> getTodoChore(Profile profile){
        //TODO:
        return new ArrayList<Chore>();
    }
    public List<Chore> getCompletedChore(Profile profile){
        //TODO:
        return new ArrayList<Chore>();
    }


    public boolean loginAccount(String email, String password) {

        for(Account account : this.getDatabasedAccounts()) {
            if(account.getEmail().equals(email) && account.getPassword().equals(password)) {
                Session.setLoggedInAccount(account);
                return true;
            }
        }
        return false;
    }

    public boolean loginProfile(String name, String password) {

        for(Profile profile : this.getDatabasedProfiles()) {
            if(profile.getName().equals(name) && profile.getPassword().equals(password)) {
                Session.setLoggedInProfile(profile);
                return true;
            }
        }
        return false;
    }
}