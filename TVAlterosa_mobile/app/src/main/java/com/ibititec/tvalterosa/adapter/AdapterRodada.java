package com.ibititec.tvalterosa.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ibititec.tvalterosa.MainActivity;
import com.ibititec.tvalterosa.R;
import com.ibititec.tvalterosa.aovivo.PartidaTempoRealActivity;
import com.ibititec.tvalterosa.modelo.Partida;
import com.ibititec.tvalterosa.modelo.Rodada;

import java.io.Serializable;
import java.util.List;

public class AdapterRodada extends BaseAdapter {
    private Activity activity;
    private List<Rodada> rodadaList = null;
    String divisao, funcionalidade;

    public AdapterRodada(Activity activityParam, List<Rodada> rodadaListParam, String divisao, String funcionalidade) {
        this.rodadaList = rodadaListParam;
        this.activity = activityParam;
        this.funcionalidade = funcionalidade;
        this.divisao = divisao;
    }

    public AdapterRodada() {
    }

    @Override
    public int getCount() {
        return rodadaList.size();
    }

    @Override
    public Object getItem(int position) {
        return rodadaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            final Rodada rodadaObj = rodadaList.get(position);
            Log.i(MainActivity.TAG, "Vai setar o Adapter, número de registro: " + rodadaList.size() + " Position: " + position + " - Nome Rodada " + rodadaObj.getNumero());

            LayoutInflater inflater = (LayoutInflater) activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.adapter_rodada, null);

            TextView txtRodada = (TextView) layout.findViewById(R.id.txtRodada);
            TextView txtData = (TextView) layout.findViewById(R.id.txtData);
            TextView txtCampo = (TextView) layout.findViewById(R.id.txtCampo);
            TextView txtHrJogo1 = (TextView) layout.findViewById(R.id.txtHrJogo1);
            TextView txtVsJogo1 = (TextView) layout.findViewById(R.id.txtversus);
            TextView txtNomeTimeMandante = (TextView) layout.findViewById(R.id.txtNomeTimeMandante);
            TextView txtNomeTimeVisitante = (TextView) layout.findViewById(R.id.txtNomeTimeVisitante);
            TextView txtCidade = (TextView) layout.findViewById(R.id.txtCidade);

            txtRodada.setText("RODADA: " + rodadaObj.getNumero() + " - ");
            txtData.setText("DATA: " + rodadaObj.getData());
            txtCidade.setText("CIDADE: " + rodadaObj.getCidade());
            txtCampo.setText("ESTÁDIO: " + rodadaObj.getCampo());
            txtHrJogo1.setText("HR: " + rodadaObj.getHoraJogo1());

            txtNomeTimeMandante.setText(rodadaObj.getNomeTimeMandante());
            txtNomeTimeVisitante.setText(rodadaObj.getNomeTimeVisitante());

            //setando nome da imagem a ser exibida
            String[] jogo1Array = splitString(rodadaObj.getJogo1());
            //String[] jogo2Array = splitString(rodadaObj.getJogo2());

            if (jogo1Array == null) {
                jogo1Array[0] = rodadaObj.getJogo1().trim();
                jogo1Array[1] = rodadaObj.getJogo1().trim();
                jogo1Array[2] = rodadaObj.getJogo1().trim();
            }

            txtVsJogo1.setText(jogo1Array[1]);

            Log.i(MainActivity.TAG, "URL position: " + position + " - " + MainActivity.PATH_FOTOS + jogo1Array[0].trim() + ".jpg");
            Log.i(MainActivity.TAG, "jogo1Array[0]: " + jogo1Array[0]);
            Log.i(MainActivity.TAG, "jogo1Array[1]: " + jogo1Array[1]);

            Uri imageUri = Uri.parse(MainActivity.PATH_FOTOS + jogo1Array[0].trim() + ".jpg");
            SimpleDraweeView draweeView = (SimpleDraweeView) layout.findViewById(R.id.imageView2);
            draweeView.setImageURI(imageUri);

            Uri imageUri2 = Uri.parse(MainActivity.PATH_FOTOS + jogo1Array[2].trim() + ".jpg");
            SimpleDraweeView draweeView2 = (SimpleDraweeView) layout.findViewById(R.id.imageView3);
            draweeView2.setImageURI(imageUri2);

            return layout;
        } catch (Exception e) {
            Log.i(MainActivity.TAG, "Erro ao preecnher o getView: " + e.getMessage());
        }
        return convertView;
    }

    private void startarActivityTempoReal(View v, Partida partida) {
        Intent intent = new Intent(activity.getApplication(), PartidaTempoRealActivity.class);
        intent.putExtra("partida_tempo_real", (Serializable) partida);
        intent.putExtra("divisao", divisao);
        intent.putExtra("funcionalidade", funcionalidade);

        activity.startActivity(intent);
    }

    public String[] splitString(String param) {
        try {
            return param.split("-");
        } catch (Exception ex) {
            ex.getMessage();
            return null;
        }
    }
}
