package com.example.askly_prototype;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class TriangleView extends View {
    // Existing variables
    public String owner="Fer";
    public String target="Fer";
    public int ownerColor=Color.RED;
    public int targetColor=Color.WHITE;
    public int idPregunta;
    public int currentColor = Color.RED;
    private Paint trianglePaint;
    private Path trianglePath;
    private List<Pin> currentClickPoints;
    private List<Pin> clickPoints;
    private int main2 = Color.BLACK;
    public OnTapListener onTapListener;

    // New variables for image handling
    private Bitmap pinBitmap;
    private Paint imagePaint;
    private int pinWidth = 80; // Adjust as needed
    private int pinHeight = 80; // Adjust as needed

    public interface OnTapListener {
        void onTap();
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

        // Load the pin image
        pinBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pin);
        // Scale the bitmap to desired size
        pinBitmap = Bitmap.createScaledBitmap(pinBitmap, pinWidth, pinHeight, true);

        // Initialize paint for image drawing with color filtering capability
        imagePaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw triangle (unchanged)
        float width = getWidth();
        float height = getHeight();

        trianglePath.reset();
        trianglePath.moveTo(width / 2, 0);
        trianglePath.lineTo(0, height);
        trianglePath.lineTo(width, height);
        trianglePath.close();
        canvas.drawPath(trianglePath, trianglePaint);

        // Dibuja los puntos
        Paint pointPaint = new Paint();

        //Aca se cambia cuando ya haya otros jugadores
        pointPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        pointPaint.setColor(Color.RED);
        pointPaint.setStrokeWidth(10);
        // Draw pins instead of circles
        for (Pin point : currentClickPoints) {
            // Apply color filter to the image
            ColorFilter filter = new LightingColorFilter(point.targetColor, point.targetColor);
            pointPaint.setColor(point.ownerColor);
            imagePaint.setColorFilter(filter);


            // Calculate position to center the image where the circle would be
            float left = point.point.x - (pinWidth / 2);
            float top = point.point.y - (pinHeight);

            canvas.drawBitmap(pinBitmap, left, top, imagePaint);
            canvas.drawCircle(point.point.x, point.point.y - 50, 10, pointPaint);
        }
    }

    // Rest of your code remains unchanged...
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
                currentClickPoints.add(new Pin(owner, new PointF(x, y), target, idPregunta, targetColor,ownerColor));
                if (onTapListener != null) {
                    onTapListener.onTap();
                }
                invalidate();
            }
        }
        return true;
    }

    // ... keep all other existing methods


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
    public void setOnTapListener(OnTapListener listener) {
        this.onTapListener = listener;
    }
}
