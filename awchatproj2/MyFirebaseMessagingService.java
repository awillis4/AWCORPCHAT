package com.example.awchatproj2;
import android.content.Intent;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
//import com.google.firebase.quickstart.fcm.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG="fcmexamplemessage";
    //private MyNotificationManager myNotificationManager;
    //private ShowNotification showNotification;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        // Check if message contains a data payload.
   // if (remoteMessage.getData().size() > 0) {
        Log.d(TAG, "Message data payload: " + remoteMessage.getData());//}
        // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
        //if (/* Check if data needs to be processed by long running job */ true)
        //{scheduleJob();}else{handleNow();}
            // Handle message within 10 seconds
            // Check if message contains a notification payload.
   // if (remoteMessage.getNotification() != null) {
        Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
    notifyUser(remoteMessage.getFrom(),remoteMessage.getNotification().getBody());}//}


   public void notifyUser(String from,String notification) {
       MyNotificationManager myNotificationManager = new MyNotificationManager(getApplicationContext());
      //ShowNotification showNotification=new ShowNotification(getApplicationContext());
       MyNotificationManager.showNotification(from, notification, new Intent(getApplicationContext(), MainActivity.class));
   }

    }
    // Also if you intend on generating your own notifications as a result of a received FCM
    // message, here is where that should be initiated. See sendNotification method below.

