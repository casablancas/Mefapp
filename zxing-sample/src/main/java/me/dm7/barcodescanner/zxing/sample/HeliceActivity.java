package me.dm7.barcodescanner.zxing.sample;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class HeliceActivity extends Activity {

    Button sintetizado;
    TextToSpeech speach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helice);

        sintetizado = (Button)findViewById(R.id.btnHelice);

        speach=new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR){
                            SharedPreferences prefLanguage= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            if(prefLanguage.getString("idioma", "0").equals("0")) //Espanol o default
                                speach.setLanguage(Locale.getDefault());
                                hablar("Contaba con un motor Continental R-670-5, del que se entregaron un total de 3 519 unidades.");
                        }
                    }
                });
    }



    public void hablar(String texto){
        speach.speak(texto, TextToSpeech.QUEUE_FLUSH, null);
    }
}
