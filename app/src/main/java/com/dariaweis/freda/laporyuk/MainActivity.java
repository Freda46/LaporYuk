package com.dariaweis.freda.laporyuk;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseUser firebaseUser;

    private TextView nama;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nama = (TextView)findViewById(R.id.txtJudul);
        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null){
                    user = firebaseUser.getDisplayName();
                    nama.setText("Hai "+user+ ". Beritahu keadaanmu jika dalam keadaan darurat");

                }

                if(firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(MainActivity.this, login.class));
                }
            }
        };





        Button btnLapor  = (Button) findViewById(R.id.btnLapor);
        Button btnPihakB = (Button) findViewById(R.id.btnPihakBerwajib);
        Button btnKeluar = (Button)findViewById(R.id.btnKeluar);
        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
            }
        });
        btnLapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, lapor.class);
                i.putExtra("nama", user);
                startActivity(i);
            }
        });

        btnPihakB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, pihakberwajib.class));
            }
        });

    }
    /*
        @Override
        protected void onStop() {
            super.onStop();
            if(mAuthStateListener != null){
                FirebaseAuth.getInstance().signOut();
                mAuth.removeAuthStateListener(mAuthStateListener);
            }
        }
    */
    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthStateListener);
    }
}
