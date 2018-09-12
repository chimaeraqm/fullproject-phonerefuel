package com.crazydwarf.phonerefuel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.crazydwarf.phonerefuel.R;
import com.crazydwarf.phonerefuel.view.DialogListItem;
import com.crazydwarf.phonerefuel.view.SimpleBottomSheetDialog;
import com.crazydwarf.phonerefuel.view.SimpleToolBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LogUpActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logup);
        SimpleToolBar toolBar = findViewById(R.id.toolbar_top);
        setSupportActionBar(toolBar);
        toolBar.setBackIconClickListener(new SimpleToolBar.BackIconClickListener() {
            @Override
            public void OnClick() {
                finish();
                Intent intent = new Intent(LogUpActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
