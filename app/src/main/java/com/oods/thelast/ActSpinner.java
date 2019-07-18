package com.oods.thelast;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ActSpinner extends Activity implements AdapterView.OnItemSelectedListener {

    // A SharedPreferences for reading data
    private SharedPreferences pref;
    // A SharedPreferences.Editor for writing data
    private SharedPreferences.Editor editor;
    private final String KEY_NAME = "name";
    private final String KEY_NAMEPIM = "name pemimpin";
    private final String KEY_EMAIL = "email";
    private final String KEY_MOBILE = "mobile";
    private final String KEY_BIDANGUSAHA = "bidang usaha";
    String[] items ={"Jasa Pelaksana Konstruksi Pemasangan Pendingin Udara (Air Conditioner), " +
            "Jasa Pelaksana Konstruksi Pemasangan Pipa air (Plumbing) Dalam Bangunan dan Salurannya",
            "Jasa Pelaksana Konstruksi Pemasangan Pipa Gas Dalam Bangunan",
            "Jasa Pelaksana Konstruksi Insulasi Dalam Bangunan",
            "Jasa Pelaksana Konstruksi Pemasangan Lift dan Tangga Berjalan",
            "Jasa Pelaksana Konstruksi Pertambangan dan Manufaktur",
            "Jasa Pelaksana Konstruksi Instalasi Thermal, Bertekanan, Minyak dan Gas, Geothermal (Pekerjaan Rekayasa)",
            "Jasa Pelaksana Konstruksi Instalasi Alat Angkut dan Alat Angkat",
            "Jasa Pelaksana Konstruksi Instalasi Perpipaan, Gas, Energi (Pekerjaan Rekayasa)",
            "Jasa Pelaksana Konstruksi Instalasi Pembangkit Tenaga Listrik Semua Daya",
            "Jasa Pelaksana Konstruksi Instalasi Jaringan Transmisi Tenaga Listrik Tegangan Tinggi/Ekstra Tegangan Tinggi"
    };
    private TextView pilihan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_spinner);
        pref = getSharedPreferences("mypreferences", MODE_PRIVATE);
        String savedName = pref.getString(KEY_NAME, "-");
        TextView tvSavedName = (TextView) findViewById(R.id.txtViewName);
        tvSavedName.setText(savedName);
        final EditText Name = (EditText) findViewById(R.id.txtName);
        Button bSave = (Button) findViewById(R.id.btnSimpan);
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String name = Name.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(ActSpinner.this,
                            "Nama tidak boleh kosong",
                            Toast.LENGTH_SHORT).show();
                } else {
                    //Initialized prefs object to initialize editor object by calling the edit method
                    editor = pref.edit();
                    //Save the name that we have in a string
//Then write the data to the internal memory of the device
                    editor.putString(KEY_NAME, name);
                    editor.commit();
                    //Reload the name values that the previous code saved.
//We could even declare our variables and initialize themwith the stored values:
                    String savedName = pref.getString(KEY_NAME, "-");
                    TextView tvSavedName = (TextView)
                            findViewById(R.id.txtViewName);
                    tvSavedName.setText(savedName);
                }
            }
        });


        pilihan=(TextView) findViewById(R.id.lblPilih);
        Spinner spin=(Spinner) findViewById(R.id.spnPilih);
        spin.setOnItemSelectedListener(this);

        //Bagian yang menyebutkan desain tampilan yang akan digunakan
        //oleh Spinner dan data yang akan digunakan oleh Spinner.
        //Untuk kasus ini, data pilihan di-supply oleh array items
        ArrayAdapter<String> pil=new ArrayAdapter
                <String>(this,android.R.layout.simple_spinner_item,items);
        pil.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(pil);
    }

    public void onItemSelected(AdapterView<?> parent, View v, int
            posisi, long id){
        pilihan.setText(items[posisi]);
    }

    public void onNothingSelected(AdapterView<?> parent){
        pilihan.setText("");
    }

}