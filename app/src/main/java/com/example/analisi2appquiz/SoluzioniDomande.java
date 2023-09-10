package com.example.analisi2appquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

import ru.noties.jlatexmath.JLatexMathDrawable;
import ru.noties.jlatexmath.JLatexMathView;

public class SoluzioniDomande extends AppCompatActivity {

    private static int countMatches(String str, String findStr){
        int lastIndex = 0;
        int count = 0;
        while (lastIndex != -1) {

            lastIndex = str.indexOf(findStr, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += findStr.length();
            }
        }
        return count+1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soluzioni_domande);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle extras = getIntent().getExtras();
        int capitolo = extras.getInt("capitolo");
        int domanda = extras.getInt("domanda");
        TextView capitoloView = (TextView) findViewById(R.id.title_soluzioni);
        TextView domandaView = (TextView) findViewById(R.id.subtitle_soluzioni);
        capitoloView.setText("Capitolo " + capitolo);
        domandaView.setText("- domanda " + domanda);

        String soluzione_name =  "soluzione_"+ capitolo +"_"+ domanda;
        int resourceID = SoluzioniDomande.this.getResources().getIdentifier(soluzione_name, "string", SoluzioniDomande.this.getPackageName());
        String soluzione = (String) getResources().getText(resourceID);
        int lines = countMatches(soluzione, "\\\\");
        JLatexMathDrawable drawable = JLatexMathDrawable.builder(soluzione)
                .textSize(60)
                .padding(8)
                .background(0xFFffffff)
                .align(JLatexMathDrawable.ALIGN_RIGHT)
                .build();
        JLatexMathView latexView = (JLatexMathView) findViewById(R.id.soluzione_domanda_latex);
        latexView.getLayoutParams().height = 400 + lines*50;
        latexView.setLatexDrawable(drawable);

    }
}