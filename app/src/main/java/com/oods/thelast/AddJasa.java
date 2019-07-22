package com.oods.thelast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oods.thelast.enums.BidangJasa;
import com.oods.thelast.model.Perusahaan;

public class AddJasa extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button btnSave;
    private EditText input_perusahaan;
    private EditText input_pimpinan;
    private EditText input_email;
    private EditText input_mobile;
    private Spinner input_jasa;

    private DatabaseReference database;
    private Perusahaan perusahaan;
    private String key;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private final String KEY_NAME = "name";
    private final String KEY_NAMEPIM = "name pemimpin";
    private final String KEY_EMAIL = "email";
    private final String KEY_MOBILE = "mobile";
    private final String KEY_BIDANGUSAHA = "bidang usaha";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_jasa);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Bangunan Gedung");

        pref = getSharedPreferences("mypreferences", MODE_PRIVATE);
        String savedName = pref.getString(KEY_NAME, "-");
        database = FirebaseDatabase.getInstance().getReference();

        input_perusahaan = findViewById(R.id.input_perusahaan);
        input_pimpinan = findViewById(R.id.input_pimpinan);
        input_email = findViewById(R.id.input_email);
        input_mobile = findViewById(R.id.input_mobile);
        input_jasa = findViewById(R.id.input_jasa);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEmpty(input_perusahaan.getText().toString()) && !isEmpty(input_pimpinan.getText().toString()) && !isEmpty(input_email.getText().toString()) && !isEmpty(input_mobile.getText().toString())){
                    submitData();

                    startActivity(DetailJasa.createIntent(getApplicationContext(), perusahaan));
                }
                else
                    Snackbar.make(findViewById(R.id.btnSave), "Data tidak boleh kosong", Snackbar.LENGTH_LONG).show();

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        input_perusahaan.getWindowToken(), 0);
            }
        });

        input_jasa.setAdapter(new ArrayAdapter<BidangJasa>(this.getApplicationContext(), R.layout.spinner_item, BidangJasa.values()) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.BLACK);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private boolean isEmpty(String s) {
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }

    private void submitData(){
        key = database.push().getKey();
        perusahaan = new Perusahaan(key, input_perusahaan.getText().toString(), input_pimpinan.getText().toString(), input_email.getText().toString(), input_mobile.getText().toString(), input_jasa.getSelectedItem().toString());

        database.child("Perusahaan").child(key).setValue(perusahaan).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                input_perusahaan.setText("");
                input_pimpinan.setText("");
                input_email.setText("");
                input_mobile.setText("");
                Snackbar.make(findViewById(R.id.btnSave), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

}
