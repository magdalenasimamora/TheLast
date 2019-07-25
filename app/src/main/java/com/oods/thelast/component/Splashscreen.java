package com.oods.thelast.component;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oods.thelast.R;
import com.oods.thelast.dependencies.model.Perusahaan;
import com.oods.thelast.dependencies.realms.PerusahaanRealm;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class Splashscreen extends AppCompatActivity {

    private static Realm realm;

    static DatabaseReference databaseReference;

    static List<Perusahaan> list = new ArrayList<>();
    static List<PerusahaanRealm> listRealm = new ArrayList<>();
    static String DB_PATH = "Perusahaan";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference(DB_PATH);
        deletePerusahaanRealm();
        populateData();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        final int welcomeScreenDisplay = 3000; // 3000 = 3 detik
        Thread welcomeThread = new Thread() {

            int wait = 0;

            @Override
            public void run() {
                try {
                    super.run();
                    while (wait < welcomeScreenDisplay) {
                        sleep(100);
                        wait += 100;
                    }
                } catch (Exception e) {
                    System.out.println("EXc=" + e);

                } finally {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        };

        welcomeThread.start();
    }

    public static void insertRealm(List<Perusahaan> perusahaan){
        for (int i=0; i<perusahaan.size(); i++){
            realm.beginTransaction();
            PerusahaanRealm perusahaanRealm = realm.createObject(PerusahaanRealm.class);
            perusahaanRealm.setKey(perusahaan.get(i).getKey());
            perusahaanRealm.setNamaPerusahaan(perusahaan.get(i).getNamaPerusahaan());
            perusahaanRealm.setNamaPemimpin(perusahaan.get(i).getNamaPemimpin());
            perusahaanRealm.setEmail(perusahaan.get(i).getEmail());
            perusahaanRealm.setKontak(perusahaan.get(i).getKontak());
            perusahaanRealm.setBidangUsaha(perusahaan.get(i).getBidangUsaha());

            listRealm.add(perusahaanRealm);

            Log.e("per", perusahaan.get(i).toString());

            realm.commitTransaction();
        }

        Log.e("realm ", "" + listRealm.size());
    }

    public static void populateData(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    Log.e("post", postSnapshot.toString());
                    Perusahaan perusahaan = postSnapshot.getValue(Perusahaan.class);

                    Log.e("perusahaan", perusahaan.toString());

                    list.add(perusahaan);
                }

                insertRealm(list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.

            }
        });
    }

    public static void deletePerusahaanRealm(){
        realm.beginTransaction();
        realm.where(PerusahaanRealm.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }
}


