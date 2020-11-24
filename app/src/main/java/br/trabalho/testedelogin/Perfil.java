package br.trabalho.testedelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil extends AppCompatActivity {
    private TextView id_PerfilEmail, id_PerfilId;
    private Button id_Logout;

    private FirebaseAuth auth;
    private FirebaseUser User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        inicializaComponentes();
        eventoClick();
    }
    private void eventoClick(){
        id_Logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Conexao.logOut();
                finish();
            }
        });
    }
    private void inicializaComponentes(){
        id_PerfilEmail=(TextView) findViewById(R.id.id_PerfilEmail);
        id_PerfilId=(TextView) findViewById(R.id.id_PerfilEmail);
        id_Logout=(Button) findViewById(R.id.id_Logout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth=Conexao.getFirebaseAuth();
        User=Conexao.getFirebaseUser();
        verificaUser();
    }
    private void verificaUser(){
        if(User == null){
            finish();
        }else {
            id_PerfilEmail.setText("Email: "+User.getEmail());
            id_PerfilId.setText("ID: "+User.getUid());
        }
    }
}