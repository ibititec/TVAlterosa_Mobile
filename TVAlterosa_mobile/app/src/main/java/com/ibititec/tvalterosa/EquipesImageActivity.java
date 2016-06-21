package com.ibititec.tvalterosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
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
            img.setBackgroundResource(R.drawable.chave_a);
            this.setTitle("Chave A");
        } else if (chave.equals("B")) {
            this.setTitle("Chave B");
            img.setBackgroundResource(R.drawable.chave_b);
        } else if (chave.equals("C")) {
            img.setImageResource(R.drawable.chave_c);
            this.setTitle("Chave C");
        } else if (chave.equals("D")) {
            img.setImageResource(R.drawable.chave_d);
            this.setTitle("Chave D");
        }
    }
    @Override
    public void onResume() {
        try {
            super.onResume();
            lerIntent();
//        AnalyticsApplication.enviarGoogleAnalitcs(this);

        } catch (Exception ex) {
            Log.i(MainActivity.TAG, "Erro: onResume PrimeiraDivisao : " + ex.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent();
            intent.putExtra("divisao", divisao);
            // add data to Intent
            setResult(EquipesActivity.RESULT_OK, intent);
            super.onBackPressed();
        } catch (Exception ex) {
            Log.i(MainActivity.TAG, "Erro: onBackPressed PrimeiraDivisao: " + ex.getMessage());
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        onBackPressed();
        //noinspection SimplifiableIfStatement
//        if (id == R.id.home) {
//            onBackPressed();
//            return true;
//        }
        return true;
    }
}
