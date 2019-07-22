package com.oods.thelast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oods.thelast.model.Perusahaan;

public class DetailJasa extends AppCompatActivity {

    TextView perusahaan, pemilik, email, mobile, jasa;

    private static Perusahaan perusahaans;

//    DatabaseReference databaseReference;
//    public static String DB_PATH = "forum-20846";

    public static Intent createIntent(Context context, Perusahaan perusahaan) {
        perusahaans = perusahaan;
        return new Intent(context, DetailJasa.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jasa);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Bangunan Gedung");

        perusahaan = findViewById(R.id.perusahaan);
        pemilik = findViewById(R.id.pemilik);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        jasa = findViewById(R.id.jasa);

        fillData();

//        databaseReference = FirebaseDatabase.getInstance().getReference(DB_PATH + "/Perusahaan/" + key);
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Perusahaan perusahaan = dataSnapshot.getValue(Perusahaan.class);
//                System.out.println(perusahaan.toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getCode());
//            }
//        });
    }

    private void fillData(){
        perusahaan.setText(perusahaans.getNamaPerusahaan());
        pemilik.setText(perusahaans.getNamaPemimpin());
        email.setText(perusahaans.getEmail());
        mobile.setText(perusahaans.getKontak());
        jasa.setText(perusahaans.getBidangUsaha());
    }
}
