package in.edu.fitnessapplication.service;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

import in.edu.fitnessapplication.reminder.ExerciseReceiver;
import in.edu.fitnessapplication.reminder.HydrationReceiver;

/**
 * Schedules recurring reminders for hydration and exercise.
 */
public class ReminderScheduler {

    public static void scheduleHydrationReminder(Context context) {
        scheduleReminder(context, HydrationReceiver.class, 9);  // 9 AM reminder
        scheduleReminder(context, HydrationReceiver.class, 14); // 2 PM reminder
        scheduleReminder(context, HydrationReceiver.class, 18); // 6 PM reminder
    }

    public static void scheduleExerciseReminder(Context context) {
        scheduleReminder(context, ExerciseReceiver.class, 7);  // 7 AM reminder
    }

    private static void scheduleReminder(Context context, Class<?> receiverClass, int hour) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, receiverClass);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                hour, // unique request code
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1); // schedule for next day
        }

        if (alarmManager != null) {
            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
            );
        }
    }
}

