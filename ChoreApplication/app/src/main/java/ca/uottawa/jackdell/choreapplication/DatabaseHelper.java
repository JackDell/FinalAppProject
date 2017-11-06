package ca.uottawa.jackdell.choreapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jack on 2017-11-05.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // String variables to hold the database's name and version
    private static final String DB_NAME = "ChoreAppDatabase";
    private static final int DB_VERSION = 1;

    // String variables that hold SQL create table commands
    private static final String CREATE_ACCOUNT_TABLE = ("CREATE TABLE IF NOT EXISTS " +
    "AccountData(name TEXT, email TEXT, pass TEXT)");
    private static final String CREATE_PROFILE_TABLE = ("CREATE TABLE IF NOT EXISTS " +
            "ProfileData(account TEXT, type TEXT, name TEXT, email TEXT, pass TEXT, points INTEGER");
    private static final String CREATE_CHORE_TABLE = ("CREATE TABLE IF NOT EXISTS " +
            "ChoreData(path TEXT, name TEXT, desc TEXT, date TEXT, status TEXT, reward INT, penalty INT");

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * This method executes the create table commands stored as class instances.
     *
     * @param database  the database where the tables will be created in.
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_ACCOUNT_TABLE);
        database.execSQL(CREATE_PROFILE_TABLE);
        database.execSQL(CREATE_CHORE_TABLE);
    }

    /**
     * Updates the class on upgrade.
     *
     * @param database      the desired database.
     * @param oldVersion    the old database version.
     * @param newVersion    the new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS AccountData");
        database.execSQL("DROP TABLE IF EXISTS ProfileData");
        database.execSQL("DROP TABLE IF EXISTS ChoreData");
        this.onCreate(database);
    }

}
