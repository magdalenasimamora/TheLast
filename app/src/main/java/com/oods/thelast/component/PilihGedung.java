package com.oods.thelast.component;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.oods.thelast.R;

public class PilihGedung extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_gedung);

        CardView cvGedung = findViewById(R.id.cvGedung);
        cvGedung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddJasa.class);
                startActivity(intent);
            }
        });
        CardView cvSipil = findViewById(R.id.cvSipil);
        cvSipil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddJasa.class);
                startActivity(intent);
            }
        });
        CardView cvElektrikal = findViewById(R.id.cvElektrikal);
        cvElektrikal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddJasa.class);
                startActivity(intent);
            }
        });
        CardView cvSpesialis = findViewById(R.id.cvSpesialis);
        cvSpesialis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddJasa.class);
                startActivity(intent);
            }
        });
        CardView cvKeterampilan = findViewById(R.id.cvKeterampilan);
        cvKeterampilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddJasa.class);
                startActivity(intent);
            }
        });
    }
}
