package com.crazydwarf.phonerefuel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.crazydwarf.phonerefuel.R;
import com.crazydwarf.phonerefuel.view.SimpleToolBar;

public class BalanceCheckActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_check);

        SimpleToolBar toolBar = findViewById(R.id.toolbar_top);
        setSupportActionBar(toolBar);

        toolBar.setBackIconClickListener(new SimpleToolBar.BackIconClickListener() {
            @Override
            public void OnClick() {
                Intent intent = new Intent(BalanceCheckActivity.this,MainActivity.class);
                intent.putExtra("LOGIN",true);
                startActivity(intent);
                finish();
            }
        });
    }
}
