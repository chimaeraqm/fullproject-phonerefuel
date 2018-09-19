package com.crazydwarf.phonerefuel.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.crazydwarf.phonerefuel.R;
import com.crazydwarf.phonerefuel.fragment.BezierCurveFragment;
import com.crazydwarf.phonerefuel.view.BezierCurveView;
import com.crazydwarf.phonerefuel.view.CommonAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class RecycleviewTestActivity extends AppCompatActivity
{
    private List<Fragment> fragments = new ArrayList<>();
    private BezierCurveFragment mBezierFragment2;
    private BezierCurveFragment mBezierFragment3;
    private BezierCurveFragment mBezierFragment4;
    private BezierCurveFragment mBezierFragment5;

    private DrawerLayout drawerLayout;

    private int lastfragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycleview_test);

        initFragments();

        Toolbar toolBar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle mDrawToggle = new ActionBarDrawerToggle(this,drawerLayout,toolBar,R.string.open,R.string.close)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawToggle.syncState();
        drawerLayout.addDrawerListener(mDrawToggle);

        RecyclerView mRecyclerview = findViewById(R.id.list_dl);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setHasFixedSize(true);

        String[] newTitles = {"BezierCurve(2)","BezierCurve(3)","BezierCurve(4)","BezierCurve(5)"};
        Integer[] ids = {R.drawable.ic_timeline_black_24dp,
                R.drawable.ic_timeline_black_24dp,
                R.drawable.ic_timeline_black_24dp,
                R.drawable.ic_timeline_black_24dp};
        CommonAdapter commonAdapter = new CommonAdapter(newTitles,ids);
        mRecyclerview.setAdapter(commonAdapter);

        commonAdapter.setOnCommonRVItemClickListener(new CommonAdapter.OnCommonRVItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switchFragment(position);
                drawerLayout.closeDrawers();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                switchFragment(position);
                drawerLayout.closeDrawers();
            }
        });


        //BezierCurveView im_drawable = findViewById(R.id.im_drawable_view);
    }

    void initFragments()
    {
        mBezierFragment2 = new BezierCurveFragment();

        mBezierFragment3 = new BezierCurveFragment();

        mBezierFragment4 = new BezierCurveFragment();

        mBezierFragment5 = new BezierCurveFragment();

        fragments.add(mBezierFragment2);
        fragments.add(mBezierFragment3);
        fragments.add(mBezierFragment4);
        fragments.add(mBezierFragment5);

        getSupportFragmentManager().beginTransaction().replace(R.id.drawer_content,mBezierFragment2).show(mBezierFragment2);
    }

    private void switchFragment(int index)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(fragments.get(lastfragment));
        if(!fragments.get(index).isAdded())
        {
            fragmentTransaction.add(R.id.drawer_content,fragments.get(index));
        }
        Fragment fragment = fragments.get(index);
        Bundle bundle = new Bundle();
        bundle.putInt("LEVEL",index+2);
        fragment.setArguments(bundle);

        fragmentTransaction.show(fragment).commitAllowingStateLoss();
    }
}
