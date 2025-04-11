package in.edu.fitnessapplication.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.time.LocalDate;

import in.edu.fitnessapplication.database.AppDatabaseHelper;
import in.edu.fitnessapplication.util.SessionManager;

public class StatsService {

    private final AppDatabaseHelper dbHelper;
    private final SessionManager sessionManager;

    public StatsService(Context context) {
        dbHelper = new AppDatabaseHelper(context);
        sessionManager = new SessionManager(context);
    }

    // Total water intake today
    public int getTotalWaterToday() {
        int userId = sessionManager.getUserId();
        String today = LocalDate.now().toString(); // Format: yyyy-MM-dd

        Log.d("StatsService", "Fetching water data for userId: " + userId + ", date: " + today);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT SUM(amount_ml) FROM water WHERE user_id = ? AND date = ?",
                new String[]{String.valueOf(userId), today}
        );

        int total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        Log.d("StatsService", "Total water found: " + total);
        return total;
    }

    // Total food calories consumed today
    public int getTotalCaloriesToday() {
        int userId = 0; // 🔧 Force match with DB for testing
        String today = LocalDate.now().toString();

        Log.d("StatsService", "Fetching food data for userId: " + userId + ", date: " + today);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT SUM(calories) FROM food WHERE user_id = ? AND date = ?",
                new String[]{String.valueOf(userId), today}
        );

        int total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        Log.d("StatsService", "Total calories found: " + total);
        return total;
    }


    // Total exercise duration today (in minutes)
    public int getTotalExerciseDurationToday() {
        int userId = sessionManager.getUserId();
        String today = LocalDate.now().toString();

        Log.d("StatsService", "Fetching exercise data for userId: " + userId + ", date: " + today);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT SUM(duration_min) FROM exercise WHERE user_id = ? AND date = ?",
                new String[]{String.valueOf(userId), today}
        );

        int total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        Log.d("StatsService", "Total exercise minutes found: " + total);
        return total;
    }

    // ✅ NEW: Total exercise calories burned today
    public int getTotalCaloriesBurnedToday() {
        int userId = sessionManager.getUserId();
        String today = LocalDate.now().toString();

        Log.d("StatsService", "Fetching burned calories for userId: " + userId + ", date: " + today);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT SUM(calories_burned) FROM exercise WHERE user_id = ? AND date = ?",
                new String[]{String.valueOf(userId), today}
        );

        int total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        Log.d("StatsService", "Total burned calories found: " + total);
        return total;
    }

}
