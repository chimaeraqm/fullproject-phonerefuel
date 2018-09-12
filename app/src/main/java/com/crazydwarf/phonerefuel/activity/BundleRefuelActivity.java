package com.crazydwarf.phonerefuel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.crazydwarf.phonerefuel.R;
import com.crazydwarf.phonerefuel.dialog.AddNumberDialog;
import com.crazydwarf.phonerefuel.dialog.DefaultFlowDialog;
import com.crazydwarf.phonerefuel.dialog.DefaultRefuelDialog;
import com.crazydwarf.phonerefuel.view.DialogListItem;
import com.crazydwarf.phonerefuel.view.SimpleBottomSheetDialog;
import com.crazydwarf.phonerefuel.view.SimpleToolBar;

import java.util.ArrayList;
import java.util.List;

public class BundleRefuelActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bundle_refuel);
        SimpleToolBar toolBar = findViewById(R.id.toolbar_top);
        setSupportActionBar(toolBar);

        toolBar.setBackIconClickListener(new SimpleToolBar.BackIconClickListener() {
            @Override
            public void OnClick() {
                Intent intent = new Intent(BundleRefuelActivity.this,MainActivity.class);
                intent.putExtra("LOGIN",true);
                startActivity(intent);
                finish();
            }
        });

        toolBar.setMenuIconClickListener(new SimpleToolBar.MenuIconClickListener() {
            @Override
            public void OnClick(View view) {
                SimpleBottomSheetDialog simpleBottomSheetDialog = new SimpleBottomSheetDialog(BundleRefuelActivity.this);
                final List<DialogListItem> items = new ArrayList<DialogListItem>();
                items.add(new DialogListItem("添加充值手机号"));
                items.add(new DialogListItem("从通讯录批量选择"));
                items.add(new DialogListItem("缺省充值金额编辑"));
                items.add(new DialogListItem("缺省购买流量编辑"));
                items.add(new DialogListItem("全部充值"));
                simpleBottomSheetDialog.addItems(items);
                simpleBottomSheetDialog.setItemClick(new SimpleBottomSheetDialog.OnItemClickListener() {
                    @Override
                    public void click(DialogListItem item) {

                        int pos = items.indexOf(item);
                        switch (pos)
                        {
                            case 0:
                            {
                                AddNumberDialog dialog = new AddNumberDialog(BundleRefuelActivity.this);
                                dialog.show();
                                break;
                            }
                            case 1:
                            {
                                Toast.makeText(getApplicationContext(), "打开通讯录批量选择手机号", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            case 2:
                            {
                                DefaultRefuelDialog dialog = new DefaultRefuelDialog(BundleRefuelActivity.this);
                                dialog.show();
                                break;
                            }
                            case 3:
                            {
                                DefaultFlowDialog dialog = new DefaultFlowDialog(BundleRefuelActivity.this);
                                dialog.show();
                                break;
                            }
                            case 4:
                            {
                                Toast.makeText(getApplicationContext(), "进入充值确认流程", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            default:
                                break;
                        }
                    }
                });
                simpleBottomSheetDialog.show();
            }
        });
    }
}
