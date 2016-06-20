package com.ibititec.tvalterosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

public class EquipesImageActivity extends AppCompatActivity {

    String divisao, funcionalidade, chave;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipes_image);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lerIntent();
        carregarImagem();
    }

    private void carregarImagem() {
        ImageView img = (ImageView) findViewById(R.id.img_equipe_chave);
        if (chave.equals("A")) {
            img.setImageResource(R.drawable.chave_a);
            this.setTitle("Chave A");
        } else if (chave.equals("B")) {
            this.setTitle("Chave B");
            img.setImageResource(R.drawable.chave_b);
        } else if (chave.equals("C")) {
            img.setImageResource(R.drawable.chave_c);
            this.setTitle("Chave C");
        } else if (chave.equals("D")) {
            img.setImageResource(R.drawable.chave_d);
            this.setTitle("Chave D");
        }
    }

    private void lerIntent() {
        try {
            Intent intent = getIntent();
            divisao = intent.getStringExtra("divisao");
            funcionalidade = intent.getStringExtra("funcionalidade");
            chave = intent.getStringExtra("chave");
        } catch (Exception ex) {
            Log.i(MainActivity.TAG, "Erro: lerIntent EquipesImagemActivity: " + ex.getMessage());
        }
    }
}
