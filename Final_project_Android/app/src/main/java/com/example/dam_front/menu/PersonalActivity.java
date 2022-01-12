package com.example.dam_front.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dam_front.R;
import com.example.dam_front.personal.PersonalCreateActivity;
import com.example.dam_front.personal.PersonalDeleteActivity;
import com.example.dam_front.personal.PersonalGetActivity;
import com.example.dam_front.personal.PersonalUpdateActivity;
import com.example.dam_front.studies.StudiesCreateActivity;
import com.example.dam_front.studies.StudiesDeleteActivity;
import com.example.dam_front.studies.StudiesGetActivity;
import com.example.dam_front.studies.StudiesUpdateActivity;

public class PersonalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        Button get_personal_btn = findViewById(R.id.get_personal_btn);
        Button update_personal_btn = findViewById(R.id.update_personal_btn);
        Button create_personal_btn = findViewById(R.id.create_personal_btn);
        Button delete_personal_btn = findViewById(R.id.delete_personal_btn);

        get_personal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_get_activity = new Intent(PersonalActivity.this, PersonalGetActivity.class);
                startActivity(intent_get_activity);
            }
        });

        update_personal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_update_activity = new Intent(PersonalActivity.this, PersonalUpdateActivity.class);
                startActivity(intent_update_activity);
            }
        });

        create_personal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_create_activity = new Intent(PersonalActivity.this, PersonalCreateActivity.class);
                startActivity(intent_create_activity);
            }
        });

        delete_personal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_delete_activity = new Intent(PersonalActivity.this, PersonalDeleteActivity.class);
                startActivity(intent_delete_activity);
            }
        });
    }
}
