package in.edu.fitnessapplication.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import in.edu.fitnessapplication.service.ReminderScheduler;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            ReminderScheduler.scheduleHydrationReminder(context);
            ReminderScheduler.scheduleExerciseReminder(context);
        }
    }
}
