package com.example.dam_front.studies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dam_front.MenuActivity;
import com.example.dam_front.R;
import com.example.dam_front.user.UserCreateActivity;

public class StudiesCreateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studies_create);

        EditText editText_institution = findViewById(R.id.input_new_institution);
        EditText editText_profile = findViewById(R.id.input_new_profile);
        EditText editText_baseSalary = findViewById(R.id.input_new_baseSalary);
        Button submit = findViewById(R.id.personal_create_submit);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String institution = editText_institution.getText().toString();
                String profile = editText_profile.getText().toString();
                String baseSalary = editText_baseSalary.getText().toString();

                String url = "http://192.168.0.174:8081/studies?institution=" + institution + "&profile="+ profile +"&baseSalary=" + baseSalary;

                RequestQueue queue = Volley.newRequestQueue(StudiesCreateActivity.this);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string
                                System.out.println(response);
                                Toast.makeText(StudiesCreateActivity.this, "Study created", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                        Toast.makeText(StudiesCreateActivity.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });
    }
}
