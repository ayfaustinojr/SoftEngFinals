package me.janebot.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by jpbalboa on 9 Nov 2017.
 */
public class AboutActivity extends Activity {

    public boolean backToAbout = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_interface);

        Button helpBtn = (Button) findViewById(R.id.helpBtn);
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.help);
                backToAbout = true;
            }
        });

        Button appInfo = (Button) findViewById(R.id.AppInfoBtn);
        appInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.app_info);
                backToAbout = true;
            }
        });

        Button banknoteInfo = (Button) findViewById(R.id.BanknoteBtn);
        banknoteInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.sec_features);
                backToAbout = true;
            }
        });

        Button devTeamBtn = (Button) findViewById(R.id.DevBtn);
        devTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.devteam);
                backToAbout = true;
            }
        });

    }

    public void onBackPressed() {
        if(backToAbout){
            Intent intent = new Intent(AboutActivity.this, AboutActivity.class);
            startActivity(intent);
            backToAbout = false;
        } else {
            Intent intent = new Intent(AboutActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
