package com.ibititec.tvalterosa;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.ibititec.tvalterosa.helpers.HttpHelper;
import com.ibititec.tvalterosa.helpers.JsonHelper;
import com.ibititec.tvalterosa.noticias.FeedNoticiasActivity;
import com.ibititec.tvalterosa.util.AnalyticsApplication;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //DECLARACAO DOS OBJETOS DE TELA
    private ImageButton btnPrimeiraDivisao, btnSegundaDivisao, btnNoticias, btnAjuda;
    private TextView txtPrimeiraDivisao, txtSegundaDivisao, txtNoticias, txtAjuda;
    private String atualizar;
    // private ProgressDialog progressDialog;

    //CONSTANTES NOME DO JSON NA BASE DE DADOS
    public static final String PDARTILHARIA = "pdartilharia", PDTABELA = "pdtabela", PDCLASSIFICACAO = "pdclassificacao",
            SDARTILHARIA = "sdartilharia", SDTABELA = "dstabela", SDCLASSIFICACAO = "sdclassificacao",
            PDCLASSIFICACAOBOLAO = "pdclassificacaobolao", SDCLASSIFICACAOBOLAO = "sdclassificacaobolao",
            PDJOGOSBOLAO = "pdjogosbolao", SDJOGOSBOLAO = "sdjogosbolao", USUARIO = "usuario", PDJOGOSRODADA = "pdjogosRODADA", SDJOGOSRODADA = "sdjogosRODADA",
            TABELA_CHAVE_A = "TABELA_CHAVE_A", TABELA_CHAVE_B = "TABELA_CHAVE_B", TABELA_CHAVE_C = "TABELA_CHAVE_C", TABELA_CHAVE_D = "TABELA_CHAVE_D",
            CLASSIFICACAO_A = "CLASSIFICACAO_A", CLASSIFICACAO_B = "CLASSIFICACAO_B", CLASSIFICACAO_C = "CLASSIFICACAO_C", CLASSIFICACAO_D = "CLASSIFICACAO_D",
            PROXIMA_RODADA = "PROXIMA_RODADA", QUARTAS_FINAL_IDA = "QUARTAS_IDA", QUARTAS_VOLTA = "QUARTAS_VOLTA", SEMI_IDA = "SEMI_IDA", SEMI_VOLTA = "SEMI_VOLTA",
            FINAL_IDA = "FINAL_IDA", FINAL_VOLTA = "FINAL_VOLTA", CLASSIFICACAO_GERAL = "CLASSIFICACA_GERAL";

    public static final String TAG = "CAMPEONATOLD";
    public static final String PATH_FOTOS = "http://52.37.37.207:98/Admin/Time/Image?nomeimagem=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        //AS DUAS LINHAS ABAIXO DESABILITAR A BARRA DE MENU
        toggle.setDrawerIndicatorEnabled(false);
        //toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //IDENTIFICACAO DOS OBJETOS DE LAYOUT
        btnPrimeiraDivisao = (ImageButton) findViewById(R.id.btnPrimeiraDivisao);
        //btnSegundaDivisao = (ImageButton) findViewById(R.id.btnSegundaDivisao);
        btnNoticias = (ImageButton) findViewById(R.id.btnNoticias);
        btnAjuda = (ImageButton) findViewById(R.id.btnPrimeiraDivisaoSobre);
        txtPrimeiraDivisao = (TextView) findViewById(R.id.txtPrimeiraDivisao);
        // txtSegundaDivisao = (TextView) findViewById(R.id.txtSegundaDivisao);
        txtNoticias = (TextView) findViewById(R.id.txtNoticias);
        txtAjuda = (TextView) findViewById(R.id.txtHelp);

        this.setTitle("Copa Alterosa SUB 20");
        //lerIntent();
        Fresco.initialize(this);
        PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit()
                .putString("atualizacao.json", "sim")
                .apply();

        lerIntent();
    }

    private void iniciarAppodeal() {
    }

    @Override
    public void onResume() {
        super.onResume();
        //METODOS DOS CLICKS
        executarAcoes();
        atualizarBaseDados(false);
        AnalyticsApplication.enviarGoogleAnalitcs(this);
        iniciarAppodeal();
    }

    private void lerIntent() {
        Intent intent = getIntent();
        atualizar = intent.getStringExtra("atualizar");
        atualizar = JsonHelper.leJsonBancoLocal("atualizacao", this);
    }

    private void atualizarBaseDados(boolean manual) {
        try {
            atualizar = JsonHelper.leJsonBancoLocal("atualizacao", this);
            //INCLUIR LOGICA PARA ATUALIZACAO AUTOMATICA
            if (!atualizar.equals("nao") || manual) {

                PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit()
                        .putString("atualizacao.json", "nao")
                        .apply();

                if (!HttpHelper.existeConexao(this)) {
                    exibirMensagem();
                    Log.i(TAG, "Sem conexão com a internet.");
                } else {
                    donwnloadFromUrl(TABELA_CHAVE_A, getString(R.string.url_pdtabela_A), "{\"id\": \"1\"}");
                    donwnloadFromUrl(TABELA_CHAVE_B, getString(R.string.url_pdtabela_B), "{\"id\": \"2\"}");
                    donwnloadFromUrl(TABELA_CHAVE_C, getString(R.string.url_pdtabela_C), "{\"id\": \"3\"}");
                    donwnloadFromUrl(TABELA_CHAVE_D, getString(R.string.url_pdtabela_D), "{\"id\": \"4\"}");

                    donwnloadFromUrl(PDARTILHARIA, getString(R.string.url_pdartilharia), "");

                    donwnloadFromUrl(CLASSIFICACAO_A, getString(R.string.url_pdclassificacao_A), "{\"id\": \"1\"}");
                    donwnloadFromUrl(CLASSIFICACAO_B, getString(R.string.url_pdclassificacao_B), "{\"id\": \"2\"}");
                    donwnloadFromUrl(CLASSIFICACAO_C, getString(R.string.url_pdclassificacao_C), "{\"id\": \"3\"}");
                    donwnloadFromUrl(CLASSIFICACAO_D, getString(R.string.url_pdclassificacao_D), "{\"id\": \"4\"}");


                    donwnloadFromUrl(QUARTAS_FINAL_IDA, getString(R.string.url_quartas_ida), "");
                    donwnloadFromUrl(QUARTAS_VOLTA, getString(R.string.url_quartas_volta), "");
                    donwnloadFromUrl(CLASSIFICACAO_GERAL, getString(R.string.url_classificacao_geral), "");
                }
            }
        } catch (Exception ex) {
            Log.i(TAG, "Não foi possível atualizar  base de dados." + ex.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_atualizar) {
            atualizarBaseDados(true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void executarAcoes() {
        try {
            btnPrimeiraDivisao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startarActivity("primeira");
                }
            });
//
//            btnSegundaDivisao.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startarActivity("segunda");
//                }
//            });

            btnNoticias.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startarActivityNoticia();
                }
            });

            txtPrimeiraDivisao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startarActivity("primeira");
                }
            });

