package com.crazydwarf.phonerefuel.fragment;

import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.crazydwarf.phonerefuel.R;
import com.crazydwarf.phonerefuel.view.BezierCurveView;

import java.util.ArrayList;
import java.util.List;

public class BezierCurveFragment extends Fragment
{
    private int level;
    private BezierCurveView mBezierCurveView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_bezier_curve,container,false);
        Bundle bundle = getArguments();
        level = bundle.getInt("LEVEL",2);
        //BezierCurveView mBezierCurveView = new BezierCurveView(getActivity(),level);
        mBezierCurveView = view.findViewById(R.id.bezierCurveView);
        mBezierCurveView.setLevel(level);
        mBezierCurveView.initView();

        final List<PointF> mPoints = mBezierCurveView.getmPoints();
        final int[] constValue = mBezierCurveView.getConstValue();

        Button bnSimu = view.findViewById(R.id.bn_simu);
        bnSimu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //将绘制过程细分为100，绘制时间5s;
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
                valueAnimator.setDuration(10 * 1000);
                valueAnimator.setInterpolator(new LinearInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        List<PointF> drawPoints = new ArrayList<PointF>();
                        float t = (float) (animation.getAnimatedValue());
                        float finalX = 0;
                        float finalY = 0;
                        //level 临时计算bezier曲线的阶数
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
                        mBezierCurveView.setT(t);
                        mBezierCurveView.setDrawPoints(drawPoints);
                    }
                });
                valueAnimator.start();
            }
        });
        return view;
    }
}
