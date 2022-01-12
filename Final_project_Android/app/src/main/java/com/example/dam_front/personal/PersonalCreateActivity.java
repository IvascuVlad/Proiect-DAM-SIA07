package com.example.dam_front.personal;

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
import com.example.dam_front.R;
import com.example.dam_front.studies.StudiesCreateActivity;

public class PersonalCreateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_create);

        EditText editText_firstName = findViewById(R.id.input_new_firstName);
        EditText editText_lastName = findViewById(R.id.input_new_lastName);
        EditText editText_phoneNo = findViewById(R.id.input_new_phoneNo);
        EditText editText_address = findViewById(R.id.input_new_address);
        Button submit = findViewById(R.id.personal_create_submit);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = editText_firstName.getText().toString();
                String lastName = editText_lastName.getText().toString();
                String phoneNo = editText_phoneNo.getText().toString();
                String address = editText_address.getText().toString();

                String url = "http://192.168.0.174:8081/personal?firstName="+ firstName +"&lastName="+ lastName +"&phoneNo="+ phoneNo +"&address="+ address;

                RequestQueue queue = Volley.newRequestQueue(PersonalCreateActivity.this);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string
                                System.out.println(response);
                                Toast.makeText(PersonalCreateActivity.this, "Personal created", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                        Toast.makeText(PersonalCreateActivity.this, "BAD REQUEST", Toast.LENGTH_SHORT).show();
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });
    }
}
