package com.akzaki.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.akzaki.quizgame.model.WordItem;
import com.google.gson.Gson;

public class Word_detail_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail_activity);
        Intent i = getIntent();
        ImageView im = findViewById(R.id.detail_image);
        TextView tx = findViewById(R.id.detail_text);

        String in_string = i.getStringExtra("item");
        WordItem wx = new Gson().fromJson(in_string,WordItem.class);
        im.setImageResource(wx.image_res_id);
        tx.setText(wx.word);
    }
}