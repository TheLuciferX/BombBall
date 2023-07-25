package com.example.bombball;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class Sprite {
    protected float x ,y; // upper left corner
    protected float w,h; // size
    protected float dx, dy; // step size
    protected boolean inMove;
    protected Bitmap pic;
    public Sprite(float x, float y, float w, float h, Bitmap pic) {
        this.x = x;
        this.y = y;
        this.w = w-45;
        this.h = h-45;
        this.pic = pic;
        if ( pic != null)
            this.pic = Bitmap.createScaledBitmap(pic, (int)w,(int)h,true);
    }
    public void draw(Canvas canvas)
    {
        Paint p = new Paint();
        if ( pic == null) // no picture
            canvas.drawRect(x, y, x+w, y + h, p);
        else
        {
            canvas.drawBitmap(pic, x,y, p);
        }

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void move()
    {
            x += dx;
            y += dy;
    }
    public boolean intersect(Sprite s)
    {
        float x1=this.x;
        float x2=x1+this.w;
        float x3=s.getX();
        float x4=x3+s.w;
        float y1=this.y;
        float y2=y1+this.h;
        float y3=s.getY();
        float y4=y3+s.h;
        if(x1<=x4&&x3<=x2&&y1<=y4&&y3<=y2)
        {
            return true;
        }
        return false;
    }
    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public float getH() {
        return h;
    }
}
