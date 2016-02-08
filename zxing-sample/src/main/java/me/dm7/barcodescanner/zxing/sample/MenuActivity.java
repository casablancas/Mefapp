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
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MenuActivity extends Activity {

    @Bind(R.id.btn_qr)
    Button _qrButton;


    AppCompatButton call;
    AppCompatButton maps;
    TextToSpeech speach;
    AppCompatButton informacion;
    AppCompatButton historia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        ButterKnife.bind(this);


        call = (AppCompatButton)findViewById(R.id.btn_servicios);
        maps = (AppCompatButton)findViewById(R.id.btn_salas);
        //sintetizado = (AppCompatButton)findViewById(R.id.btn_informacion);
        informacion = (AppCompatButton)findViewById(R.id.btn_informacion);
        historia = (AppCompatButton)findViewById(R.id.btn_historia);

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

        informacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //realizarLlamada();
                Intent i = new Intent(MenuActivity.this, InformacionActivity.class);
                startActivity(i);
            }
        });

        historia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, HistoriaActivity.class);
                startActivity(i);
            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trazarRuta();
            }
        });


        /*sintetizado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hablar("Pa ke kiere saber eso jaja saludos");
            }
        });*/


        _qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                //finish();
                Intent i = new Intent(MenuActivity.this, FullScannerActivity.class);
                startActivity(i);
                //Log.v("TEEEEEEEEEEEEEEEEST", "boton de qr");
                //launchActivity();
            }
        });
    }

    public void realizarLlamada()
    {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:2138810"));
        MenuActivity.this.startActivity(callIntent);
    }

    public void trazarRuta()
    {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=19.019463,-98.247147 ( Museo del Ejército y Fuerza Aérea )"));
        startActivity(intent);
    }

    public void hablar(String texto){
        speach.speak(texto, TextToSpeech.QUEUE_FLUSH, null);
    }

}
