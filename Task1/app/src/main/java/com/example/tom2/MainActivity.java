package com.example.tom2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private EditText editTextTextPersonName11;
    private EditText editTextTextPersonName21;
    private EditText editTextTextPersonName31;
    private Switch switch11;
    private int r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextTextPersonName11=findViewById(R.id.editTextTextPersonName1);
        editTextTextPersonName21=findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName31=findViewById(R.id.editTextTextPersonName3);
        switch11=findViewById(R.id.switch1);
        textView=findViewById(R.id.textView3);
        r=1;
    }
    public void infoRequest (View view){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://jsonplaceholder.typicode.com/todos/"+String.valueOf(r);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson=builder.create();
                        JsonD jsonD8=gson.fromJson(response,JsonD.class);
                        editTextTextPersonName11.setText(String.valueOf(jsonD8.userId));
                        editTextTextPersonName21.setText(String.valueOf(jsonD8.id));
                        editTextTextPersonName31.setText(jsonD8.title);
                        switch11.setChecked(jsonD8.completed);
                        textView.setText(response);
                        r++;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work");
            }
        });
        queue.add(stringRequest);
    }

}