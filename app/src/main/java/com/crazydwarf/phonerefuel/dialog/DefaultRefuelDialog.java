package com.crazydwarf.phonerefuel.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.crazydwarf.phonerefuel.R;

public class DefaultRefuelDialog extends Dialog
{
    private Context mContext;

    public DefaultRefuelDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_default_refuel);

        Button bnConfirm = findViewById(R.id.bn_confirm);
        bnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "保存缺省的充值金额", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        Button bnExit = findViewById(R.id.bn_exit);
        bnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "放弃当前操作", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

    }
}
