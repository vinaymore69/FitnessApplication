package in.edu.fitnessapplication.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import in.edu.fitnessapplication.database.AppDatabaseHelper;

public class FoodRepository {

    private final AppDatabaseHelper dbHelper;

    public FoodRepository(Context context) {
        dbHelper = new AppDatabaseHelper(context);
    }

    public long addFood(int userId, String name, String category, int calories) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String currentDate = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(new java.util.Date());

        values.put("user_id", userId);
        values.put("date", currentDate);
        values.put("description", name);      // assuming 'description' = food name
        values.put("category", category);     // Make sure your table has this column
        values.put("calories", calories);

        long result = db.insert(AppDatabaseHelper.TABLE_FOOD, null, values);
        db.close();
        return result;
    }


    public int getTotalCalories(String date, int userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int totalCalories = 0;

        Cursor cursor = db.rawQuery(
                "SELECT SUM(calories) FROM " + AppDatabaseHelper.TABLE_FOOD + " WHERE date = ? AND user_id = ?",
                new String[]{date, String.valueOf(userId)}
        );

        if (cursor.moveToFirst()) {
            totalCalories = cursor.getInt(0);
        }

        cursor.close();
        db.close();
        return totalCalories;
    }

    public Cursor getFoodByUserId(int userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        return db.query(
                AppDatabaseHelper.TABLE_FOOD,
                null,
                "user_id = ?",
                new String[]{String.valueOf(userId)},
                null,
                null,
                "date DESC"
        );
    }
}
