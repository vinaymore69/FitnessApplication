package in.edu.fitnessapplication.reminder;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import in.edu.fitnessapplication.util.NotificationUtil;

public class HydrationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationUtil.showNotification(
                context,
                "Time to Drink Water 💧",
                "Stay hydrated! Log your water intake now."
        );
    }
}
