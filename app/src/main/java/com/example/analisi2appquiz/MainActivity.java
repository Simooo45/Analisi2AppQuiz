package com.example.analisi2appquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        assignOnClickListener();
    }

    public void assignOnClickListener(){
        Button cap1 = (Button)findViewById(R.id.button1);
        Button cap2 = (Button)findViewById(R.id.button2);
        Button cap3 = (Button)findViewById(R.id.button3);
        Button cap4 = (Button)findViewById(R.id.button4);
        Button cap5 = (Button)findViewById(R.id.button5);
        Button tutti = (Button)findViewById(R.id.button);


        cap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuCapitolo.class);
                intent.putExtra("capitolo", 1);
                startActivity(intent);
            }
        });
        cap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuCapitolo.class);
                intent.putExtra("capitolo", 2);
                startActivity(intent);
            }
        });
        cap3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuCapitolo.class);
                intent.putExtra("capitolo", 3);
                startActivity(intent);
            }
        });
        cap4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuCapitolo.class);
                intent.putExtra("capitolo", 4);
                startActivity(intent);
            }
        });
        cap5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuCapitolo.class);
                intent.putExtra("capitolo", 5);
                startActivity(intent);
            }
        });

        tutti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Interrogami.class);
                intent.putExtra("capitolo", -1);
                startActivity(intent);
            }
        });
    }
}