package com.ibititec.tvalterosa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.ibititec.tvalterosa.admin.LoginUsuarioActivity;
import com.ibititec.tvalterosa.util.RegistrationIntentService;

public class SplashActivity extends AppCompatActivity {
    private final int DURACAO_TELA = 3000;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //CHAMANDO A TELA MAIN ACTIVITY
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //Intent novaTela = new Intent(SplashActivity.this, MainActivity.class);
//                Intent novaTela = new Intent(SplashActivity.this, MainActivity.class);
//                SplashActivity.this.startActivity(novaTela);
//                SplashActivity.this.finish();
//            }
//        }, DURACAO_TELA);
        iniciarAppodeal();


        // //INICIALIZACAO DO FRESCO
        inicializarPushMessage();

    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i("LOG", "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    private void inicializarPushMessage() {
        if (checkPlayServices()) {
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
    }

    private void iniciarAppodeal() {
        //String PRODUCAO
        //String appKey = "a7abb670bb95499ee0c535d3d8f3787704b48736d99fab89";
        //String DESENVOLCIVMENTO
        String appKey = "a7abb670bb95499ee0c535d3d8f3787704b48736d99fab8ssdsddsd9";

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    public void onClick(View v) {
        Intent novaTela = new Intent(SplashActivity.this, MainActivity.class);
        SplashActivity.this.startActivity(novaTela);
        SplashActivity.this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }


}
