package com.dariaweis.freda.laporyuk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class pihakberwajib extends AppCompatActivity {
    private DatabaseReference database;
    private Button btnLokasi;

    private TextView txtUser;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<freda> daftarfreda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pihakberwajib);


        btnLokasi = (Button)findViewById(R.id.btnLokasi);
        recyclerView = (RecyclerView)findViewById(R.id.rv_main);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        database = FirebaseDatabase.getInstance().getReference();
        database.child("freda").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<freda> daftarfreda = new ArrayList<freda>();
                for(DataSnapshot noteDataSnapshot: dataSnapshot.getChildren()){
                    freda freda = noteDataSnapshot.getValue(freda.class);

                    daftarfreda.add(0, freda);


                }

                adapter = new adapterfreda(daftarfreda, pihakberwajib.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
