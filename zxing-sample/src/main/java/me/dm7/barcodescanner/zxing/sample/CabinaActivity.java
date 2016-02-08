package me.dm7.barcodescanner.zxing.sample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.widget.Button;

import java.util.Locale;

/**
 * Created by Alejandro on 29/01/2016.
 */
public class CabinaActivity extends Activity {
    Button sintetizado;
    TextToSpeech speach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabina);

        sintetizado = (Button)findViewById(R.id.btnCabina);

        speach=new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR){
                            SharedPreferences prefLanguage= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            if(prefLanguage.getString("idioma", "0").equals("0")) //Espanol o default
                                speach.setLanguage(Locale.getDefault());
                            hablar("Características generales."+
                                    "Tripulación de dos pilotos y un alumno.");
                        }
                    }
                });
    }



    public void hablar(String texto){
        speach.speak(texto, TextToSpeech.QUEUE_FLUSH, null);
    }
}
