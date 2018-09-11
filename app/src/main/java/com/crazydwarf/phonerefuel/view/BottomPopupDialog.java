package com.crazydwarf.phonerefuel.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.crazydwarf.phonerefuel.R;

public class BottomPopupDialog extends BottomSheetDialog
{
    private Context mContext;

    public BottomPopupDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow_main);
        TextView tv_LocalBalance = findViewById(R.id.tv_local_balance);
        TextView tv_ExchangeUpdate = findViewById(R.id.tv_exchange_update);
        TextView tv_UserLogup = findViewById(R.id.tv_user_logup);
        TextView tv_UserLogin = findViewById(R.id.tv_user_login);

        tv_LocalBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "clicked tv_LocalBalance", Toast.LENGTH_LONG).show();
            }
        });

        tv_ExchangeUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "clicked tv_ExchangeUpdate", Toast.LENGTH_LONG).show();
            }
        });

        tv_UserLogup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "clicked tv_UserLogup", Toast.LENGTH_LONG).show();
            }
        });

        tv_UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "clicked tv_UserLogin", Toast.LENGTH_LONG).show();
            }
        });
    }
}
