package com.example.dam_front.menu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dam_front.MainActivity;
import com.example.dam_front.R;

public class UserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Button create_user_btn = findViewById(R.id.create_user_btn);
        Button update_user_btn = findViewById(R.id.update_user_btn);
        Button delete_user_btn = findViewById(R.id.delete_user_btn);

        create_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserActivity.this, "User created", Toast.LENGTH_SHORT).show();
            }
        });

        update_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserActivity.this, "User updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserActivity.this, "User deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
