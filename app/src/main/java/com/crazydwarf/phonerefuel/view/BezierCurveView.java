package com.crazydwarf.phonerefuel.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.crazydwarf.phonerefuel.UserUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BezierCurveView extends View
{
    private Context mContext;
    /**
     * @param mPoints 保存控制点
     * @param drawPoints 所需绘制的点
     *
     */
    private List<PointF> mPoints = new ArrayList<PointF>();
    private List<PointF> drawPoints = new ArrayList<PointF>();

    //t:曲线绘制的控制节点
    private float t = 0;

    //根据杨辉三角设置的曲线计算常数
    private int[] constValue = null;

    //bezier曲线的阶数
    private int level;

    /**
     * 界面上绘制的图形包括
     * 1 控制点连线 mControlLinePaint
     * 2 控制点 mControlPointPaint
     * 3 bezier曲线 mBezierPaint
     * 4 bezier曲线端点 mBezierPaint
     * 4 文字 mTextPaint
     * 5 辅助线
     * 6 辅助线端点（与控制点连线的交点）
     */
    //绘制各线的paint和path
    private Paint mControlLinePaint;
    private Paint mControlPointPaint;

    private Paint mBezierPaint;
    private Paint mBezierPointPaint;

    private Paint mTextPaint;

    private List<Paint> adsLinePaints = new ArrayList<Paint>();
    private List<Paint> adsPointPaints = new ArrayList<Paint>();

    private Path mControlPath;
    private Path mBezierPath;

    public BezierCurveView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public BezierCurveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public BezierCurveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    void initView()
    {
        mControlLinePaint = new Paint();
        mControlLinePaint.setColor(Color.GRAY);
        mControlLinePaint.setStrokeWidth(8);
        mControlLinePaint.setStyle(Paint.Style.STROKE);
        mControlLinePaint.setAntiAlias(true);
        mControlLinePaint.setStrokeCap(Paint.Cap.ROUND);

        mControlPointPaint  = new Paint();
        mControlPointPaint.setColor(Color.BLACK);
        mControlPointPaint.setStrokeWidth(4);
        mControlPointPaint.setStyle(Paint.Style.STROKE);
        mControlPointPaint.setAntiAlias(true);
        mControlPointPaint.setStrokeCap(Paint.Cap.ROUND);

        mBezierPaint = new Paint();
        mBezierPaint.setColor(Color.RED);
        mBezierPaint.setStrokeWidth(8);
        mBezierPaint.setStyle(Paint.Style.STROKE);
        mBezierPaint.setAntiAlias(true);
        mBezierPaint.setStrokeCap(Paint.Cap.ROUND);

        mBezierPointPaint  = new Paint();
        mBezierPointPaint.setColor(Color.BLACK);
        mBezierPointPaint.setStrokeWidth(10);
        mBezierPointPaint.setStyle(Paint.Style.FILL);
        mBezierPointPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        int textStrokeWidth = UserUtil.px2sp(mContext,10);
        mTextPaint.setTextSize(40);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStrokeWidth(textStrokeWidth);
        mTextPaint.setStyle(Paint.Style.FILL);

        Paint adsLinePaint1 = new Paint();
        for(int i=0;i<level-1;i++)
        {
            adsLinePaint1.setColor(Color.BLUE);
            adsLinePaint1.setStrokeWidth(8);
            adsLinePaint1.setStyle(Paint.Style.STROKE);
            adsLinePaint1.setAntiAlias(true);
            adsLinePaint1.setStrokeCap(Paint.Cap.ROUND);
            adsLinePaints.add(adsLinePaint1);
            Paint adsPointPaint1 = new Paint();

            adsPointPaint1.setColor(Color.BLUE);
            adsPointPaint1.setStrokeWidth(10);
            adsPointPaint1.setStyle(Paint.Style.FILL);
            adsPointPaint1.setAntiAlias(true);
            adsPointPaints.add(adsPointPaint1);
        }

        mControlPath = new Path();
        mBezierPath = new Path();

        mPoints.add(new PointF(0,0));
        mPoints.add(new PointF(0,0));
        mPoints.add(new PointF(0,0));
        mPoints.add(new PointF(0,0));

        constValue = null;
        if(mPoints.size() == 3)
        {
            constValue = new int[]{1,2,1};
        }
        else if(mPoints.size() == 4)
        {
            constValue = new int[]{1,3,3,1};
        }
        //将绘制过程细分为100，绘制时间5s;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valueAnimator.setDuration(10 * 1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                t = (float) (animation.getAnimatedValue());
                float finalX = 0;
                float finalY = 0;
                //level 临时计算bezier曲线的阶数
                level = mPoints.size()-1;
                for(int i=0;i<mPoints.size();i++)
                {
                    PointF pointi = mPoints.get(i);
                    float pointix = pointi.x;
                    float pointiy = pointi.y;
                    int param = constValue[i];
                    double x = param * pointix * Math.pow(1-t,level-i) * Math.pow(t,i);
                    double y = param * pointiy * Math.pow(1-t,level-i) * Math.pow(t,i);
                    finalX += x;
                    finalY += y;
                }
                PointF nextPoint = new PointF(finalX,finalY);
                drawPoints.add(nextPoint);
                invalidate();
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //mControlPointPaint绘制控制点，mTextPaint绘制控制点文字
        for(PointF pointi : mPoints)
        {
            canvas.drawPoint(pointi.x,pointi.y,mControlPointPaint);
            int pos = mPoints.indexOf(pointi);
            String pointText = String.format(Locale.US,"P%d",pos);
            canvas.drawText(pointText,pointi.x,pointi.y,mTextPaint);
        }

        //mControlLinePaint沿mControlPath绘制辅助线
        mControlPath.reset();
        PointF pointHead = mPoints.get(0);
        mControlPath.moveTo(pointHead.x,pointHead.y);
        for(int i=1;i<mPoints.size();i++)
        {
            PointF pointi = mPoints.get(i);
            mControlPath.lineTo(pointi.x,pointi.y);
        }
        canvas.drawPath(mControlPath,mControlLinePaint);

        //adsLinePaints绘制辅助线，adsPointPaints绘制辅助线端点
//        for(int i=0;i<level;i++)
//        {
//
//        }
//        List<PointF> adsPoints = new ArrayList<PointF>();
//        Paint adsPointPaint1 = adsPointPaints.get(0);
//        for(int i=1;i<mPoints.size();i++)
//        {
//            PointF point0 = mPoints.get(i-1);
//            PointF pointi = mPoints.get(i);
//            PointF process = new PointF();
//            process.x = (1 - t) * point0.x + t * pointi.x;
//            process.y = (1 - t) * point0.y + t * pointi.y;
//            adsPoints.add(process);
//            canvas.drawPoint(process.x,process.y,adsPointPaint1);
//        }
//        Paint adsLinePaint1 = adsLinePaints.get(0);
//        canvas.drawLine(adsPoints.get(0).x,adsPoints.get(0).y,adsPoints.get(1).x,adsPoints.get(1).y,adsLinePaint1);

        //mBezierPaint沿mBezierPath绘制bezier曲线,mBezierPointPaint绘制bezier曲线尾点
        mBezierPath.reset();
        if(drawPoints.size() > 1)
        {
            mBezierPath.moveTo(pointHead.x,pointHead.y);
            for(int i=1;i<drawPoints.size();i++)
            {
                PointF pointi = drawPoints.get(i);
                mBezierPath.lineTo(pointi.x,pointi.y);
            }
            canvas.drawPath(mBezierPath,mBezierPaint);
            PointF tailPoint = drawPoints.get(drawPoints.size()-1);
            canvas.drawPoint(tailPoint.x,tailPoint.y,mBezierPointPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
//        switch (event.getAction())
//        {
//            case MotionEvent.ACTION_DOWN:
//            case MotionEvent.ACTION_MOVE:
//                if(mPoints.size() == 3)
//                {
//                    PointF point1 = mPoints.get(1);
//                    point1.x = event.getX();
//                    point1.y = event.getY();
//                    invalidate();
//                }
//                break;
//                default:
//                    break;
//        }
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float centerX = w/2;
        float centerY = h/2;
        //初始化各点位置
        if(mPoints.size() == 4)
        {
            mPoints.get(0).x = centerX-500;
            mPoints.get(0).y = centerY;
            mPoints.get(1).x = centerX-250;
            mPoints.get(1).y = centerY-500;
            mPoints.get(2).x = centerX+250;
            mPoints.get(2).y = centerY-500;
            mPoints.get(3).x = centerX+500;
            mPoints.get(3).y = centerY;
            drawPoints.clear();
            drawPoints.add(mPoints.get(0));
        }
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
