package com.example.dam_front.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dam_front.contract.ContractGetActivity;
import com.example.dam_front.R;

public class ContractActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);

        Button get_contarct_btn = findViewById(R.id.get_contract_btn);
        Button update_contract_btn = findViewById(R.id.update_contract_btn);
        Button create_contract_btn = findViewById(R.id.create_contract_btn);
        Button delete_contract_btn = findViewById(R.id.delete_contract_btn);

        get_contarct_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_get_activity = new Intent(ContractActivity.this, ContractGetActivity.class);
                startActivity(intent_get_activity);
            }
        });

        create_contract_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContractActivity.this, "Contract created", Toast.LENGTH_SHORT).show();
            }
        });

        update_contract_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContractActivity.this, "Contract updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete_contract_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContractActivity.this, "Contract deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
