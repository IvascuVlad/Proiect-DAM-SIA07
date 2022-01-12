package com.example.dam_front;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dam_front.menu.ContractActivity;
import com.example.dam_front.menu.PersonalActivity;
import com.example.dam_front.menu.PositionActivity;
import com.example.dam_front.menu.StudiesActivity;
import com.example.dam_front.menu.UserActivity;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String id = getIntent().getStringExtra("PATH");

        Button user_btn = findViewById(R.id.user_btn);
        Button personal_btn = findViewById(R.id.personal_btn);
        Button studies_btn = findViewById(R.id.studies_btn);
        Button positions_btn = findViewById(R.id.position_btn);
        Button contract_btn = findViewById(R.id.contract_btn);

        user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_user_activity = new Intent(MenuActivity.this, UserActivity.class);
                intent_user_activity.putExtra("PATH", id);
                startActivity(intent_user_activity);
            }
        });

        personal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_personal_activity = new Intent(MenuActivity.this, PersonalActivity.class);
                intent_personal_activity.putExtra("PATH", id);
                startActivity(intent_personal_activity);
            }
        });

        studies_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_studies_activity = new Intent(MenuActivity.this, StudiesActivity.class);
                intent_studies_activity.putExtra("PATH", id);
                startActivity(intent_studies_activity);
            }
        });

        positions_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_positions_activity = new Intent(MenuActivity.this, PositionActivity.class);
                intent_positions_activity.putExtra("PATH", id);
                startActivity(intent_positions_activity);
            }
        });

        contract_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_contract_activity = new Intent(MenuActivity.this, ContractActivity.class);
                intent_contract_activity.putExtra("PATH", id);
                startActivity(intent_contract_activity);
            }
        });
    }
}
