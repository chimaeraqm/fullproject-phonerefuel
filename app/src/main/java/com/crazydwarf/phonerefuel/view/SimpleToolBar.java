package com.crazydwarf.phonerefuel.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crazydwarf.phonerefuel.R;

public class SimpleToolBar extends Toolbar
{
    private Context mContext;
    private ImageView mImageView_AppIcon;
    private ImageView mImageView_MenuIcon;
    private ImageView mImageView_BackIcon;
    private TextView mTextView_Title;

    /**
     * related setting params
     */
    private String mTitle;
    private int mTitleSize;
    private int mTitleColor;

    private Drawable mAppIcon;
    private boolean mAppIconShow;
    private int mAppIconSize;
    private AppIconClickListener appIconClickListener;

    private Drawable mBackIcon;
    private int mBackIconSize;
    private BackIconClickListener backIconClickListener;

    private Drawable mMenuIcon;
    private int mMenuIconSize;
    private MenuIconClickListener menuIconClickListener;

    private boolean mBackgroundBlur;
    private float mBackgroundBlurRatio;

    public SimpleToolBar(Context context) {
        this(context,null);
    }

    public SimpleToolBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SimpleToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleToolBar);
        mTitle = typedArray.getString(R.styleable.SimpleToolBar_title);
        mTitleSize = typedArray.getDimensionPixelSize(R.styleable.SimpleToolBar_titleSize,0);
        mTitleColor = typedArray.getColor(R.styleable.SimpleToolBar_titleColor, Color.WHITE);

        mAppIcon = typedArray.getDrawable(R.styleable.SimpleToolBar_appIcon);
        mAppIconShow = typedArray.getBoolean(R.styleable.SimpleToolBar_appIconShow,true);
        mAppIconSize = typedArray.getDimensionPixelSize(R.styleable.SimpleToolBar_appIconSize,20);

        mBackIcon = typedArray.getDrawable(R.styleable.SimpleToolBar_backIcon);
        mBackIconSize = typedArray.getDimensionPixelSize(R.styleable.SimpleToolBar_backIconSize,20);

        mMenuIcon = typedArray.getDrawable(R.styleable.SimpleToolBar_menuIcon);
        mMenuIconSize = typedArray.getDimensionPixelSize(R.styleable.SimpleToolBar_mentIconSize,20);

        mBackgroundBlur = typedArray.getBoolean(R.styleable.SimpleToolBar_backgroundBlur,false);
        mBackgroundBlurRatio = typedArray.getFloat(R.styleable.SimpleToolBar_backgroundBlurRatio,0f);
        initView();
        typedArray.recycle();
    }

    private void initView()
    {
        LayoutInflater.from(mContext).inflate(R.layout.simpletoolbar,this,true);
        mTextView_Title = findViewById(R.id.tv_title);
        mTextView_Title.setText(mTitle);
        mTextView_Title.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX ,mTitleSize);
        mTextView_Title.setTextColor(mTitleColor);

        mImageView_AppIcon = findViewById(R.id.im_icon);
        if(mAppIconShow)
        {
            mImageView_AppIcon.setBackground(mAppIcon);
            ViewGroup.LayoutParams layoutParams = mImageView_AppIcon.getLayoutParams();
            layoutParams.height = mAppIconSize;
            layoutParams.width = mAppIconSize;
            mImageView_AppIcon.setLayoutParams(layoutParams);
            mImageView_AppIcon.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    appIconClickListener.OnClick();
                }
            });
        }

        mImageView_BackIcon = findViewById(R.id.im_back);
        mImageView_BackIcon.setBackground(mBackIcon);
        ViewGroup.LayoutParams backicon_layoutParams = mImageView_BackIcon.getLayoutParams();
        backicon_layoutParams.height = mBackIconSize;
        backicon_layoutParams.width = mBackIconSize;
        mImageView_BackIcon.setLayoutParams(backicon_layoutParams);
        mImageView_BackIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                backIconClickListener.OnClick();
            }
        });

        mImageView_MenuIcon = findViewById(R.id.im_menu);
        mImageView_MenuIcon.setBackground(mMenuIcon);
        ViewGroup.LayoutParams menuicon_layoutParams = mImageView_MenuIcon.getLayoutParams();
        menuicon_layoutParams.height = mMenuIconSize;
        menuicon_layoutParams.width = mMenuIconSize;
        mImageView_MenuIcon.setLayoutParams(menuicon_layoutParams);
        mImageView_MenuIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                menuIconClickListener.OnClick(view);
            }
        });
    }

    public void setmTextView_Title(TextView mTextView_Title) {
        this.mTextView_Title = mTextView_Title;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmTitleSize(int mTitleSize) {
        this.mTitleSize = mTitleSize;
    }

    public void setmTitleColor(int mTitleColor) {
        this.mTitleColor = mTitleColor;
    }

    public void setmAppIcon(Drawable mAppIcon) {
        this.mAppIcon = mAppIcon;
    }

    public void setmAppIconShow(boolean mAppIconShow) {
        this.mAppIconShow = mAppIconShow;
    }

    public void setmAppIconSize(int mAppIconSize) {
        this.mAppIconSize = mAppIconSize;
    }

    public void setmBackIcon(Drawable mBackIcon) {
        this.mBackIcon = mBackIcon;
    }

    public void setmBackIconSize(int mBackIconSize) {
        this.mBackIconSize = mBackIconSize;
    }

    public void setmMenuIcon(Drawable mMenuIcon) {
        this.mMenuIcon = mMenuIcon;
    }

    public void setmMenuIconSize(int mMenuIconSize) {
        this.mMenuIconSize = mMenuIconSize;
    }

    public void setmBackgroundBlur(boolean mBackgroundBlur) {
        this.mBackgroundBlur = mBackgroundBlur;
    }

    public void setmBackgroundBlurRatio(float mBackgroundBlurRatio) {
        this.mBackgroundBlurRatio = mBackgroundBlurRatio;
    }

    /**
     * click listener interface for appicon, menuicon, backicon respectivly
     */
    public void setBackIconClickListener(BackIconClickListener backIconClickListener) {
        this.backIconClickListener = backIconClickListener;
    }

    public void setAppIconClickListener(AppIconClickListener appIconClickListener) {
        this.appIconClickListener = appIconClickListener;
    }

    public void setMenuIconClickListener(MenuIconClickListener menuIconClickListener) {
        this.menuIconClickListener = menuIconClickListener;
    }

    public interface BackIconClickListener{
        void OnClick();
    }

    public interface MenuIconClickListener{
        void OnClick(View view);
    }

    public interface AppIconClickListener{
        void OnClick();
    }
}