package com.ibititec.tvalterosa;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GruposActivity extends AppCompatActivity {

    TextView txtChaveA, txtChaveB, txtChaveC, txtChaveD;
    ImageView imgChaveA, imgChaveB, imgChaveC, imgChaveD;

    String divisao, funcionalidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        iniciarlizarComponentes();
        executarAcoes();
        lerIntent();
    }

    private void lerIntent() {
        try {
            Intent intent = getIntent();
            divisao = intent.getStringExtra("divisao");
            funcionalidade = intent.getStringExtra("funcionalidade");
        } catch (Exception ex) {
            Log.i(MainActivity.TAG, "Erro: lerIntent PrimeiraDivisaoTabela: " + ex.getMessage());
        }
    }

    private void executarAcoes() {

        txtChaveA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivity("A");
            }
        });

        txtChaveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivity("B");
            }
        });

        txtChaveC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivity("C");
            }
        });

        txtChaveD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivity("D");
            }
        });

        imgChaveA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivity("A");
            }
        });

        imgChaveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivity("B");
            }
        });

        imgChaveC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivity("C");
            }
        });

        imgChaveD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivity("D");
            }
        });
    }

    private void startaActivity(String a) {
        try {
            Intent intent = new Intent(this, PrimeiraDivisaoTabelaActivity.class);
            intent.putExtra("chave", a);
            intent.putExtra("divisao", divisao);
            intent.putExtra("funcionalidade", funcionalidade);
            startActivity(intent);
        } catch (Exception ex) {
            Log.i(MainActivity.TAG, "Erro: Startar Activity Grupos: " + ex.getMessage());
        }
    }

    private void iniciarlizarComponentes() {
        txtChaveA = (TextView) findViewById(R.id.chave_A);
        txtChaveB = (TextView) findViewById(R.id.chave_B);
        txtChaveC = (TextView) findViewById(R.id.chave_C);
        txtChaveD = (TextView) findViewById(R.id.chave_D);

        imgChaveA = (ImageView) findViewById(R.id.btnChaveA);
        imgChaveB = (ImageView) findViewById(R.id.btnChaveB);
        imgChaveC = (ImageView) findViewById(R.id.btnChaveC);
        imgChaveD = (ImageView) findViewById(R.id.btnChaveD);
    }
}
