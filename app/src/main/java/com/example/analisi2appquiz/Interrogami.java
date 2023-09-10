package com.example.analisi2appquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import ru.noties.jlatexmath.JLatexMathDrawable;
import ru.noties.jlatexmath.JLatexMathView;

public class Interrogami extends AppCompatActivity {
    private int capitolo;
    private int cap;
    private int domanda;
    private int numeroDomande;
    private Random rd;
    private JLatexMathView latexView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interrogami);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle extras = getIntent().getExtras();
        rd = new Random();
        capitolo = extras.getInt("capitolo");
        numeroDomande = -1;
        cambiaDomanda();
        setOnClickListeners();
    }

    private void setOnClickListeners(){
        Button soluzioni = (Button) findViewById(R.id.button_soluzione);
        Button prossimaDomanda = (Button) findViewById(R.id.prossima_domanda_button);

        prossimaDomanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiaDomanda();
            }
        });

        soluzioni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Interrogami.this, SoluzioniDomande.class);
                intent.putExtra("capitolo", cap);
                intent.putExtra("domanda", domanda);
                startActivity(intent);
            }
        });
    }

    private void cambiaDomanda(){

        cap = capitolo;
        if (capitolo == -1){
            cap = rd.nextInt(5)+1;
            String variable_name = "numero_domande_" + cap;
            int resourceID = Interrogami.this.getResources().getIdentifier(variable_name, "integer", Interrogami.this.getPackageName());
            numeroDomande = (Integer) getResources().getInteger(resourceID);
        }
        else if (numeroDomande == -1){
            String variable_name = "numero_domande_" + cap;
            int resourceID = Interrogami.this.getResources().getIdentifier(variable_name, "integer", Interrogami.this.getPackageName());
            numeroDomande = (Integer) getResources().getInteger(resourceID);
        }
        domanda = rd.nextInt(numeroDomande)+1;

        TextView n_domanda_view = (TextView) findViewById(R.id.numero_domanda);
        n_domanda_view.setText("Domanda N°" + domanda);
        TextView n_capitolo_view = (TextView) findViewById(R.id.titolo_numero_capitolo);
        n_capitolo_view.setText("Capitolo N°" + cap);

        String domanda_name =  "domanda_"+cap+"_"+domanda;
        int resourceID = Interrogami.this.getResources().getIdentifier(domanda_name, "string", Interrogami.this.getPackageName());
        String domanda = (String) getResources().getText(resourceID);
        JLatexMathDrawable drawable = JLatexMathDrawable.builder(domanda)
                .textSize(60)
                .padding(8)
                .background(0xFFffffff)
                .align(JLatexMathDrawable.ALIGN_RIGHT)
                .build();
        latexView = (JLatexMathView) findViewById(R.id.j_latex_math_view);
        latexView.setLatexDrawable(drawable);

    }
}