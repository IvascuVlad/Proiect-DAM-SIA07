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
import com.example.dam_front.studies.StudiesUpdateActivity;

public class PersonalUpdateActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_update);

        EditText editText_id = findViewById(R.id.input_update_id);
        EditText editText_phone = findViewById(R.id.input_update_phone);
        EditText editText_address = findViewById(R.id.input_update_address);
        Button submit = findViewById(R.id.personal_update_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editText_id.getText().toString();
                String phone = editText_phone.getText().toString();
                String address = editText_address.getText().toString();

                String url = "http://192.168.0.174:8081/personal/"+ id + "?phoneNo="+ phone +"&address=" + address;

                RequestQueue queue = Volley.newRequestQueue(PersonalUpdateActivity.this);

                StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string
                                System.out.println(response);
                                Toast.makeText(PersonalUpdateActivity.this, "Personal updated", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                        Toast.makeText(PersonalUpdateActivity.this, "BAD REQUEST", Toast.LENGTH_SHORT).show();
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });
    }
}
