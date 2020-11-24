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

public class Cadastro extends AppCompatActivity {

    private EditText id_DigiteEmail, id_DigiteSenha;
    private Button id_Voltar, id_Registrar;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializaComponentes();
        eventoClicks();
    }
    private void  eventoClicks(){
        id_Voltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        id_Registrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String email= id_DigiteEmail.getText().toString().trim();
                String senha= id_DigiteSenha.getText().toString().trim();
                criarUser(email,senha);
            }
        });
    }
    private void criarUser(String email, String senha){
        auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    alert("usuário Cadastrado com sucesso");
                    Intent i = new Intent(Cadastro.this,Perfil.class);
                    startActivity(i);
                    finish();
                }else{
                    alert("Erro de cadastro");

                }
            }
        });
    }
    private void alert(String msg){
        Toast.makeText(Cadastro.this,msg, Toast.LENGTH_SHORT).show();
    }

    public void inicializaComponentes(){
        id_DigiteEmail=(EditText) findViewById(R.id.id_DigiteEmail);
        id_DigiteSenha=(EditText)findViewById(R.id.id_DigiteSenha);
        id_Voltar=(Button)findViewById(R.id.id_Voltar);
        id_Registrar=(Button)findViewById(R.id.id_Registrar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}