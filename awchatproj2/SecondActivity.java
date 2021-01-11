package com.example.awchatproj2;
import android.app.NotificationManager;import android.app.PendingIntent;
import android.content.Context;import android.content.Intent;
import android.os.Bundle;import android.util.Log;import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;import androidx.core.app.TaskStackBuilder;
//import android.support.v7.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private Object NotificationManager;private Object SecondActivity;
    String mNotification;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_second);
        View v = findViewById(R.id.Login);v.setOnClickListener(this); }

    @Override public void onClick(View arg0) {
        if (arg0.getId() == R.id.Login) {
            //define a new Intent for the second Activity
            Intent intent = new Intent(this, MainActivity.class);
            /*start the second Activity*/this.startActivity(intent); }}

        protected void displayNotification(){Log.i("Start", "notification");
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this,mNotification);

        mBuilder.setContentTitle("New Message");
        mBuilder.setContentText("You've received new message.");
        mBuilder.setTicker("New Message Alert!");
        //mBuilder.setSmallIcon(R.drawable.woman);

        /* Increase notification number every time a new notification arrives */
        int numMessages = 0;
        mBuilder.setNumber(++numMessages);

        /* Add Big View Specific Configuration */
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        String[] events = new String[6];
        events[0] = new String("This is first line....");
        events[1] = new String("This is second line...");
        events[2] = new String("This is third line...");
        events[3] = new String("This is 4th line...");
        events[4] = new String("This is 5th line...");
        events[5] = new String("This is 6th line...");

        // Sets a title for the Inbox style big view
        inboxStyle.setBigContentTitle("Big Title Details:");

        // Moves events into the big view
        for(int i=0;i<events.length;i++){inboxStyle.addLine(events[i]);}

        mBuilder.setStyle(inboxStyle);

        /* Creates an explicit intent for an Activity in your app */
        Intent resultIntent = new Intent(this, SecondActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(SecondActivity.class);

        /* Adds the Intent that starts the Activity to the top of the stack */
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        /* notificationID allows you to update the notification later */NotificationManager.notify();}}
