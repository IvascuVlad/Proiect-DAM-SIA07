package com.example.dam_front.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dam_front.R;
import com.example.dam_front.contract.ContractGetActivity;
import com.example.dam_front.studies.StudiesCreateActivity;
import com.example.dam_front.studies.StudiesDeleteActivity;
import com.example.dam_front.studies.StudiesGetActivity;
import com.example.dam_front.studies.StudiesUpdateActivity;

public class StudiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studies);

        Button get_studies_btn = findViewById(R.id.get_studies_btn);
        Button update_studies_btn = findViewById(R.id.update_studies_btn);
        Button create_studies_btn = findViewById(R.id.create_studies_btn);
        Button delete_studies_btn = findViewById(R.id.delete_studies_btn);

        get_studies_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_get_activity = new Intent(StudiesActivity.this, StudiesGetActivity.class);
                startActivity(intent_get_activity);
            }
        });

        update_studies_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_update_activity = new Intent(StudiesActivity.this, StudiesUpdateActivity.class);
                startActivity(intent_update_activity);
            }
        });

        create_studies_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_create_activity = new Intent(StudiesActivity.this, StudiesCreateActivity.class);
                startActivity(intent_create_activity);
            }
        });

        delete_studies_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_delete_activity = new Intent(StudiesActivity.this, StudiesDeleteActivity.class);
                startActivity(intent_delete_activity);
            }
        });
    }
}