//            txtSegundaDivisao.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startarActivity("segunda");
//                }
//            });

            txtNoticias.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startarActivityNoticia();
                }
            });

            btnAjuda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startarActivityHelp("primeira", "sobre");
                }
            });
            txtAjuda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startarActivityHelp("primeira", "sobre");
                }
            });

        } catch (Exception ex) {
            Log.i(MainActivity.TAG, "Erro: executarAcoes MainActivity: " + ex.getMessage());
        }
    }

    private void startarActivityHelp(String divisao, String funcionalidade) {
        try {
            Intent intent = new Intent(this, SobreActivity.class);
            intent.putExtra("divisao", divisao);
            intent.putExtra("funcionalidade", funcionalidade);
            startActivity(intent);
        } catch (Exception ex) {
            Log.i(MainActivity.TAG, "Erro: startarActivityHelp PrimeiraDivisao: " + ex.getMessage());
        }
    }

    private void startarActivity(String divisao) {
        try {
            Intent intent = new Intent(this, PrimeiraDivisaoActivity.class);
            intent.putExtra("divisao", divisao);


            startActivity(intent);
        } catch (Exception ex) {
            Log.i(MainActivity.TAG, "Erro: statarActivity MainActivity: " + ex.getMessage());
        }
    }

    private void startarActivityNoticia() {
        try {
            Intent intent = new Intent(this, FeedNoticiasActivity.class);
            startActivity(intent);
        } catch (Exception ex) {
            Log.i(MainActivity.TAG, "Erro: startartActivityNoticia MainActivity: " + ex.getMessage());
        }
    }

    private void gravarJson(String nomeJson, String json) {
        try {
            PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit()
                    .putString(nomeJson + ".json", json)
                    .apply();
            Log.i(TAG, "Gravou Json: " + json);
        } catch (Exception ex) {
            Log.i(TAG, "Erro no metodo:  gravarJson: " + nomeJson + " - Erro: " + ex.getMessage());
        }
    }

    private void donwnloadFromUrl(final String nomeJsonParam, String urlJson, final String param) {
        (new AsyncTask<String, Void, String>() {

            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(MainActivity.this, "Aguarde", "Atualizando dados");
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
                    Log.i(TAG, json);
                    if (json == null) {
                        Log.w(TAG, "JSON veio nulo na url : " + url);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, String.format(getString(R.string.msg_erro_json), e.getMessage()));
                }
                return json;
            }

            @Override
            protected void onPostExecute(String json) {
                super.onPostExecute(json);

                progressDialog.dismiss();

                if (json == null || json.equals("")) {
                    //Log.w(TAG, "JSON veio nulo!");
                    return;
                }

                PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit()
                        .putString(nomeJsonParam + ".json", json)
                        .apply();
            }
        }).execute(urlJson);
    }

    private void exibirMensagem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Atenção");
        //define a mensagem
        builder.setMessage("Não identificado conexão com a internet, verifique se sua conexão está ativa.");
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
