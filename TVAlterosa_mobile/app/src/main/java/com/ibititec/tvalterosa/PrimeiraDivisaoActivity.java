package com.ibititec.tvalterosa;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;
import com.ibititec.tvalterosa.bolao.BolaoPrincipalActivity;
import com.ibititec.tvalterosa.helpers.HttpHelper;

import java.io.IOException;

public class PrimeiraDivisaoActivity extends AppCompatActivity {

    private ImageButton btnTabela, btnArtilharia, btnClassificacao, btnAjuda, btnBolao, btnProximaRodada;
    private TextView txtTabela, txtArtilharia, txtClassificacao, txtAjuda, txtBolao, txtProximaRodada;
    private String divisao;
    Toolbar toolbar;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeira_divisao);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnArtilharia = (ImageButton) findViewById(R.id.btnPrimeiraDivisaoArtilharia);
        btnTabela = (ImageButton) findViewById(R.id.btnPrimeiraDivisaoTabela);
        btnClassificacao = (ImageButton) findViewById(R.id.btnPrimeiraDivisaoClassificacao);
        btnAjuda = (ImageButton) findViewById(R.id.btnPrimeiraDivisaoSobre);
        btnProximaRodada = (ImageButton) findViewById(R.id.btnProximaRodada);

        txtArtilharia = (TextView) findViewById(R.id.txtArtilharia);
        txtTabela = (TextView) findViewById(R.id.txtTabela);
        txtClassificacao = (TextView) findViewById(R.id.txtClassificacao);
        txtAjuda = (TextView) findViewById(R.id.txtHelp);
        txtProximaRodada = (TextView) findViewById(R.id.txtProximaRodada);

        lerIntent();
        executarAcoes();
    }

    @Override
    public void onResume() {
        try {
            super.onResume();
            lerIntent();
//        AnalyticsApplication.enviarGoogleAnalitcs(this);
            iniciarAppodeal();
        } catch (Exception ex) {
            Log.i(MainActivity.TAG, "Erro: onResume PrimeiraDivisao : " + ex.getMessage());
        }
    }

    private void iniciarAppodeal() {
        Appodeal.show(this, Appodeal.BANNER_BOTTOM);
    }

    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent();
            intent.putExtra("divisao", divisao);
            // add data to Intent
            setResult(PrimeiraDivisaoTabelaActivity.RESULT_OK, intent);
            super.onBackPressed();
        } catch (Exception ex) {
            Log.i(MainActivity.TAG, "Erro: onBackPressed PrimeiraDivisao: " + ex.getMessage());
        }
    }

    private void executarAcoes() {
        try {
            if (divisao.equals("primeira")) {
                this.setTitle("Copa TV Alterosa");
            } else {
                this.setTitle("Segunda Divisão");

                btnBolao.setImageResource(R.drawable.sdbolao);
                btnAjuda.setImageResource(R.drawable.sdajuda);
                btnTabela.setImageResource(R.drawable.sdtabela);
                btnArtilharia.setImageResource(R.drawable.sdartilharia);
                btnClassificacao.setImageResource(R.drawable.sdclassificacao);
            }

            btnArtilharia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startarActivityArtilharia(divisao, "artilharia");
                }
            });

            btnTabela.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startarActivity(divisao, "tabela");
                }
            });

            btnClassificacao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startarActivity(divisao, "classificacao");
                }
            });

            btnProximaRodada.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startarActivityProximaRodada(divisao);
                }
            });



            btnBolao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startarActivityBolao(divisao);
                }
            });

            txtArtilharia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startarActivityArtilharia(divisao, "artilharia");
                }
            });

            txtTabela.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startarActivity(divisao, "tabela");
                }
            });

            txtClassificacao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startarActivity(divisao, "classificacao");
                }
            });



            txtBolao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startarActivityBolao(divisao);
                }
            });

            txtProximaRodada.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startarActivityProximaRodada(divisao);
                }
            });
        } catch (Exception ex) {
            Log.i(MainActivity.TAG, "Erro: executarAcoes PrimeiraDivisao: " + ex.getMessage());
        }
    }

    private void startarActivityProximaRodada(String divisao) {
        try {
            if (HttpHelper.existeConexao(this)) {
                donwnloadFromUrl(MainActivity.PROXIMA_RODADA, getString(R.string.url_proximarodada), "");
                intent = new Intent(this, PrimeiraDivisaoTabelaActivity.class);
            } else {
                exibirMensagem();
                Log.i(MainActivity.TAG, "Sem conexão com a internet.");
            }
        } catch (Exception ex) {
            Log.i(MainActivity.TAG, "Erro: startarActivityHelp PrimeiraDivisao: " + ex.getMessage());
        }

    }



    private void startarActivityBolao(String divisao) {
        try {
            Intent intent = new Intent(this, BolaoPrincipalActivity.class);
            intent.putExtra("divisao", divisao);
            startActivity(intent);
        } catch (Exception ex) {
            Log.i(MainActivity.TAG, "Erro: startarActivityBolao PrimeiraDivisao: " + ex.getMessage());
        }
    }

    private void startarActivityArtilharia(String divisao, String funcionalidade) {
        try {
            Intent intent = new Intent(this, PrimeiraDivisaoTabelaActivity.class);
            intent.putExtra("divisao", divisao);
            intent.putExtra("funcionalidade", funcionalidade);
            startActivity(intent);
        } catch (Exception ex) {
            Log.i(MainActivity.TAG, "Erro: startarActivityBolao PrimeiraDivisao: " + ex.getMessage());
        }
    }

    private void startarActivity(String divisao, String funcionalidade) {
        try {
            Intent intent = new Intent(this, GruposActivity.class);
            intent.putExtra("divisao", divisao);
            intent.putExtra("funcionalidade", funcionalidade);
            startActivity(intent);
        } catch (Exception ex) {
            Log.i(MainActivity.TAG, "Erro: startarActivity PrimeiraDivisao: " + ex.getMessage());
        }
    }

    private void lerIntent() {
        try {
            Intent intent = getIntent();
            divisao = intent.getStringExtra("divisao");
        } catch (Exception ex) {
            Log.i(MainActivity.TAG, "Erro: lerIntent PrimeiraDivisao: " + ex.getMessage());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        onBackPressed();
        return true;
    }

    private void donwnloadFromUrl(final String nomeJsonParam, String urlJson, final String param) {
        (new AsyncTask<String, Void, String>() {
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(PrimeiraDivisaoActivity.this, "Aguarde", "Atualizando dados");
            }

            @Override
            protected String doInBackground(String... params) {
                String json = null;

                try {
                    String url = params[0];
                    if (param.equals("")) {
                        json = HttpHelper.downloadFromURL(url);
                    } else {
                        json = HttpHelper.POSTJson(url, param);
                    }
                    Log.i(MainActivity.TAG, json);
                    if (json == null) {
                        Log.w(MainActivity.TAG, "JSON veio nulo na url : " + url);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(MainActivity.TAG, String.format(getString(R.string.msg_erro_json), e.getMessage()));
                }
                return json;
            }

            @Override
            protected void onPostExecute(String json) {
                super.onPostExecute(json);

                progressDialog.dismiss();

                if (json == null) {
                    //Log.w(TAG, "JSON veio nulo!");
                    return;
                }

                PreferenceManager.getDefaultSharedPreferences(PrimeiraDivisaoActivity.this).edit()
                        .putString(nomeJsonParam + ".json", json)
                        .apply();
                intent.putExtra("divisao", divisao);
                intent.putExtra("funcionalidade", "ProximaRodada");
                startActivity(intent);
            }
        }).execute(urlJson);
    }

    private void exibirMensagem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //define o titulo
        builder.setTitle("Atenção");
        //define a mensagem
        builder.setMessage("Para visualizar a próxima rodada é necessário conexão com a internet.");
        //define um botão como positivo
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                return;
                // Toast.makeText(MainActivity.this, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });

        //cria o AlertDialog
        AlertDialog alerta = builder.create();
        //Exibe
        alerta.show();
    }
}
