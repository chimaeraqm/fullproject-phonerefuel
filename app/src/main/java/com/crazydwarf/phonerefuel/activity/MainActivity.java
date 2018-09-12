package com.crazydwarf.phonerefuel.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.crazydwarf.phonerefuel.R;
import com.crazydwarf.phonerefuel.view.DialogListItem;
import com.crazydwarf.phonerefuel.view.SimpleBottomSheetDialog;
import com.crazydwarf.phonerefuel.view.SimpleToolBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleToolBar toolBar = findViewById(R.id.toolbar_top);
        setSupportActionBar(toolBar);
        toolBar.setBackIconClickListener(new SimpleToolBar.BackIconClickListener() {
            @Override
            public void OnClick() {
                activityExit();
            }
        });


        toolBar.setMenuIconClickListener(new SimpleToolBar.MenuIconClickListener() {
            @Override
            public void OnClick(View view) {
                SimpleBottomSheetDialog simpleBottomSheetDialog = new SimpleBottomSheetDialog(MainActivity.this);
                final List<DialogListItem> items = new ArrayList<DialogListItem>();
                items.add(new DialogListItem("当前手机号余额查询"));
                items.add(new DialogListItem("汇率刷新"));
                items.add(new DialogListItem("用户注册"));
                items.add(new DialogListItem("用户登录"));
                simpleBottomSheetDialog.addItems(items);
                simpleBottomSheetDialog.show();
                simpleBottomSheetDialog.setItemClick(new SimpleBottomSheetDialog.OnItemClickListener() {
                    @Override
                    public void click(DialogListItem item) {

                        int pos = items.indexOf(item);
                        switch (pos)
                        {
                            case 2:
                            {
                                finish();
                                Intent intent = new Intent(MainActivity.this,LogUpActivity.class);
                                startActivity(intent);
                                break;
                            }
                            case 3:
                            {
                                finish();
                                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                                startActivity(intent);
                                break;
                            }
                            default:
                                break;
                        }
                    }
                });
            }
        });
    }
    private long exitTime = 0;

    private void activityExit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            //TODO : 只用finish无法完全退出，会返回launcher页面
            finish();
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
        }
    }
}
