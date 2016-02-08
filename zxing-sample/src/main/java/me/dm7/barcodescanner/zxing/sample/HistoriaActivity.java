package me.dm7.barcodescanner.zxing.sample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import java.util.Locale;

public class HistoriaActivity extends Activity {

    AppCompatButton historia;
    TextToSpeech speach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia);

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

        historia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hablar("La apertura del Museo del Ejército y Fuerza Aérea Fue posible con el rescate del antiguo Colegio de San Francisco Xavier. \n" +
                        "El recinto fue inaugurado el 18 de noviembre de 2014. El objetivo de dicho museo es reconocer el trabajo de las fuerzas armadas del país, así como mostrar la participación del estado en momentos trascendentales para México.\n");
            }
        });
    }

    public void hablar(String texto){
        speach.speak(texto, TextToSpeech.QUEUE_FLUSH, null);
    }
}
