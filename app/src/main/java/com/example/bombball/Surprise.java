package com.example.bombball;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Surprise extends Sprite {
    private float wScreen, hScreen;
    private float dy;
    public Surprise(float ws, float hs, Bitmap pic) {
        super(0, 0, 0, 0, pic);
        this.wScreen = ws;
        this.hScreen = hs;
        w = ws / 17;
        h = w;
        x=0;
        y=0;
        dy=10;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint p=new Paint();
        p.setColor(Color.GRAY);
        canvas.drawRect(x, y, x+w, y + h, p);
        Paint p2=new Paint();
        p2.setColor(Color.BLACK);
        p2.setTextSize(30);
        p2.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("?",this.x+this.w/2,this.y+this.h/2+15,p2);
    }

    public void move(float Ly,float Lh) {
        if(!(this.y+this.h>=Ly+Lh))
        {
            this.y+=dy;
        }
    }

}
