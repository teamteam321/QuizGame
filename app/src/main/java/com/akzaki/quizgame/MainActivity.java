package com.akzaki.quizgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button learn_button = findViewById(R.id.learn_button);
        learn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(MainActivity.this,WordList.class);
                startActivity(x);
            }
        });

        final Button play_button = findViewById(R.id.play_button);
        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //while(Game_activity.continue_playing){
                    Intent x = new Intent(MainActivity.this,Game_activity.class);
                   // x.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(x);
                //}

            }
        });
    }
}