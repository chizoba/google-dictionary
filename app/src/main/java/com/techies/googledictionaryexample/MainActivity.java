package com.techies.googledictionaryexample;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.techies.googledictionary.GoogleDictionary;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.textView);

        GoogleDictionary gd = new GoogleDictionary();

        FragmentManager fm = getSupportFragmentManager();

        gd.setDictionaryEnabled(tv, fm);

    }
}
