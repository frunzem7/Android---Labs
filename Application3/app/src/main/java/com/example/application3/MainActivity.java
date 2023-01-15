package com.example.application3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String uriPolitics = "https://rss.nytimes.com/services/xml/rss/nyt/Politics.xml";
        Button politicsButton = findViewById(R.id.button);
        politicsButton.setOnClickListener(view -> openActivity(uriPolitics));

        String uriBusiness = "https://rss.nytimes.com/services/xml/rss/nyt/Business.xml";
        Button businessButton = findViewById(R.id.button2);
        businessButton.setOnClickListener(view -> openActivity(uriBusiness));
    }

    private void openActivity(String uri) {
        Intent intent = new Intent(this, RecyclerActivity.class);
        intent.putExtra("uri", uri);
        startActivity(intent);
    }
}