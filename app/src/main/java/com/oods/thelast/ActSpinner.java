package com.oods.thelast;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oods.thelast.model.Perusahaan;

public class ActSpinner extends Activity implements AdapterView.OnItemSelectedListener {
    private Button btnSimpan;
    private EditText namaPerusahaan;
    private EditText namaPimpinan;
    private EditText email;
    private EditText kontak;
    private Spinner bidangUsaha;

    private DatabaseReference database;

    // A SharedPreferences for reading data
    private SharedPreferences pref;
    // A SharedPreferences.Editor for writing data
    private SharedPreferences.Editor editor;
    private final String KEY_NAME = "name";
    private final String KEY_NAMEPIM = "name pemimpin";
    private final String KEY_EMAIL = "email";
    private final String KEY_MOBILE = "mobile";
    private final String KEY_BIDANGUSAHA = "bidang usaha";
//    String[] items ={"Jasa Pelaksana Konstruksi Pemasangan Pendingin Udara (Air Conditioner), " +
//            "Jasa Pelaksana Konstruksi Pemasangan Pipa air (Plumbing) Dalam Bangunan dan Salurannya",
//            "Jasa Pelaksana Konstruksi Pemasangan Pipa Gas Dalam Bangunan",
//            "Jasa Pelaksana Konstruksi Insulasi Dalam Bangunan",
//            "Jasa Pelaksana Konstruksi Pemasangan Lift dan Tangga Berjalan",
//            "Jasa Pelaksana Konstruksi Pertambangan dan Manufaktur",
//            "Jasa Pelaksana Konstruksi Instalasi Thermal, Bertekanan, Minyak dan Gas, Geothermal (Pekerjaan Rekayasa)",
//            "Jasa Pelaksana Konstruksi Instalasi Alat Angkut dan Alat Angkat",
//            "Jasa Pelaksana Konstruksi Instalasi Perpipaan, Gas, Energi (Pekerjaan Rekayasa)",
//            "Jasa Pelaksana Konstruksi Instalasi Pembangkit Tenaga Listrik Semua Daya",
//            "Jasa Pelaksana Konstruksi Instalasi Jaringan Transmisi Tenaga Listrik Tegangan Tinggi/Ekstra Tegangan Tinggi"
//    };
//    private TextView pilihan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_spinner);
        pref = getSharedPreferences("mypreferences", MODE_PRIVATE);
        String savedName = pref.getString(KEY_NAME, "-");
//        TextView tvSavedName = (TextView) findViewById(R.id.txtViewName);
//        tvSavedName.setText(savedName);
//        final EditText Name = (EditText) findViewById(R.id.etNamaPerusahaan);
        database = FirebaseDatabase.getInstance().getReference();
        btnSimpan = (Button) findViewById(R.id.btnSave);
        namaPerusahaan = (EditText) findViewById(R.id.etNamaPerusahaan);
        namaPimpinan = (EditText) findViewById(R.id.etNamaPimpinan);
        email = (EditText) findViewById(R.id.etEmail);
        kontak = (EditText) findViewById(R.id.etKontak);
        bidangUsaha = (Spinner) findViewById(R.id.spinBidangUsaha);
//        btnSimpan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                String name = namaPerusahaan.getText().toString();
//                if (name.equals("")) {
//                    Toast.makeText(ActSpinner.this,
//                            "Nama tidak boleh kosong",
//                            Toast.LENGTH_SHORT).show();
//                } else {
//                    //Initialized prefs object to initialize editor object by calling the edit method
//                    editor = pref.edit();
//                    //Save the name that we have in a string
////Then write the data to the internal memory of the device
//                    editor.putString(KEY_NAME, name);
//                    editor.commit();
//                    //Reload the name values that the previous code saved.
////We could even declare our variables and initialize themwith the stored values:
//                    String savedName = pref.getString(KEY_NAME, "-");
//                    TextView tvSavedName = (TextView)
//                            findViewById(R.id.txtViewName);
//                    tvSavedName.setText(savedName);
//                }
//            }
//        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEmpty(namaPerusahaan.getText().toString()) && !isEmpty(namaPimpinan.getText().toString()) && !isEmpty(email.getText().toString()) && !isEmpty(kontak.getText().toString()))
                    submitData(new Perusahaan(namaPerusahaan.getText().toString(), namaPimpinan.getText().toString(), email.getText().toString(), kontak.getText().toString(), bidangUsaha.getSelectedItem().toString()));
                else
                    Snackbar.make(findViewById(R.id.btnSave), "Data tidak boleh kosong", Snackbar.LENGTH_LONG).show();

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        namaPerusahaan.getWindowToken(), 0);
            }
        });


//        pilihan=(TextView) findViewById(R.id.lblPilih);
//        Spinner spin=(Spinner) findViewById(R.id.spinBidangUsaha);
//        spin.setOnItemSelectedListener(this);

        //Bagian yang menyebutkan desain tampilan yang akan digunakan
        //oleh Spinner dan data yang akan digunakan oleh Spinner.
        //Untuk kasus ini, data pilihan di-supply oleh array items
//        ArrayAdapter<String> pil=new ArrayAdapter
//                <String>(this,android.R.layout.simple_spinner_item,items);
//        pil.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spin.setAdapter(pil);

        Spinner spinner = (Spinner) findViewById(R.id.spinBidangUsaha);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.bidang_usaha_array, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View v, int
            pos, long id){
//        pilihan.setText(items[pos]);
        parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent){
//        pilihan.setText("");
    }

    private void submitData(Perusahaan perusahaan){
        database.child("Perusahaan").push().setValue(perusahaan).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                namaPerusahaan.setText("");
                namaPimpinan.setText("");
                email.setText("");
                kontak.setText("");
//                bidangUsaha.setTooltipText("");
                Snackbar.make(findViewById(R.id.btnSave), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private boolean isEmpty(String s) {
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }
}