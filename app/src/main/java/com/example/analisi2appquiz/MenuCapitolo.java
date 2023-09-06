package com.example.analisi2appquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuCapitolo extends AppCompatActivity {
    private int capitolo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_capitolo1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle extras = getIntent().getExtras();
        capitolo = extras.getInt("capitolo");
        TextView n_domanda_view = (TextView) findViewById(R.id.textView3);
        n_domanda_view.setText("Capitolo "+capitolo);
        String sottotitolo =  "titolo_capitolo_"+capitolo;
        int resourceID = MenuCapitolo.this.getResources().getIdentifier(sottotitolo, "string", MenuCapitolo.this.getPackageName());
        String titolo = (String) getResources().getText(resourceID);
        TextView sottotitoloView = (TextView) findViewById(R.id.textView2);
        sottotitoloView.setText(titolo);
        assignListeners();
    }

    public void assignListeners(){
        Button domande = (Button)findViewById(R.id.but_cap1_vedi_domande);
        Button interrogami = (Button) findViewById(R.id.cap1_interrogami);
        domande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCapitolo.this, CapitoloDomande.class);
                intent.putExtra("capitolo", capitolo);
                startActivity(intent);
            }
        });
        interrogami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCapitolo.this, Interrogami.class);
                intent.putExtra("capitolo", capitolo);
                startActivity(intent);
            }
        });
    }
}