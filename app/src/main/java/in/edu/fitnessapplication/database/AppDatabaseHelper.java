package in.edu.fitnessapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "fitness.db";
    private static final int DB_VERSION = 2; // Bumped to force upgrade
   

    // Table names
    public static final String TABLE_USER = "users";
    public static final String TABLE_WATER = "water";
    public static final String TABLE_FOOD = "food";
    public static final String TABLE_EXERCISE = "exercise";

    public AppDatabaseHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // users table
        db.execSQL("CREATE TABLE " + TABLE_USER + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT UNIQUE, " +
                "password TEXT" +
                ")");

        // water table with 'time' column added
        db.execSQL("CREATE TABLE " + TABLE_WATER + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER, " +
                "date TEXT, " +
                "time TEXT, " + // <-- new column added
                "amount_ml INTEGER, " +
                "FOREIGN KEY(user_id) REFERENCES " + TABLE_USER + "(id)" +
                ")");

        // food table
        db.execSQL("CREATE TABLE " + TABLE_FOOD + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER, " +
                "date TEXT, " +
                "description TEXT, " +
                "category TEXT, " +                  // <-- Add this line
                "calories INTEGER, " +
                "FOREIGN KEY(user_id) REFERENCES " + TABLE_USER + "(id)" +
                ")");


        // exercise table
        db.execSQL("CREATE TABLE " + TABLE_EXERCISE + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER, " +
                "date TEXT, " +
                "name TEXT, " +                    // <-- Add this line
                "type TEXT, " +
                "duration_min INTEGER, " +
                "calories_burned INTEGER, " +
                "FOREIGN KEY(user_id) REFERENCES " + TABLE_USER + "(id)" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop and recreate all tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WATER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}
