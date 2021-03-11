package com.example.activitylifecicle_erick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //para mensaje en consola
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    //mensaje mediante clave-valor definida public para poder usarla tambien en la SecondActivity
    public static final String EXTRA_MESSAGE = "com.example.activitylifecicle_erick.extra.MESSAGE";

    //para guardar lo del edittext
    private EditText mMessageEditText;

    //para saber si hubo respuesta
    public static final int TEXT_REQUEST = 1;

    //para guardar estos contenidos
    private TextView mReplyHeaderTextView;
    private TextView mReplyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        //guardamos el id del edittext
        mMessageEditText = findViewById(R.id.editText_main);

        //guardamos los id de los encabezados
        mReplyHeaderTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);

        //restaura el estado
        if (savedInstanceState != null) {
            boolean isVisible =
                    savedInstanceState.getBoolean("reply_visible");

            if (isVisible) {
                mReplyHeaderTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(savedInstanceState.getString("reply_text"));
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }

    }//fin  del método onCreate

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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mReplyHeaderTextView.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);

            outState.putString("reply_text",mReplyTextView.getText().toString());
        }
    }

    public void launchSecondActivity(View view) {
        //mensaje en consola
        Log.d(LOG_TAG, "Button clicked");

        //se obtiene lo que se escribio en el edittext
        String message = mMessageEditText.getText().toString();

        /*creamos objeto Intent para segunda activity, en el primer parámetro hacemos referencia a la clase actual y el segundo a
        la clase a la que vamos en este caso nuestra SecondActivity*/
        Intent intent = new Intent(this, secondActivity.class);

        /*con nuestro objeto intent creado ahora tomamos el valor del id que es el valor clave, el segundo parámetro es el texto
        real obtenido del mMessageEditText*/
        intent.putExtra(EXTRA_MESSAGE, message);

        //se abre la segunda activity
        startActivityForResult(intent, TEXT_REQUEST);
    }//fin del método launchSecondActivity

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TEXT_REQUEST){
            if(resultCode == RESULT_OK){
                String reply = data.getStringExtra(secondActivity.EXTRA_REPLY);

                mReplyHeaderTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }
}