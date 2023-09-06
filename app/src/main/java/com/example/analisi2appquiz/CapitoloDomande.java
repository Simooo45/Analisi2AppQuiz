package com.example.analisi2appquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import ru.noties.jlatexmath.JLatexMathDrawable;
import ru.noties.jlatexmath.JLatexMathView;

public class CapitoloDomande extends AppCompatActivity {
    private int numberOfQuestions;
    private int capitolo;
    private Button but;
    private int lastScroll;
    private JLatexMathView latexView;
    private LayoutInflater inflater;
    private LinearLayout layout_scheme;
    public int last_question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_capitolo1_domande);
        last_question = 1;
        lastScroll = 0;
        Bundle extras = getIntent().getExtras();
        capitolo = extras.getInt("capitolo");
        String variable_name = "numero_domande_" + capitolo;
        int resourceID = CapitoloDomande.this.getResources().getIdentifier(variable_name, "integer", CapitoloDomande.this.getPackageName());
        numberOfQuestions = (Integer) getResources().getInteger(resourceID);
        showQuestion(last_question);
        createButtonsBar();
        setOnClickListeners();

    }

    private void setOnClickListeners(){
        HorizontalScrollView scrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
        //int scrollViewWidth = scrollView.getWidth();
        int step =  200; //scrollViewWidth/numberOfQuestions;
        Button precedente = (Button) findViewById(R.id.button_precedente);
        Button successivo = (Button) findViewById(R.id.button_successivo);
        Button soluzione = (Button) findViewById(R.id.button_soluzione);
        precedente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                last_question--;
                lastScroll = lastScroll -step;
                scrollView.scrollTo(lastScroll, 0);

                showQuestion();
            }
        });
        successivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                last_question++;
                lastScroll = lastScroll + step;
                scrollView.scrollTo(lastScroll, 0);
                showQuestion();
            }
        });
        soluzione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CapitoloDomande.this, SoluzioniDomande.class);
                intent.putExtra("capitolo", capitolo);
                intent.putExtra("domanda", last_question);
                startActivity(intent);
            }
        });

    }
    private void createButtonsBar (){
        layout_scheme = (LinearLayout) findViewById(R.id.linear_layout_cap_1);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200,200);
        params.setMargins(10, 10, 10, 10);
        HorizontalScrollView scrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);


        for (int i = 1; i <= numberOfQuestions; i++) {
            but = (Button) inflater.inflate(R.layout.choose_question_button, null, false);
            but.setLayoutParams(params);
            but.setText(""+i);
            but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastScroll = scrollView.getScrollX();
                    showQuestion((Button) v);
                }
            });
            layout_scheme.addView(but);
        }
    }

    private void showQuestion(int num){
        last_question = num;
        Button successivo = (Button) findViewById(R.id.button_successivo);
        if (num == numberOfQuestions){
            successivo.setEnabled(false);
        }
        else{
            successivo.setEnabled(true);
        }

        Button precedente = (Button) findViewById(R.id.button_precedente);
        if (num == 1){
            precedente.setEnabled(false);
        }
        else{
            precedente.setEnabled(true);
        }

        TextView n_domanda_view = (TextView) findViewById(R.id.cap_1_numero_domanda);
        n_domanda_view.setText("Domanda N°"+num);
        String domanda_name =  "domanda_"+capitolo+"_"+num;
        int resourceID = CapitoloDomande.this.getResources().getIdentifier(domanda_name, "string", CapitoloDomande.this.getPackageName());
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

    private void showQuestion(){
        showQuestion(last_question);
    }

    private void showQuestion (Button v){
        showQuestion(Integer.valueOf(v.getText().toString()));
    }

}