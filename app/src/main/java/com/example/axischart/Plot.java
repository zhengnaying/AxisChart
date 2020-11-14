package com.example.axischart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Plot {
    //定义方程图形的表达式、自变量和自变量范围，例如绘制sin(x)曲线，自变量是x，绘制的范围为[-10,10]
    private String m_strExp;//表达式，例如sin(x)
    private String m_strvar;//表达式中的自变量，例如x
    private int m_nMinX;//自变量最小值
    private int m_nMaxX;//自变量最大值

    private int m_cLine;//图形颜色

    private Axis mAxis;//坐标轴，曲线在此坐标轴下进行绘制
    public Plot(Axis mAxis, String strExp, String strvar) {
         this.mAxis=mAxis;
         m_strExp = strExp;
         m_strvar = strvar;
         m_nMinX = -21;
         m_nMaxX = 21;
         m_cLine = Color.DKGRAY;
    }
    public void draw(Canvas canvas)
    {
        if(mAxis==null)
            return;

        //画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(m_cLine);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);

        ExpressionWithVars e = new ExpressionWithVars(m_strExp, m_strvar);
        double oldx= m_nMinX;
        double oldy= e.evalf(m_nMinX);

        double delta = ((double)(m_nMaxX - m_nMinX)) / 100;

        //绘制方程图形，将图形分为100点，依次连接起来
        for (int i = 1; i < 100; i++)
        {
            double newx = (m_nMinX + delta * i);
            double newy = e.evalf(newx);
            canvas.drawLine(mAxis.convertXLP2DP(oldx),mAxis.convertYLP2DP(oldy),mAxis.convertXLP2DP(newx),mAxis.convertYLP2DP(newy),paint);
            oldx=newx;
            oldy=newy;
        }
    }

}
