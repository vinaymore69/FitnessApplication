package in.edu.fitnessapplication.service;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import in.edu.fitnessapplication.database.AppDatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Provides summed data over the last 7 days for charts.
 */
public class ChartDataService {
    private final AppDatabaseHelper dbHelper;

    public ChartDataService(Context context) {
        dbHelper = new AppDatabaseHelper(context);
    }

    /**
     * @return Map of date → total water (ml) for the last 7 days.
     */
    public Map<String, Integer> getWeeklyWater(int userId) {
        return querySumByDate(AppDatabaseHelper.TABLE_WATER, "amount_ml", userId);
    }

    /**
     * @return Map of date → total food calories for the last 7 days.
     */
    public Map<String, Integer> getWeeklyFood(int userId) {
        return querySumByDate(AppDatabaseHelper.TABLE_FOOD, "calories", userId);
    }

    /**
     * @return Map of date → total exercise duration (min) for the last 7 days.
     */
    public Map<String, Integer> getWeeklyExercise(int userId) {
        return querySumByDate(AppDatabaseHelper.TABLE_EXERCISE, "duration_min", userId);
    }

    private Map<String, Integer> querySumByDate(String table, String sumColumn, int userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT date, SUM(" + sumColumn + ") AS total " +
                "FROM " + table + " " +
                "WHERE user_id = ? AND date >= date('now','-6 days') " +
                "GROUP BY date ORDER BY date";
        Cursor cursor = db.rawQuery(sql, new String[]{ String.valueOf(userId) });

        // Initialize map with the last 7 days at zero
        Map<String, Integer> result = new LinkedHashMap<>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        for (int i = 6; i >= 0; i--) {
            Calendar d = (Calendar) cal.clone();
            d.add(Calendar.DATE, -i);
            result.put(sdf.format(d.getTime()), 0);
        }

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                int total  = cursor.getInt(cursor.getColumnIndexOrThrow("total"));
                result.put(date, total);
            }
            cursor.close();
        }
        db.close();
        return result;
    }
}

