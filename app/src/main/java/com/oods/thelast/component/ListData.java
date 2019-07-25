package com.oods.thelast.component;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;

import com.oods.thelast.R;
import com.oods.thelast.component.adapters.BangunanAdapter;
import com.oods.thelast.dependencies.realms.PerusahaanRealm;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class ListData extends AppCompatActivity implements BangunanAdapter.OnClickBangunanListener {

    Realm realm;

    RecyclerView recyclerView;

    BangunanAdapter adapter;
    ScaleInAnimationAdapter scaleInAnimationAdapter;

    List<PerusahaanRealm> listPerusahaan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        recyclerView = findViewById(R.id.rcList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        populateData();
    }

    private void populateData(){
        realm.executeTransactionAsync(realm1 -> listPerusahaan = realm1.copyFromRealm(realm1.where(PerusahaanRealm.class).findAll()), () -> {
            if (!listPerusahaan.isEmpty()) {
                adapter = new BangunanAdapter(this, listPerusahaan, this);
                scaleInAnimationAdapter = new ScaleInAnimationAdapter(adapter);
                recyclerView.setAdapter(scaleInAnimationAdapter);

                Log.e("list", listPerusahaan.toString());
            }
        });
    }

    @Override
    public void OnClickBangunan(String idBangunan) {
        startActivity(DetailJasa.createIntent(this, idBangunan));
    }
}
