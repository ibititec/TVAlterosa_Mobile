package com.ibititec.tvalterosa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EquipesActivity extends AppCompatActivity {

    TextView txtChaveA, txtChaveB, txtChaveC, txtChaveD;
    ImageView imgChaveA, imgChaveB, imgChaveC, imgChaveD;

    String divisao, funcionalidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipes);
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
            setResult(PrimeiraDivisaoActivity.RESULT_OK, intent);
            super.onBackPressed();
        } catch (Exception ex) {
            Log.i(MainActivity.TAG, "Erro: onBackPressed PrimeiraDivisao: " + ex.getMessage());
        }
    }

    private void executarAcoes() {

        if (funcionalidade.equals("classificacao")) {
            txtChaveA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startaActivityClassficacao("A");
                }
            });

            txtChaveB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startaActivityClassficacao("B");
                }
            });

            txtChaveC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startaActivityClassficacao("C");
                }
            });

            txtChaveD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startaActivityClassficacao("D");
                }
            });

            imgChaveA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startaActivityClassficacao("A");
                }
            });

            imgChaveB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startaActivityClassficacao("B");
                }
            });

            imgChaveC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startaActivityClassficacao("C");
                }
            });

            imgChaveD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startaActivityClassficacao("D");
                }
            });
        } else if (funcionalidade.equals("equipes")) {

            txtChaveA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startaActivityEquipes("A");
                }
            });

            txtChaveB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startaActivityEquipes("B");
                }
            });

            txtChaveC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startaActivityEquipes("C");
                }
            });

            txtChaveD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startaActivityEquipes("D");
                }
            });

            imgChaveA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startaActivityEquipes("A");
                }
            });

            imgChaveB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startaActivityEquipes("B");
                }
            });

            imgChaveC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startaActivityEquipes("C");
                }
            });

            imgChaveD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startaActivityEquipes("D");
                }
            });
        }
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

    private void startaActivityClassficacao(String a) {
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

    private void startaActivityEquipes(String a) {
        try {
            Intent intent = new Intent(this, EquipesImageActivity.class);
            intent.putExtra("chave", a);
            intent.putExtra("divisao", divisao);
            intent.putExtra("funcionalidade", "equipes");
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
        txtChaveA = (TextView) findViewById(R.id.chave_A);
        txtChaveB = (TextView) findViewById(R.id.chave_B);
        txtChaveC = (TextView) findViewById(R.id.chave_C);
        txtChaveD = (TextView) findViewById(R.id.chave_D);

        imgChaveA = (ImageView) findViewById(R.id.btnChaveA);
        imgChaveB = (ImageView) findViewById(R.id.btnChaveB);
        imgChaveC = (ImageView) findViewById(R.id.btnChaveC);
        imgChaveD = (ImageView) findViewById(R.id.btnChaveD);


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
