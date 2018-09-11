package com.crazydwarf.phonerefuel.activity;

import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.crazydwarf.phonerefuel.R;
import com.crazydwarf.phonerefuel.view.BottomPopupDialog;
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
                Toast.makeText(MainActivity.this, "this is clicked BackIcon", Toast.LENGTH_LONG).show();
            }
        });


        toolBar.setMenuIconClickListener(new SimpleToolBar.MenuIconClickListener() {
            @Override
            public void OnClick(View view) {
                SimpleBottomSheetDialog simpleBottomSheetDialog = new SimpleBottomSheetDialog(MainActivity.this);
                List<DialogListItem> items = new ArrayList<DialogListItem>();
                items.add(new DialogListItem("当前手机号余额查询"));
                items.add(new DialogListItem("充值历史记录"));
                items.add(new DialogListItem("批量充值"));
                items.add(new DialogListItem("汇率刷新"));
                simpleBottomSheetDialog.addItems(items);
                simpleBottomSheetDialog.show();
                simpleBottomSheetDialog.setItemClick(new SimpleBottomSheetDialog.OnItemClickListener() {
                    @Override
                    public void click(DialogListItem item) {

                        String output = String.format(Locale.US,"this is clicked %s",item.title);
                        Toast.makeText(MainActivity.this, output, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    /**
     * Dim bg when popup window appears
     * @param bgcolor transparent percent of bg
     */
    private void darkenBackgroud(Float bgcolor) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgcolor;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
}
