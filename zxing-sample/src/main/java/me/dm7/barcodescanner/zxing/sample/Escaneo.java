package me.dm7.barcodescanner.zxing.sample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Escaneo extends Activity {

    Button helice;
    Button cabina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escaneo);

        helice = (Button)findViewById(R.id.btnHelice);
        cabina = (Button)findViewById(R.id.btnCabina);

        helice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Escaneo.this, HeliceActivity.class);
                startActivity(i);
            }
        });

        cabina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Escaneo.this, CabinaActivity.class);
                startActivity(i);
            }
        });
    }


}
