package com.akzaki.quizgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akzaki.quizgame.model.WordItem;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Game_activity extends AppCompatActivity implements View.OnClickListener{
    private ImageView quesion_image;
    private Button[] answer_button_list = new Button[4];
    public String answer_word =  "";
    TextView show_score;
    public static int score = 0;
    public static int round = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activity);

        Random rr = new Random();
        ArrayList<WordItem> tmp = WordList.item;

        while(true) {
            break;

        }
        quesion_image = findViewById(R.id.quesion_image);
        show_score = findViewById(R.id.ScoreView);
        show_score.setText(score+" คะแนน");

        answer_button_list[0] = findViewById(R.id.answer_1);
        answer_button_list[1] = findViewById(R.id.answer_2);
        answer_button_list[2] = findViewById(R.id.answer_3);
        answer_button_list[3] = findViewById(R.id.answer_4);

        //sdsd

        gameLoop(rr, tmp);


    }

    private void gameLoop(Random rr, ArrayList<WordItem> tmp) {
        int[] picked_table = new int[tmp.size()];

        int rand = rr.nextInt(tmp.size());
        picked_table[rand] = 1;
        answer_word = tmp.get(rand).word;
        quesion_image.setImageResource(tmp.get(rand).image_res_id);
        int anno = rr.nextInt(4);


        answer_button_list[anno].setText(tmp.get(rand).word);

        for(int e = 0;e<answer_button_list.length;e++){
            if(e == anno)
                continue;
            int ttt;
            do{
                ttt = rr.nextInt(tmp.size());
            }while(picked_table[ttt] != 0);
            picked_table[ttt] = 1;
            answer_button_list[e].setText(tmp.get(ttt).word);
        }
        //set listener
        for(int e = 0;e<answer_button_list.length;e++){
            answer_button_list[e].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        round+=1;
        Button ck = findViewById(view.getId());
        if(ck.getText().equals(this.answer_word)){
            score+=1;
            Toast t = Toast.makeText(this,"Correct",0);
            t.show();
        }else{
            Toast t = Toast.makeText(this,"Incorrect",0);
            t.show();
        }
        show_score.setText(score+" คะแนน");

        if(round %5 == 0){
            loop_decision();
        }else{
            finish();
            startActivity(getIntent());
        }



    }

    private void loop_decision(){
        AlertDialog.Builder dial = new AlertDialog.Builder(Game_activity.this);
        dial.setTitle("สรุปผล");
        dial.setMessage("คุณได้ "+score+" คะแนน\n\nต้องการเล่นเกมใหม่หรือไม่?");
        dial.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                score = 0;
                round = 0;
                finish();
                startActivity(getIntent());
            }
        });
        dial.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                score = 0;
                round = 0;
                finish();
            }
        });
        dial.show();

    }
}