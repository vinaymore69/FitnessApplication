package in.edu.fitnessapplication.repository;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import in.edu.fitnessapplication.database.AppDatabaseHelper;

public class UserRepository {
    private AppDatabaseHelper dbHelper;

    public UserRepository(Context context) {
        dbHelper = new AppDatabaseHelper(context);
    }

    /**
     * Registers a new user.
     * @param username unique username
     * @param password plaintext password (consider hashing in production)
     * @return row ID of new user, or -1 if registration failed
     */
    public long register(String username, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        long id = db.insert(AppDatabaseHelper.TABLE_USER, null, cv);
        db.close();
        return id;
    }

    /**
     * Attempts to log in a user.
     * @param username entered username
     * @param password entered password
     * @return user ID if successful, or -1 if credentials are invalid
     */
    public int login(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                AppDatabaseHelper.TABLE_USER,
                new String[]{"id"},
                "username = ? AND password = ?",
                new String[]{ username, password },
                null, null, null
        );

        int userId = -1;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                userId = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            }
            cursor.close();
        }
        db.close();
        return userId;
    }
}