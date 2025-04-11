package in.edu.fitnessapplication.reminder;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import in.edu.fitnessapplication.util.NotificationUtil;

public class ExerciseReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationUtil.showNotification(
                context,
                "Exercise Reminder 🏃‍♂️",
                "Time for a quick workout! Log your activity today."
        );
    }
}

