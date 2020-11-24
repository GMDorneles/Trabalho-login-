package br.trabalho.testedelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button id_Acessar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaComponentes();
        eventoClicks();
    }
    private void eventoClicks(){
        id_Acessar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });
    }
    private void inicializaComponentes(){
        id_Acessar=(Button) findViewById(R.id.id_Acessar);
    }
}