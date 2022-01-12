package com.example.dam_front.personal;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dam_front.R;
import com.example.dam_front.studies.StudiesGetActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PersonalGetActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_get);

        TextView textView = findViewById(R.id.textView_personal);

        RequestQueue queue = Volley.newRequestQueue(PersonalGetActivity.this);
        String url ="http://192.168.0.174:8081/personal";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            String tmp_response = "";
                            for(int i = 0; i< array.length();i++){
                                JSONObject object = (JSONObject) array.get(i);
                                tmp_response += object.toString(1);
                            }
                            //JSONObject object = (JSONObject) array.get(1);
                            textView.setText(tmp_response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
                Toast.makeText(PersonalGetActivity.this, "BAD REQUEST", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
        Toast.makeText(PersonalGetActivity.this, "Studies", Toast.LENGTH_SHORT).show();
    }
}
