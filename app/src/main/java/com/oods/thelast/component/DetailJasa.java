package com.oods.thelast.component;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.oods.thelast.R;
import com.oods.thelast.dependencies.realms.PerusahaanRealm;

import io.realm.Realm;

public class DetailJasa extends AppCompatActivity {

    TextView perusahaan, pemilik, email, mobile, jasa;

    PerusahaanRealm perusahaanRealm;
    Realm realm;

    String role = "";
    public static String keys;

    public static Intent createIntent(Context context, String key) {
        keys = key;
        return new Intent(context, DetailJasa.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jasa);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Bangunan Gedung");

        perusahaan = findViewById(R.id.perusahaan);
        pemilik = findViewById(R.id.pemilik);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        jasa = findViewById(R.id.jasa);

        fillData();
    }

    private void fillData(){
        realm.beginTransaction();
        perusahaanRealm = realm.where(PerusahaanRealm.class).equalTo("key", keys).findFirst();

        if (perusahaanRealm != null){
            perusahaan.setText(perusahaanRealm.getNamaPerusahaan());
            pemilik.setText(perusahaanRealm.getNamaPemimpin());
            email.setText(perusahaanRealm.getEmail());
            mobile.setText(perusahaanRealm.getKontak());
            jasa.setText(perusahaanRealm.getBidangUsaha());

        }
        realm.commitTransaction();
    }
}
