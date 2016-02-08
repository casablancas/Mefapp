package me.dm7.barcodescanner.zxing.sample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.Bind;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";


    @Bind(R.id.input_name)
    EditText _nameText;
    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_city) EditText _cityText;
    @Bind(R.id.btn_signup)
    Button _signupButton;
    //@Bind(R.id.link_login)
    TextView _loginLink;
    ProgressDialog progress;

    AppCompatButton introduccion;
    TextToSpeech speach;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                hablar("Bienvenido al Museo del Ejecito y Fuerza Aerea");

                Toast.makeText(getBaseContext(), "Se han enviado tus datos", Toast.LENGTH_SHORT).show();
                //progress.show();
                Thread t = new Thread();
                try {
                    ProgressDialog progress = new ProgressDialog(SignupActivity.this);
                    progress.setMessage("Actualizando datos, por favor espere...");
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.setIndeterminate(false);

                    t.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(SignupActivity.this, Escaneo.class);
                startActivity(intent);


                //signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _cityText.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void hablar(String texto){
        speach.speak(texto, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _cityText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("nombre muy corto");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("introduce un email válido");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() /*|| password.length() < 4 || password.length() > 10*/) {
            _cityText.setError("debes introducir una ciudad");
            valid = false;
        } else {
            _cityText.setError(null);
        }

        return valid;
    }
}