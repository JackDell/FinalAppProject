package com.example.ahame_000.seg2105.databasing;

import android.database.Cursor;

import com.example.ahame_000.seg2105.*;

import java.util.ArrayList;
import java.util.List;

// --------------------------
//  Written by: Jack Dell
// --------------------------

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
        String[] values = {account.toString(), account.getEmail(), account.getName(), account.getPassword()};
        DB_Helper.getWritableDatabase().execSQL("INSERT INTO Accounts VALUES (summary, email, name, pass)", values);
    }

    public Account getAccount(String accountString) {
        String select = "SELECT * FROM Accounts WHERE summary = '" + accountString + "'";
        Cursor c = DB_Helper.getReadableDatabase().rawQuery(select, new String[]{});
        if(c == null) return null;
        if(c.moveToFirst() == false) return null;
        String name = c.getString(c.getColumnIndex("name"));
        String email = c.getString(c.getColumnIndex("email"));
        String password = c.getString(c.getColumnIndex("pass"));

        for(Account acc : this.getDatabasedAccounts()) {
            if(acc.getName() == name && acc.getEmail() == email && acc.getPassword() == password) return acc;
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
                String name = c.getString(c.getColumnIndex("name"));
                String pass = c.getString(c.getColumnIndex("pass"));
                accounts.add(new Account(email, name, pass));
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
        String[] values = {profile.toString(), profile.getName(), profile.getPassword(), profile.getIcon().toString(), profile.getAccount().toString()};
        DB_Helper.getWritableDatabase().execSQL("INSERT INTO Profiles VALUES (summary, name, pass, icon, account)", values);
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
                String summary = c.getString(c.getColumnIndex("kind"));
                String name = c.getString(c.getColumnIndex("name"));
                String password = c.getString(c.getColumnIndex("pass"));
                String icon = c.getString(c.getColumnIndex("icon"));
                int points = c.getInt(c.getColumnIndex("points"));
                String account = c.getString(c.getColumnIndex("account"));
                if(summary.contains("Parent")) {
                    profiles.add(new Parent(name, password, icon, points, this.getAccount(account), new ArrayList<Chore>()));
                }
                else {
                    profiles.add(new Child(name, password, icon, points, this.getAccount(account), new ArrayList<Chore>()));
                }
            }
        }
        finally {
            c.close();
        }

        return profiles;
    }


    public boolean loginAccount(String email, String password) {

        for(Account account : this.getDatabasedAccounts()) {
            if(account.getEmail().equals(email) && account.getPassword().equals(password)) {
                Session.setAccount(account);
                return true;
            }
        }
        return false;
    }

    public boolean loginProfile(String name, String password) {

        for(Profile profile : this.getDatabasedProfiles()) {
            if(profile.getName().equals(name) && profile.getPassword().equals(password)) {
                Session.setProfile(profile);
                return true;
            }
        }
        return false;
    }
}