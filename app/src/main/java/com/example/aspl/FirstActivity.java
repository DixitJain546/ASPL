package com.example.aspl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this,R.color.navColor));
        }
        setContentView(R.layout.activity_first);
        CardView getStarted=findViewById(R.id.buttonGetStarted);
        CardView alreadyMember=findViewById(R.id.buttonAlreadyMember);
        final Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                 startActivity(intent);
            }
        });
        alreadyMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(intent);
            }
        });
    }
}
