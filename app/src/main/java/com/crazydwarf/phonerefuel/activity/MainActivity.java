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
//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        SimpleToolBar toolBar = findViewById(R.id.toolbar_top);
        setSupportActionBar(toolBar);
        toolBar.setBackIconClickListener(new SimpleToolBar.BackIconClickListener() {
            @Override
            public void OnClick() {
                activityExit();
            }
        });

        Intent intent = getIntent();
        final boolean login_status = intent.getBooleanExtra("LOGIN",false);
        toolBar.setMenuIconClickListener(new SimpleToolBar.MenuIconClickListener() {
            @Override
            public void OnClick(View view) {
                SimpleBottomSheetDialog simpleBottomSheetDialog = new SimpleBottomSheetDialog(MainActivity.this);
                final List<DialogListItem> items = new ArrayList<DialogListItem>();
                if(login_status == true)
                {
                    items.add(new DialogListItem("当前手机号余额查询"));
                    items.add(new DialogListItem("汇率刷新"));
                    items.add(new DialogListItem("充值历史查询"));
                    items.add(new DialogListItem("批量充值"));
                    items.add(new DialogListItem("注销当前用户"));
                    simpleBottomSheetDialog.addItems(items);
                    simpleBottomSheetDialog.setItemClick(new SimpleBottomSheetDialog.OnItemClickListener() {
                        @Override
                        public void click(DialogListItem item) {

                            int pos = items.indexOf(item);
                            switch (pos)
                            {
                                case 0:
                                {
                                    //TODO : 需要加入activity的基类baseactivity控制activity的生命周期
                                    Intent intent = new Intent(MainActivity.this,BalanceCheckActivity.class);
                                    startActivity(intent);
                                    finish();
                                    break;
                                }
                                case 2:
                                {
                                    Intent intent = new Intent(MainActivity.this,RefuelHistory.class);
                                    startActivity(intent);
                                    finish();
                                    break;
                                }
                                case 3:
                                {
                                    Intent intent = new Intent(MainActivity.this,BundleRefuelActivity.class);
                                    startActivity(intent);
                                    finish();
                                    break;
                                }
                                case 4:
                                {
                                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                    break;
                                }
                                default:
                                    break;
                            }
                        }
                    });
                }
                else
                {
                    items.add(new DialogListItem("当前手机号余额查询"));
                    items.add(new DialogListItem("汇率刷新"));
                    items.add(new DialogListItem("用户注册"));
                    items.add(new DialogListItem("用户登录"));
                    simpleBottomSheetDialog.addItems(items);
                    simpleBottomSheetDialog.setItemClick(new SimpleBottomSheetDialog.OnItemClickListener() {
                        @Override
                        public void click(DialogListItem item) {

                            int pos = items.indexOf(item);
                            switch (pos)
                            {
                                case 0:
                                {
                                    //TODO : 需要加入activity的基类baseactivity控制activity的生命周期
                                    Intent intent = new Intent(MainActivity.this,BalanceCheckActivity.class);
                                    startActivity(intent);
                                    finish();
                                    break;
                                }
                                case 2:
                                {
                                    Intent intent = new Intent(MainActivity.this,LogUpActivity.class);
                                    startActivity(intent);
                                    finish();
                                    break;
                                }
                                case 3:
                                {
                                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                    break;
                                }
                                default:
                                    break;
                            }
                        }
                    });
                }
                simpleBottomSheetDialog.show();
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
