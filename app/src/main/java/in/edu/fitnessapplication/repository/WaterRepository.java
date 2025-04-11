package in.edu.fitnessapplication.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import in.edu.fitnessapplication.database.AppDatabaseHelper;

public class WaterRepository {

    private final AppDatabaseHelper dbHelper;

    public WaterRepository(Context context) {
        dbHelper = new AppDatabaseHelper(context);
    }

    // Insert new water intake entry
    public long addWater(int userId, String time, int amount) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String currentDate = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(new java.util.Date());

        values.put("user_id", userId);
        values.put("date", currentDate);
        values.put("time", time);
        values.put("amount_ml", amount);

        long result = db.insert(AppDatabaseHelper.TABLE_WATER, null, values);
        db.close();
        return result;
    }

    // Get total water intake for a given date and user
    public int getTotalWater(String date, int userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int totalWater = 0;

        Cursor cursor = db.rawQuery(
                "SELECT SUM(amount_ml) FROM " + AppDatabaseHelper.TABLE_WATER + " WHERE date = ? AND user_id = ?",
                new String[]{date, String.valueOf(userId)}
        );

        if (cursor.moveToFirst()) {
            totalWater = cursor.getInt(0);
        }

        cursor.close();
        db.close();
        return totalWater;
    }

    // Get water intake entries for a specific user, ordered by time
    public Cursor getWaterByUserId(int userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        return db.query(
                AppDatabaseHelper.TABLE_WATER,
                null,
                "user_id = ?",
                new String[]{String.valueOf(userId)},
                null,
                null,
                "time DESC"
        );
    }
}
