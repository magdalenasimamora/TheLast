package com.oods.thelast.component;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.oods.thelast.R;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView jasaId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        jasaId = (CardView) findViewById(R.id.jasaId);
        jasaId.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AdminActivity.this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.jasaId : i = new Intent(this, ListData.class);
            startActivity(i);
            break;

            case R.id.aboutId : i = new Intent(this, ListData.class);
            startActivity(i);
            break;

            case R.id.exitId : i = new Intent(this, ListData.class);
            startActivity(i);
            break;
        }

    }
}

