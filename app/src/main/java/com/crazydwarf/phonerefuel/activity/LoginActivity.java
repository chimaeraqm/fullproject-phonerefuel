package com.crazydwarf.phonerefuel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.crazydwarf.phonerefuel.R;
import com.crazydwarf.phonerefuel.view.SimpleToolBar;

public class LoginActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SimpleToolBar toolBar = findViewById(R.id.toolbar_top);
        setSupportActionBar(toolBar);
        toolBar.setBackIconClickListener(new SimpleToolBar.BackIconClickListener() {
            @Override
            public void OnClick() {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button bnLogin = findViewById(R.id.bn_login);
        bnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("LOGIN",true);
                startActivity(intent);
                finish();
            }
        });
    }
}
