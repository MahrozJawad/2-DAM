package com.example.tema12.cloudfirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    EditText user;
    EditText passwrod;
    Button login;
    Button register;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
            mAuth.signOut();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        user = findViewById(R.id.usuario);
        passwrod = findViewById(R.id.contraseñs);
        login = findViewById(R.id.identificar);
        register = findViewById(R.id.registrar);

        /*mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    Toast.makeText(MainActivity.this,user.getEmail() + " Logged in", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this,"User Null", Toast.LENGTH_SHORT).show();
            }
        };*/

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(user.getText().toString(), passwrod.getText().toString())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Usuario creado", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(user.getText().toString(), passwrod.toString())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Usuario no existe, pulse a rigistrar para registrar", Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(MainActivity.this, "logged in", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }



}
