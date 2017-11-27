package ca.uottawa.jackdell.choreapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2017-11-05.
 */

public class DatabaseManager {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        this.dbHelper = new DatabaseHelper(context);
        this.database = this.dbHelper.getWritableDatabase();
    }

    /**
     * Takes and Account object and saves its information to the database.
     * This means that it also databases all of its Profiles, and general Chores.
     *
     * @param account   an Account instance that will be databased
     */
    public void saveAccount(Account account) {
        // Creating a content values object to hold information about the account
        ContentValues values = new ContentValues();

        // Filling values with the account information
        values.put("name", account.getName());
        values.put("email", account.getEmail());
        values.put("pass", account.getPassword());

        // Databasing the account values into the AccountData table
        database.insert("AccountData", null, values);

        // For every Profile that the Account has, database said Profile
        for(Profile profile : account.getProfiles()) {
            this.saveProfile(profile);
        }

        // For every general Chore the account has, database said Chore
        for(Chore chore : account.getGeneralChores()) {
            this.saveChore(chore);
        }
    }

    /**
     * @return Cursor   returns an iterable Cursor object containing all Account rows from the AccountData table.
     */
    public List<Account> getDatabasedAccounts() {
        String[] columns = new String[] {"name", "email", "pass"};

        Cursor cur = this.database.query(true, "AccountData", columns, null, null, null, null, null, null);

        List<Account> accounts = new ArrayList<>();
        if(cur == null) return accounts;

        while(cur.moveToNext()) {
            String name = cur.getString(cur.getColumnIndex("name"));
            String email = cur.getString(cur.getColumnIndex("email"));
            String password = cur.getString(cur.getColumnIndex("pass"));

            accounts.add(new Account(name, email, password));
        }

        return accounts;
    }

    /**
     * Take a Profile object and saves its information to the database.
     * Any Chores assigned to the Profile will also be databased.
     *
     * @param profile   a Profile instance, or Profile-child instance that will be databased.
     */
    public void saveProfile(Profile profile) {
        // Creating a content values object to hold information about the profile
        ContentValues values = new ContentValues();
        // Filling values with the profile information
        values.put("account", profile.toString());
        // Setting the type to "Parent" or "Child" depending on profile instance type
        if(profile instanceof Parent) {
            values.put("type", "Parent");
        }
        else {
            values.put("type", "Child");
        }
        // Adding remaining profile data
        values.put("name", profile.getName());
        values.put("email", profile.getEmail());
        values.put("pass", profile.getPassword());
        values.put("points", profile.getPoints());

        // Databasing the profile values into the ProfileData table
        database.insert("ProfileData", null, values);

        // For every chore the profile has, database said Chore
        for(Chore chore : profile.getChores()) {
            this.saveChore(chore);
        }
    }

    /**
     * Takes an Account object and returns a java.util List of Profiles, these profiles are
     * the already databased profiles of the passed Account.
     *
     * @param account   the Chore instance that the method will return the profiles of.
     * @return returns a java.util List of Profiles.
     */
    public List<Profile> getDatabasedProfiles(Account account) {

        String[] columns = new String[] {"account", "type", "name", "email", "pass", "points"};
        String whereClause = ("account = " + account.toString());

        Cursor cur = this.database.query(true, "ProfileData", columns, whereClause, null, null, null, null, null);

        List<Profile> profiles = new ArrayList<>();

        if(cur == null) return profiles;

        while(cur.moveToNext()) {

            String type = cur.getString(cur.getColumnIndex("type"));
            String name = cur.getString(cur.getColumnIndex("name"));
            String email = cur.getString(cur.getColumnIndex("email"));
            String password = cur.getString(cur.getColumnIndex("pass"));
            int points = cur.getInt(cur.getColumnIndex("points"));

            if(type.equals("Parent")) {
                profiles.add(new Parent(name, email, password, points, account));
            }
            else {
                profiles.add(new Child(name, email, password, points, account));
            }
        }

        return profiles;
    }

    /**
     * Takes a Chore object and saves its information to the database.
     *
     * @param chore a Chore instance that will be databased.
     */
    public void saveChore(Chore chore) {
        ContentValues Values = new ContentValues();

        Values.put("profile", chore.getPath());
        Values.put("name", chore.getName());
        Values.put("desc", chore.getDescription());
        Values.put("date", chore.getDeadline().toString());
        Values.put("status", chore.getStatus().toString());
        Values.put("reward", chore.getReward());
        Values.put("penalty", chore.getPenalty());

        database.insert("ChoreData", null, Values);
    }

    /**
     * Takes an Account object and returns a java.util List of Chores,
     * these Chores are the already databased general Chores of the Account.
     *
     * @param account   the Account instance in which the method will return the databased Chores for.
     * @return returns a java.util List of Chores in which are databased for the passed Account.
     */
    public List<Chore> getDatabasedChores(Account account) {

        List<Chore> chores = new ArrayList<>();
        if(account == null) return chores;

        String[] columns = new String[] {"path", "name", "desc", "status", "reward", "penalty"};
        String path = account.toString();
        String whereClause = ("path = " + path);

        Cursor cur = this.database.query(true, "ChoreData", columns, whereClause, null, null, null, null, null);
        if(cur == null) return chores;

        while(cur.moveToNext()) {
            String name = cur.getString(cur.getColumnIndex("name"));
            String desc = cur.getString(cur.getColumnIndex("desc"));
            Date date = Date.valueOf(cur.getString(cur.getColumnIndex("date")));
            ChoreStatus status = ChoreStatus.fromString(cur.getString(cur.getColumnIndex("status")));
            int reward = cur.getInt(cur.getColumnIndex("reward"));
            int penalty = cur.getInt(cur.getColumnIndex("penalty"));

            chores.add(new Chore(path, name, desc, date, status,reward, penalty));
        }

        return chores;
    }

    /**
     * Takes a Profile object and returns a java.util List of Chores,
     * these Chores are the already databased Chores of the Profile.
     *
     * @param profile the Profile instance in which the method will return the databased Chores for.
     * @return returns a java.util List of Chores in which are databased for the passed Profile.
     */
    public List<Chore> getDatabasedChores(Profile profile) {

        List<Chore> chores = new ArrayList<>();
        if(profile == null) return chores;

        String[] columns = new String[] {"path", "name", "desc", "status", "reward", "penalty"};
        String path = profile.toString();
        String whereClause = ("path = " + path);

        Cursor cur = this.database.query(true, "ChoreData", columns, whereClause, null, null, null, null, null);
        if(cur == null) return chores;

        while(cur.moveToNext()) {
            String name = cur.getString(cur.getColumnIndex("name"));
            String desc = cur.getString(cur.getColumnIndex("desc"));
            Date date = Date.valueOf(cur.getString(cur.getColumnIndex("date")));
            ChoreStatus status = ChoreStatus.fromString(cur.getString(cur.getColumnIndex("status")));
            int reward = cur.getInt(cur.getColumnIndex("reward"));
            int penalty = cur.getInt(cur.getColumnIndex("penalty"));

            chores.add(new Chore(path, name, desc, date, status,reward, penalty));
        }

        return chores;
    }

    public Account accountLogin(String email) {
        for(Account a : this.getDatabasedAccounts()) {
            if(a.getEmail().equals(email)) return a;
        }
        return null;
    }
}
