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

    TextView txtRodada1, txtRodada2, txtRodada3, txtRodada4, txtRodada5, txtRodada6;
    ImageView imgChave1, imgChave2, imgChave3, imgChave4, imgChave5, imgChave6;

    String divisao, funcionalidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        iniciarlizarComponentes();
        lerIntent();
        executarAcoes();
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


        txtRodada1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivityRodada("1");
            }
        });

        imgChave1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivityRodada("1");
            }
        });

        //
        txtRodada2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivityRodada("2");
            }
        });

        imgChave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivityRodada("2");
            }
        });

        txtRodada3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivityRodada("3");
            }
        });

        imgChave3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivityRodada("3");
            }
        });

        txtRodada4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivityRodada("4");
            }
        });

        imgChave4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivityRodada("4");
            }
        });

        txtRodada5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivityRodada("5");
            }
        });

        imgChave5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivityRodada("5");
            }
        });

        txtRodada6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivityRodada("6");
            }
        });

        imgChave6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startaActivityRodada("6");
            }
        });


    }

    private void startaActivityRodada(String a) {
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


        txtRodada1 = (TextView) findViewById(R.id.chave_Rodada_1);

        imgChave1 = (ImageView) findViewById(R.id.btnRodada_1);

        txtRodada2 = (TextView) findViewById(R.id.chave_Rodada_2);

        imgChave2 = (ImageView) findViewById(R.id.btnRodada_2);

        txtRodada3 = (TextView) findViewById(R.id.chave_Rodada_3);

        imgChave3 = (ImageView) findViewById(R.id.btnRodada_3);

        txtRodada4 = (TextView) findViewById(R.id.chave_Rodada_4);

        imgChave4 = (ImageView) findViewById(R.id.btnRodada_4);

        txtRodada5 = (TextView) findViewById(R.id.chave_Rodada_5);

        imgChave5 = (ImageView) findViewById(R.id.btnRodada_5);

        txtRodada6 = (TextView) findViewById(R.id.chave_Rodada_6);

        imgChave6 = (ImageView) findViewById(R.id.btnRodada_6);
    }
}
