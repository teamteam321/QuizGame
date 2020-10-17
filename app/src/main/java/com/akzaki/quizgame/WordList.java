package com.akzaki.quizgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akzaki.quizgame.model.WordItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordList extends AppCompatActivity {
    public static final ArrayList<WordItem> item = new ArrayList<>(Arrays.asList(new WordItem(R.drawable.cat, "CAT"),
            new WordItem(R.drawable.dog, "DOG"),
            new WordItem(R.drawable.koala, "KOALA"),
        new WordItem(R.drawable.lion, "LION"),
        new WordItem(R.drawable.rabbit, "RABBIT"),

        new WordItem(R.drawable.owl, "OWL"),
        new WordItem(R.drawable.penguin, "PENGUIN"),
        new WordItem(R.drawable.pig, "PIG"),
        new WordItem(R.drawable.tiger, "TIGER"),
        new WordItem(R.drawable.dolphin, "DOLPHIN")));

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        final Context cont;



        public MyAdapter(Context con) {
            cont = con;

        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //creating viewholder reduce layout inflate call
            //holder extract for content
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_word,parent,false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            holder.imageView.setImageResource(item.get(position).image_res_id);
            holder.wordTextView.setText(item.get(position).word);
            holder.item = item.get(position);
            //final String tss = item.get(position).word;
            /*holder.rootview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast t = Toast.makeText(cont,tss,1);
                    t.show();
                }
            });*/
        }

        @Override
        public int getItemCount() {
            return item.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            View rootview;
            ImageView imageView;
            TextView wordTextView;
            WordItem item;
            //String string_to_set in onBindViewHodler
            //can also create string for backward referance but maybe pointless
            //cuz these two argument are also set (same method/bahavior)
            public MyViewHolder(View itemView){
                super(itemView);
                rootview = itemView;
                imageView = itemView.findViewById(R.id.word_image);
                wordTextView = itemView.findViewById(R.id.detail_text);
                //template from layout obj for faster lookup
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //image,text view are null at first but after-
                        //onbindviewholder are called they are ready
                        Intent x = new Intent(cont,Word_detail_activity.class);
                        x.putExtra("image_id",item.image_res_id);
                        x.putExtra("image_word",item.word);
                        String out_string = new Gson().toJson(item);
                        x.putExtra("item",out_string);


                        startActivity(x);
                        Toast t = Toast.makeText(cont,wordTextView.getText(),1);
                        t.show();
                    }
                });
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);
        MyAdapter adapter = new MyAdapter(WordList.this);
        LinearLayoutManager lm = new LinearLayoutManager(WordList.this);

        RecyclerView rv = findViewById(R.id.wordlist_recycler_view);

        rv.setAdapter(adapter);
        rv.setLayoutManager(lm);



    }
}