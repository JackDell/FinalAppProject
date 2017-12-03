package com.example.ahame_000.seg2105.databasing;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.ahame_000.seg2105.Account;
import com.example.ahame_000.seg2105.Adult;
import com.example.ahame_000.seg2105.Child;
import com.example.ahame_000.seg2105.Chore;
import com.example.ahame_000.seg2105.ChoreState;
import com.example.ahame_000.seg2105.Profile;
import com.example.ahame_000.seg2105.Session;

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
     *
     * @param account the account object you wish to save to the database
     */
    public void saveAccount(Account account) {
        ContentValues values = new ContentValues();
        values.put("email", account.getEmail());
        values.put("password", account.getPassword());

        DB_Helper.getWritableDatabase().insert("Accounts", null, values);

        for (Chore chore : account.getAllChores()) {
            this.saveChore(chore);
        }
    }

    public Account getAccount(String accEmail) {
        Cursor c = DB_Helper.getReadableDatabase().rawQuery("SELECT * FROM Accounts WHERE email = '" + accEmail + "'", null);

        Account account;

        if (c == null) return null;
        if (!c.moveToFirst()) {
            c.close();
            return null;
        }

        try {
            c.moveToFirst();
            String password = c.getString(c.getColumnIndex("password"));
            account = new Account(accEmail, password);
            account.setProfiles(getAccountProfiles(account));
            account.setChores(getDatabasedChores(account));
        } finally {
            c.close();
        }

        return account;
    }

    /**
     * This method takes an profile object and saves its information to the database
     *
     * @param profile the profile object you wish to save to the database
     */
    public void saveProfile(Profile profile) {
        String kind = "Adult";
        if (profile instanceof Child) {
            kind = "Child";
        }

        ContentValues values = new ContentValues();
        values.put("kind", kind);
        values.put("name", profile.getName());
        values.put("password", profile.getPassword());
        values.put("points", profile.getPoints());
        values.put("accEmail", profile.getAccount().getEmail());

        DB_Helper.getWritableDatabase().insert("Profiles", null, values);

        for (Chore chore : profile.getAllChores()) {
            saveChore(chore);
        }
        profile.getAccount().getProfiles().add(profile);
    }

    public List<Profile> getAccountProfiles(Account account) {

        List<Profile> profiles = new ArrayList<>();

        Cursor c = DB_Helper.getReadableDatabase().rawQuery("SELECT * FROM Profiles WHERE accEmail='" + account.getEmail() + "'", null);

        if (c == null) return profiles;
        if (!c.moveToFirst()) {
            c.close();
            return profiles;
        }

        try {
            while (c.moveToNext()) {
                String name = c.getString(c.getColumnIndex("name"));
                String password = c.getString(c.getColumnIndex("password"));
                int points = c.getInt(c.getColumnIndex("points"));
                String kind = c.getString(c.getColumnIndex("kind"));
                if (kind.equals("Adult")) {
                    Adult adult = new Adult(name, password, points, account, new ArrayList<Chore>());
                    profiles.add(adult);
                } else {
                    Child child = new Child(name, password, points, account, new ArrayList<Chore>());
                    profiles.add(child);
                }
            }
        } finally {
            c.close();
        }

        return profiles;
    }

    //name TEXT, desc TEXT, completedDate DATE, deadline DATE, creator TEXT, assignedTo TEXT, reward INTEGER, penalty INTEGER)";


    public void saveChore(Chore chore) {

//TODO: make sure the chore is not
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
        DB_Helper.getWritableDatabase().insert("Profiles", null, values);
    }


    public List<Chore> getDatabasedChores(Account account) {

        List<Chore> chores = new ArrayList<>();

        Cursor c = DB_Helper.getReadableDatabase().rawQuery("SELECT * FROM Chores WHERE accEmail='" + account.getEmail() + "'", null);

        if (c == null) return chores;
        if (!c.moveToFirst()) {
            c.close();
            return chores;
        }

        try {
            while (c.moveToNext()) {
                UUID id = UUID.fromString(c.getString(c.getColumnIndex("id")));
                String name = c.getString(c.getColumnIndex("name"));
                String desc = c.getString(c.getColumnIndex("description"));
                ChoreState state = ChoreState.valueOf(c.getString(c.getColumnIndex("state")));
                Date completionDate = new Date(c.getLong(c.getColumnIndex("completeDate")));
                Date deadline = new Date(c.getLong(c.getColumnIndex("deadline")));
                Adult adult = (Adult) account.getProfile(c.getString(c.getColumnIndex("creator")));
                Profile assignedTo = null;
                if (c.getString(c.getColumnIndex("assignedTo")) != null) {
                    assignedTo = account.getProfile(c.getString(c.getColumnIndex("assignedTo")));
                }
                int reward = c.getInt(c.getColumnIndex("reward"));
                int penalty = c.getInt(c.getColumnIndex("penalty"));

                Chore chore = new Chore(name, desc, state, completionDate, deadline, adult, assignedTo, reward, penalty, account, id);

                chores.add(chore);

                if (assignedTo != null) {
                    chore.getAssignedTo().addChore(chore);
                }
            }
        } finally {
            c.close();
        }

        return chores;
    }

    public boolean loginAccount(String email, String password) {

        Cursor c = DB_Helper.getReadableDatabase().rawQuery("SELECT * FROM Accounts WHERE email='" + email + "' AND password='" + password + "'", null);

        if (c == null) return false;
        if (c.moveToFirst() == false) {
            c.close();
            return false;
        }

        Session.setLoggedInAccount(this.getAccount(email));
        return true;
    }
}