package com.example.dam_front.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dam_front.MainActivity;
import com.example.dam_front.MenuActivity;
import com.example.dam_front.R;

public class UserCreateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create);

        EditText editText_username = findViewById(R.id.input_new_username);
        EditText editText_password = findViewById(R.id.input_new_parola);
        EditText editText_email = findViewById(R.id.input_new_email);
        EditText editText_personal = findViewById(R.id.input_new_personal);
        EditText editText_studies = findViewById(R.id.input_new_studies);

        String username = editText_username.getText().toString();
        String password = editText_password.getText().toString();
        String email = editText_email.getText().toString();
        String personal = editText_personal.getText().toString();
        String studies = editText_studies.getText().toString();

        String url = "http://192.168.0.174:8081/user?userName="+ username +"&password="+ password +"&email="+ email +"&personalID="+ personal +"&studiesID=" + studies;

        RequestQueue queue = Volley.newRequestQueue(UserCreateActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string
                        String id = response.substring(response.indexOf("(") + 1);
                        id = id.substring(0, id.indexOf(")"));
                        Intent intent_menu_activity = new Intent(UserCreateActivity.this, MenuActivity.class);
                        intent_menu_activity.putExtra("PATH", id);
                        startActivity(intent_menu_activity);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
                Toast.makeText(UserCreateActivity.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
