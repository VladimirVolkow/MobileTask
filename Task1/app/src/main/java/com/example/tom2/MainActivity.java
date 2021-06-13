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
    private TextView textViewReceiveBefore;
    private EditText userIdReceiveBefore;
    private EditText idReceiveBefore;
    private EditText titleReceiveBefore;
    private Switch completedReceiveBefore;
    private int enumeration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userIdReceiveBefore=findViewById(R.id.userIdReceive);
        idReceiveBefore=findViewById(R.id.idReceive);
        titleReceiveBefore=findViewById(R.id.titleReceive);
        completedReceiveBefore=findViewById(R.id.completedReceive);
        textViewReceiveBefore=findViewById(R.id.textViewReceive);
        enumeration=1;
    }
    public void infoRequest (View view){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://jsonplaceholder.typicode.com/todos/"+String.valueOf(enumeration);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson=builder.create();
                        Deserialize deserialize =gson.fromJson(response, Deserialize.class);
                        userIdReceiveBefore.setText(String.valueOf(deserialize.userId));
                        idReceiveBefore.setText(String.valueOf(deserialize.id));
                        titleReceiveBefore.setText(deserialize.title);
                        completedReceiveBefore.setChecked(deserialize.completed);
                        textViewReceiveBefore.setText(response);
                        enumeration++;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textViewReceiveBefore.setText("That didn't work");
            }
        });
        queue.add(stringRequest);
    }
}