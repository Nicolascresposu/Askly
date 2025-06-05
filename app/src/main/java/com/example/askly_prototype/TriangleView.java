package com.example.askly_prototype;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class TriangleView extends View {
    //Estos 3 se establecen desde MainActivity, para el añadirpunto.
    public String owner="Fer";
    public String target="Fer";
    public int playerColor=Color.RED;
    public int idPregunta;
    public int currentColor = Color.RED;
    private Paint trianglePaint;
    private Path trianglePath;
    private List<Pin> currentClickPoints;
    private List<Pin> clickPoints;
    private int main2 = Color.BLACK;
    public OnTapListener onTapListener; // Interfaz normal de java para avisar al MainActivity.kt

    public interface OnTapListener {
        void onTap();
    }
    public void setOnTapListener(OnTapListener listener) {
        this.onTapListener = listener;
    }


    public TriangleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        main2 = ContextCompat.getColor(getContext(), R.color.main2);
        trianglePaint = new Paint();
        trianglePaint.setColor(main2);
        trianglePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        trianglePaint.setStrokeWidth(4);

        trianglePath = new Path();
        clickPoints = new ArrayList<>();
        currentClickPoints = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Definir las 2 puntas (arriba y derecha) del triangulo, para dimensiones
        float width = getWidth();
        float height = getHeight();

        trianglePath.reset();
        trianglePath.moveTo(width / 2, 0);         // Top center
        trianglePath.lineTo(0, height);             // Bottom left
        trianglePath.lineTo(width, height);         // Bottom right
        trianglePath.close();                       // Close the triangle

        canvas.drawPath(trianglePath, trianglePaint);

        // Dibuja los puntos
        Paint pointPaint = new Paint();

        //Aca se cambia cuando ya haya otros jugadores
        pointPaint.setStyle(Paint.Style.FILL);

        for (Pin point : currentClickPoints) {
            pointPaint.setColor(point.color);
            canvas.drawCircle(point.point.x, point.point.y, 10, pointPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (isInsideTriangle(x, y)) {
                if (currentClickPoints.size() == 9) {
                    clickPoints.addAll(currentClickPoints);
                    currentClickPoints.removeAll(clickPoints);
                }
                currentClickPoints.add(new Pin(owner,new PointF(x, y),target,idPregunta,playerColor)); //Esto se llena y actualiza con datos desde MainActiivty
                if (onTapListener != null) {
                    onTapListener.onTap(); // Avisar al listener
                }
                invalidate(); // Redibujar el triangulo
            }
        }
        return true;
    }

    private boolean isInsideTriangle(float x, float y) {
        float width = getWidth();
        float height = getHeight();

        PointF p0 = new PointF(width / 2, 0);
        PointF p1 = new PointF(0, height);
        PointF p2 = new PointF(width, height);

        return isPointInTriangle(new PointF(x, y), p0, p1, p2);
    }

    // Pa ver si un punto esta o no en el triangulo
    private boolean isPointInTriangle(PointF p, PointF a, PointF b, PointF c) {
        float as_x = p.x - a.x;
        float as_y = p.y - a.y;

        boolean s_ab = (b.x - a.x) * as_y - (b.y - a.y) * as_x > 0;

        if ((c.x - a.x) * as_y - (c.y - a.y) * as_x > 0 == s_ab) return false;
        if ((c.x - b.x) * (p.y - b.y) - (c.y - b.y) * (p.x - b.x) > 0 != s_ab) return false;

        return true;
    }

    public List<Pin> getCurrentClickPoints() {
        return currentClickPoints;
    }
}
