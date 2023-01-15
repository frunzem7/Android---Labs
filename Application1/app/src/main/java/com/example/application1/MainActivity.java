package com.example.application1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button notificationButton = findViewById(R.id.button);
        notificationButton.setOnClickListener(view -> pushNotification());

        Button searchButton = findViewById(R.id.button2);
        searchButton.setOnClickListener(view -> searchGoogle());

        RadioGroup radGrp = findViewById(R.id.radioGroup);
        radGrp.check(R.id.radioButton4);

        Button camera = findViewById(R.id.button4);
        camera.setOnClickListener(view -> openCamera());

        Button exitButton = findViewById(R.id.button3);
        exitButton.setOnClickListener(view -> exitOfApp());
    }

    private void pushNotification() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException i) {
            Thread.currentThread().interrupt();
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("myCh", "My Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "myCh")
                .setSmallIcon(android.R.drawable.stat_notify_sync)
                .setContentTitle("My notification")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Hi, I'm Mariana. This is my notification!"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notification = builder.build();
        notificationManagerCompat = NotificationManagerCompat.from(this);

        notificationManagerCompat.notify(1, notification);
    }

    private void searchGoogle() {
        EditText search = findViewById(R.id.editTextTextPersonName);
        Editable searchText = search.getText();
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, searchText.toString());
        startActivity(intent);
    }

    private void openCamera() {
        startActivity(new Intent(this, ImageActivity.class));
    }

    private void exitOfApp() {
        this.finishAffinity();
    }
}