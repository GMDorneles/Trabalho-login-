package br.trabalho.testedelogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText id_Email, id_Senha;
    private Button id_Logar, id_Novo;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializaComponentes();
        eventoClicks();
    }
    private void eventoClicks(){
        id_Novo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),Cadastro.class);
                startActivity(i);
            }
        });
        id_Logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String email = id_Email.getText().toString().trim();
               String senha = id_Senha.getText().toString().trim();
               login(email,senha);
            }
        });
    }
    private void login(String email, String senha){
        auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent i =new Intent(Login.this,Perfil.class);
                    startActivity(i);
                }else{
                    alert("email ou  senha incorretos");
                }

            }
        });

    }
    private void alert(String s){
        Toast.makeText(Login.this,s,Toast.LENGTH_SHORT).show();
    }
    private void inicializaComponentes(){
        id_Email = (EditText) findViewById(R.id.id_Email);
        id_Senha= (EditText) findViewById(R.id.id_Senha);
        id_Logar=(Button) findViewById(R.id.id_Logar);
        id_Novo=(Button) findViewById(R.id.id_Novo);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}