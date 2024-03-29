package com.example.ahame_000.seg2105.Database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.ahame_000.seg2105.DataStructures.Account;
import com.example.ahame_000.seg2105.DataStructures.Adult;
import com.example.ahame_000.seg2105.DataStructures.Child;
import com.example.ahame_000.seg2105.DataStructures.Chore;
import com.example.ahame_000.seg2105.DataStructures.ChoreState;
import com.example.ahame_000.seg2105.Helpers.DateHelper;
import com.example.ahame_000.seg2105.DataStructures.Profile;
import com.example.ahame_000.seg2105.Helpers.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DatabaseManager {

    private static DatabaseHelper DB_Helper;
    private final String ADULT = "Adult";
    private final String CHILD = "Child";



    public DatabaseManager(DatabaseHelper DB_Helper) {
        this.DB_Helper = DB_Helper;
    }

    /**
     * This method takes an account object and saves its information to the database
     *
     * @param account the account object you wish to save to the database
     */
    public boolean saveAccount(Account account) {

        Cursor c = DB_Helper.getReadableDatabase().rawQuery("SELECT * FROM Accounts WHERE email='" + account.getEmail() + "'", null);
        if (c != null && c.moveToFirst()){
            c.close();
            return false;
        }



        ContentValues values = new ContentValues();
        values.put("email", account.getEmail());
        values.put("password", account.getPassword());

        DB_Helper.getWritableDatabase().insert("Accounts", null, values);

        return true;
    }

    /**
     * takes a string of account name, and returns account from database
     * @param accEmail the name of the account
     * @return the account related to the passed email
     */
    private Account getAccount(String accEmail) {
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
    public boolean saveProfile(Profile profile) {

        // Database already contains profile, return
        //if(Session.getLoggedInAccount().getProfiles().contains(profile)) return;

        String kind = ADULT;
        if (profile instanceof Child) {
            kind = CHILD;
        }

        ContentValues values = new ContentValues();
        values.put("kind", kind);
        values.put("name", profile.getName());
        values.put("password", profile.getPassword());
        values.put("points", profile.getPoints());
        values.put("accEmail", profile.getAccount().getEmail());

        DB_Helper.getWritableDatabase().insert("Profiles", null, values);
        return true;

    }

    private List<Profile> getAccountProfiles(Account account) {

        List<Profile> profiles = new ArrayList<>();

        Cursor c = DB_Helper.getReadableDatabase().rawQuery("SELECT * FROM Profiles WHERE accEmail='" + account.getEmail() + "'", null);

        if (c == null) return profiles;
        if (!c.moveToFirst()) {
            c.close();
            return profiles;
        }

        try {
            while (true) {
                String name = c.getString(c.getColumnIndex("name"));
                String password = c.getString(c.getColumnIndex("password"));
                int points = c.getInt(c.getColumnIndex("points"));
                String kind = c.getString(c.getColumnIndex("kind"));
                if (kind.equals(ADULT)) {
                    Adult adult = new Adult(name, password, points, account, new ArrayList<Chore>());
                    profiles.add(adult);
                } else {
                    Child child = new Child(name, password, points, account, new ArrayList<Chore>());
                    profiles.add(child);
                }
                if(!c.moveToNext())
                    break;;
            }
        } finally {
            c.close();
        }

        return profiles;
    }
     public void updateProfilePoints(Profile profile){
         ContentValues values = new ContentValues();
         values.put("points", profile.getPoints());
         DB_Helper.getWritableDatabase().update("Profiles",values,"name='" + profile.getName() + "' AND accEmail='" + profile.getAccount().getEmail() + "'",null);
     }



    public void saveChore(Chore chore) {

        ContentValues values = new ContentValues();
        values.put("id", chore.getStringId());
        values.put("name", chore.getName());
        values.put("desc", chore.getDescription());
        if(chore.getCompletedDate() == null) {
            values.put("completedDate", "null");
        }
        else {
            values.put("completedDate", DateHelper.getDateString(chore.getCompletedDate()));
        }

        values.put("deadline", DateHelper.getDateString(chore.getDeadline()));
        values.put("creator", chore.getCreator().getName());
        if(chore.getAssignedTo() == null) {
            values.put("assignedTo", "null");
        }
        else {
            values.put("assignedTo", chore.getAssignedTo().getName());
        }
        values.put("reward", chore.getReward());
        values.put("penalty", chore.getPenalty());
        if(chore.getState() == null)
            values.put("state", ChoreState.UNASSIGNED.toString());
        else
            values.put("state", chore.getState().toString());
        values.put("accEmail", chore.getAccount().getEmail());

        if(Session.getLoggedInAccount().getChore(chore.getId()) == null) {
            DB_Helper.getWritableDatabase().insert("Chores", null, values);
        }
        else {
            DB_Helper.getWritableDatabase().delete("Chores","id = '" + chore.getStringId() + "'",null);
            DB_Helper.getWritableDatabase().insert("Chores", null, values);
        }
    }


    private List<Chore> getDatabasedChores(Account account) {

        List<Chore> chores = new ArrayList<>();

        Cursor c = DB_Helper.getReadableDatabase().rawQuery("SELECT * FROM Chores WHERE accEmail='" + account.getEmail() + "'", null);

        if (c == null) return chores;
        if (!c.moveToFirst()) {
            c.close();
            return chores;
        }

        try {
            while (true) {

                UUID id = UUID.fromString(c.getString(c.getColumnIndex("id")));
                String name = c.getString(c.getColumnIndex("name"));
                String desc = c.getString(c.getColumnIndex("desc"));
                ChoreState state = ChoreState.valueOf(c.getString(c.getColumnIndex("state")));

                Date completedDate;
                String completeStr = c.getString(c.getColumnIndex("completedDate"));
                if(completeStr.equals("null")) {
                    completedDate = null;
                }
                else {
                    completedDate = DateHelper.dateFromString(completeStr);
                }

                Date deadline = DateHelper.dateFromString(c.getString(c.getColumnIndex("deadline")));

                Profile assignedTo;
                String assignStr = c.getString(c.getColumnIndex("assignedTo"));
                if(assignStr.equals("null")) {
                    assignedTo = null;
                }
                else {
                    assignedTo = account.getProfile(assignStr);
                }

                Adult creator = (Adult) account.getProfile(c.getString(c.getColumnIndex("creator")));
                int reward = c.getInt(c.getColumnIndex("reward"));
                int penalty = c.getInt(c.getColumnIndex("penalty"));

                Chore chore = new Chore(name, desc, state, completedDate, deadline, creator, assignedTo, reward, penalty, account, id);

                chores.add(chore);

                if (assignedTo != null) {
                    chore.getAssignedTo().addChore(chore);
                }
                if(!c.moveToNext())
                    break;;
            }
        } finally {
            c.close();
        }

        return chores;
    }


    public void removeChore(Chore chore){
        DB_Helper.getWritableDatabase().delete("Chores","id = '" + chore.getStringId() + "'",null);
    }


    public boolean loginAccount(String email, String password) {

        Cursor c = DB_Helper.getReadableDatabase().rawQuery("SELECT * FROM Accounts WHERE email='" + email + "' AND password='" + password + "'", null);

        if (c == null) return false;
        if (!c.moveToFirst()) {
            c.close();
            return false;
        }

        Session.setLoggedInAccount(getAccount(email));
        return true;
    }

}