package com.example.activitylifecicle_erick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class secondActivity extends AppCompatActivity {

    //mensaje mediante clave-valor definida public para poder usarla tambien en la Primera Activity
    public static final String EXTRA_REPLY = "com.example.activitylifecicle_erick.extra.REPLAY";

    private static final String LOG_TAG = secondActivity.class.getSimpleName();

    //variable para la edicion
    private EditText mReplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //obtenemos el intent disponible
        Intent intent = getIntent();

        //se obtiene el mensaje de la otra activity
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //referencia de nuestro textView
        TextView textView = findViewById(R.id.text_message);

        //lo ponemos en él
        textView.setText(message);

        //obtenemos la referencia que fue escrito en nuestra segunda activity EditText y lo guardamos en mReplay
        mReplay = findViewById(R.id.editText_second);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    public void returnReply(View view) {
        //obtenemos lo de mReply
        String reply = mReplay.getText().toString();

        //Creamos un objeto intent
        Intent replyIntent = new Intent();

        /*ahora tomamos el valor del id que es el valor clave, el segundo parámetro es el texto
        real obtenido del reply*/
        replyIntent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK, replyIntent);

        Log.d(LOG_TAG, "End SecondActivity");

        //cerramos la actividad
        finish();
    }
}