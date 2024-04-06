package com.example.reservas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
class onCompleteListener<T> implements com.google.android.gms.tasks.OnCompleteListener<com.google.firebase.auth.AuthResult> {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {

    }
}
public class RegistrarseActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

 private EditText correo;
 private EditText codigo;
 private EditText telefono;
 private String sexo;
 private EditText edad;
 private EditText contraseña;
 private EditText confirmarContraseña;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        mAuth = FirebaseAuth.getInstance();


        correo = findViewById(R.id.correo);

        codigo = findViewById(R.id.codigo);

        telefono = findViewById(R.id.telefono);


        edad = findViewById(R.id.edad);

        contraseña = findViewById(R.id.contraseña);

        confirmarContraseña = findViewById(R.id.confirmarContraseña);


    }

    public void onStart(){
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void registrarUsuario (View view){

        if (contraseña.getText().toString().equals(confirmarContraseña.getText().toString())){

            mAuth.createUserWithEmailAndPassword(correo.getText().toString(),contraseña.getText().toString())
                    .addOnCompleteListener(this, new onCompleteListener<AuthResult>(){
                       @Override
                        public void onComplete(@NonNull Task<AuthResult> task){
                           if (task.isSuccessful()){
                           Toast.makeText(getApplicationContext(),"Usuario Registrado.", Toast.LENGTH_SHORT).show();

                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);

                              //updateUi(user);


                           }else {

                              Toast.makeText(getApplicationContext(),"Authentication failed.", Toast.LENGTH_SHORT).show();
                                // updateUI(null);



                           }


                        }

                    });

        }else {
            Toast.makeText(this, "Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
        }

    }


}