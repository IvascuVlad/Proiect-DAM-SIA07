package com.example.dam_front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText_username = findViewById(R.id.input_username);
        EditText editText_password = findViewById(R.id.input_parola);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editText_username.getText().toString();
                String password = editText_password.getText().toString();

                username = "ivascuvl";
                password = "vlad";

                if(username.length() > 0 && password.length() > 0){
                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    String url ="http://192.168.0.174:8081/user?userName="+ username + "&password=" + password;

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the first 500 characters of the response string
                                    String id = response.substring(response.indexOf("(") + 1);
                                    id = id.substring(0, id.indexOf(")"));
                                    Intent intent_menu_activity = new Intent(MainActivity.this, MenuActivity.class);
                                    intent_menu_activity.putExtra("PATH", id);
                                    startActivity(intent_menu_activity);

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println(error.toString());
                            Toast.makeText(MainActivity.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
                        }
                    });

                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);
                }
            }
        });
    }
}