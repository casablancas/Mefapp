package me.dm7.barcodescanner.zxing.sample;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import java.util.Locale;

public class InformacionActivity extends Activity {

    AppCompatButton call;
    TextToSpeech speach;
    AppCompatButton maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        call = (AppCompatButton)findViewById(R.id.btn_historia);
        maps = (AppCompatButton)findViewById(R.id.btn_salas);

        speach=new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR){
                            SharedPreferences prefLanguage= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            if(prefLanguage.getString("idioma", "0").equals("0")) //Espanol o default
                                speach.setLanguage(Locale.getDefault());
                        }
                    }
                });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarLlamada();
            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trazarRuta();
            }
        });
    }

    public void realizarLlamada()
    {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:2463165"));//(222)2463215
        InformacionActivity.this.startActivity(callIntent);
    }

    public void trazarRuta()
    {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=19.049306,-98.202163 ( Museo del Ejército y Fuerza Aérea )"));
        startActivity(intent);
    }
}
