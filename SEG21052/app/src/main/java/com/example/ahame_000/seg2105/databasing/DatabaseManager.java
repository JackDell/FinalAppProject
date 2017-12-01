package com.example.ahame_000.seg2105.databasing;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.ahame_000.seg2105.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        ContentValues values = new ContentValues();
        values.put("email", account.getEmail());
        values.put("password", account.getPassword());

        DB_Helper.getWritableDatabase().insert("Accounts", null, values);

        this.loginAccount(account.getEmail(),  account.getPassword());
    }

    public Account getAccount(String accountEmail) {
        String select = "SELECT * FROM Accounts WHERE email = '" + accountEmail + "'";
        Cursor c = DB_Helper.getReadableDatabase().rawQuery(select, new String[]{});
        if(c == null) return null;
        if(c.moveToFirst() == false) return null;
        String email = c.getString(c.getColumnIndex("email"));
        String password = c.getString(c.getColumnIndex("password"));

        for(Account acc : this.getDatabasedAccounts()) {
            if( acc.getEmail().equals(email) && acc.getPassword().equals(password)) return acc;
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
                String pass = c.getString(c.getColumnIndex("password"));
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
        String kind = "Adult";
        if(profile instanceof Child){
            kind = "Child";
        }

        ContentValues values = new ContentValues();
        values.put("kind", kind);
        values.put("name", profile.getName());
        values.put("password", profile.getPassword());
        values.put("points", profile.getPoints());
        values.put("accEmail", profile.getAccount().getEmail());

        DB_Helper.getWritableDatabase().insert("Profiles", null, values);
    }

    /**
     * Returns a List off all the databased profiles
     * @return  a List object containing all the profiles from the database
     */
    public List<Profile> getDatabasedProfiles() {
        // Creating the List object to fill
        List<Profile> profiles = new ArrayList<>();

        // Points the SQL cursor at the accounts
        Cursor c = DB_Helper.getReadableDatabase().rawQuery("SELECT * FROM Profiles", null);

        // If the cursor object we attempted to create is null (db doesn't exist), or has a length of 0 (db exists but has no entries)
        // we want to return our empty list
        if(c == null) return profiles;
        if(c.getCount() == 0) return profiles;

        try {
            while(c.moveToNext()) {
                String name = c.getString(c.getColumnIndex("name"));
                String password = c.getString(c.getColumnIndex("password"));
                int points = c.getInt(c.getColumnIndex("points"));
                String account = c.getString(c.getColumnIndex("account"));
                String kind = c.getString(c.getColumnIndex("kind"));
                if(kind.equals("Adult")) {
                    profiles.add(new Adult(name, password,  points, this.getAccount(account), new ArrayList<Chore>()));
                }
                else {
                    profiles.add(new Child(name, password, points, this.getAccount(account), new ArrayList<Chore>()));
                }
            }
        }
        finally {
            c.close();
        }

        return profiles;
    }

    public Profile getProfileByName(String name) {
        for(Profile profile : this.getDatabasedProfiles()) {
            if(profile.getName() == name) return profile;
        }

        return null;
    }

    //name TEXT, desc TEXT, completedDate DATE, deadline DATE, creator TEXT, assignedTo TEXT, reward INTEGER, penalty INTEGER)";


    public void saveChore(Chore chore) {

        if(this.getDatabasedChores().contains(chore)) return;

        ContentValues values = new ContentValues();
        values.put("id", chore.getStringId());
        values.put("name", chore.getName());
        values.put("desc", chore.getDescription());
        values.put("completedDate", chore.getCompletedDate().getTime());
        values.put("deadline", chore.getCompletedDate().getTime());
        values.put("creatorName", chore.getCreator().getName());
        values.put("assignedTo", chore.getAssignedTo().getName());
        values.put("reward", chore.getReward());
        values.put("penalty", chore.getPenalty());
        values.put("accEmail", chore.getAccount().getEmail());

        //Object[] values = {id, name, desc, completedDate, creator.getName(), assignedTo.getName(), reward, penalty, deadline, accEmail};
        DB_Helper.getWritableDatabase().insert("Profiles", null, values);
    }

    public List<Chore> getDatabasedChores() {
        List<Chore> chores = new ArrayList<>();

        Cursor c = DB_Helper.getReadableDatabase().rawQuery("SELECT * FROM Chores", null);

        if(c == null) return chores;
        if(c.getCount() == 0) return chores;

        try {
            while(c.moveToNext()) {
                UUID id = UUID.fromString(c.getString(c.getColumnIndex("id")));
                Account account = this.getAccount(c.getString(c.getColumnIndex("accEmail")));
                String name = c.getString(c.getColumnIndex("name"));
                String desc = c.getString(c.getColumnIndex("description"));
                Date completionDate = new Date(c.getLong(c.getColumnIndex("completeDate")));
                Date deadline = new Date(c.getLong(c.getColumnIndex("deadline")));
                Adult adult = (Adult) this.getProfileByName(c.getString(c.getColumnIndex("creator")));
                Profile assignedTo = this.getProfileByName(c.getString(c.getColumnIndex("assignedTo")));
                int reward = c.getInt(c.getColumnIndex("reward"));
                int penalty = c.getInt(c.getColumnIndex("penalty"));

                chores.add(new Chore(name, desc, completionDate, deadline, adult, assignedTo, reward, penalty, account, id));
            }
        }
        finally {
            c.close();
        }

        return chores;
    }

    /**
     * Takes either an account or profile object and returns the databased chores for it
     * @param obj
     * @return
     */
    public List<Chore> getDatabasedChores(Object obj) {

        List<Chore> chores = new ArrayList<>();

        if(!(obj instanceof Account || obj instanceof Profile)) return chores;

        for(Chore chore : this.getDatabasedChores()) {
            if(obj instanceof Account) {
                Account account = (Account) obj;
                if(chore.getAccount().getEmail().equals(account.getEmail())) chores.add(chore);
            }
            else if(obj instanceof Profile) {
                Profile profile = (Profile) obj;
                if(chore.getAssignedTo().getName().equals(profile.getName())) chores.add(chore);
            }
        }

        return chores;
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