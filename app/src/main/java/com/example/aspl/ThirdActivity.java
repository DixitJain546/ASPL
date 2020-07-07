package com.example.aspl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        }
        setContentView(R.layout.activity_third);
        final EditText mobile=findViewById(R.id.editMobileNo);
        final EditText pass=findViewById(R.id.editPassword);
        CardView proceed=findViewById(R.id.buttonProceed);
        final String[] message = {null};
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mobileNo = mobile.getText().toString();
                final String password = pass.getText().toString();

                //validating inputs
                if (TextUtils.isEmpty(mobileNo)) {
                    mobile.setError("Please enter your mobile");
                    mobile.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    pass.setError("Please enter your password");
                    pass.requestFocus();
                    return;
                }

                String url = "https://mekvahan.com/api/android_intern_task";
                final TextView textView = null;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                JSONObject obj = null;
                                try {
                                    obj = new JSONObject(response);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                //if no error in response
                                try {
                                    message[0] = obj.getString("status");
                                    Toast.makeText(getApplicationContext(), "Message : "+obj.getString("status"), Toast.LENGTH_SHORT).show();
                                    if(message[0].equals("true")) {
                                        Toast.makeText(getApplicationContext(),"Booking Successful",Toast.LENGTH_LONG).show();
                                        Intent i=new Intent(ThirdActivity.this,FirstActivity.class);
                                        startActivity(i);
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("Invalid Login");
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("mobile", mobileNo);
                        params.put("password", password);
                        return params;
                    }
                };

                RequestQueue queue = Volley.newRequestQueue(ThirdActivity.this);
                queue.add(stringRequest);
            }
        });

    }
}




