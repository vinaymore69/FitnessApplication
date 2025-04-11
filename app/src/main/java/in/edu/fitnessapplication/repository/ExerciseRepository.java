package in.edu.fitnessapplication.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import in.edu.fitnessapplication.database.AppDatabaseHelper;

public class ExerciseRepository {

    private final AppDatabaseHelper dbHelper;

    public ExerciseRepository(Context context) {
        dbHelper = new AppDatabaseHelper(context);
    }

    public long addExercise(int userId, String name, String type, int duration, int caloriesBurned) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String currentDate = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(new java.util.Date());

        values.put("user_id", userId);
        values.put("date", currentDate);
        values.put("type", type);
        values.put("duration_min", duration);
        values.put("calories_burned", caloriesBurned);
        values.put("name", name);  // <-- Add this only if you have a 'name' column in your table

        long result = db.insert(AppDatabaseHelper.TABLE_EXERCISE, null, values);
        db.close();
        return result;
    }


    public int getTotalCaloriesBurned(int userId, String date) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT SUM(calories_burned) FROM " + AppDatabaseHelper.TABLE_EXERCISE +
                        " WHERE user_id = ? AND date = ?",
                new String[]{String.valueOf(userId), date}
        );

        int total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }

        cursor.close();
        db.close();
        return total;
    }


    public Cursor getExerciseByUserId(int userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        return db.query(
                AppDatabaseHelper.TABLE_EXERCISE,
                null,
                "user_id = ?",
                new String[]{String.valueOf(userId)},
                null,
                null,
                "date DESC"
        );
    }
}
