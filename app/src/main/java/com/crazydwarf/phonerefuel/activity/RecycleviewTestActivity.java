package com.crazydwarf.phonerefuel.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.crazydwarf.phonerefuel.R;
import com.crazydwarf.phonerefuel.view.CommonAdapter;

public class RecycleviewTestActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycleview_test);
        RecyclerView mRecyclerview = findViewById(R.id.recycleview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setHasFixedSize(true);

        String[] newTitles = {"title1","title2","title3","title4"};
        Integer[] ids = {R.drawable.ic_chevron_left_black_32dp,
                R.drawable.ic_chevron_left_black_32dp,
                R.drawable.ic_chevron_left_black_32dp,
                R.drawable.ic_chevron_left_black_32dp};
        CommonAdapter commonAdapter = new CommonAdapter(newTitles,ids);
        RecyclerView recyclerView = findViewById(R.id.recycleview);
        recyclerView.setAdapter(commonAdapter);

        commonAdapter.setOnCommonRVItemClickListener(new CommonAdapter.OnCommonRVItemClickListener() {
            @Override
            public void onItemClick(View view) {
                Toast.makeText(RecycleviewTestActivity.this, "click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view) {
                Toast.makeText(RecycleviewTestActivity.this, "long click", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
