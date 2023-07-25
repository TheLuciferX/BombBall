package com.example.bombball;

import android.graphics.Bitmap;

public class Man extends Sprite {
    private float wScreen, hScreen;
    private float mx;
    public Man(float ws, float hs, Bitmap pic) {
        super(0, 0, 0, 0, pic);
        this.wScreen = ws;
        this.hScreen = hs;
        w = ws/24;
        h = w*3;
        x = ws / 2 - w / 2;
        y = hs - h*2;
        mx=0;
    }
    public void move(float x)
    {
        this.x=x;
        if(this.x<0)
            this.x=0;
        if(this.x>wScreen-w)
            this.x=wScreen-w;

    }
        public void move()
        {
            if(mx>this.x + this.w)
            {
                this.move(this.x+25);
            }

            if(mx<this.x)
            {
                this.move(this.x-25);
            }
        }
        public void setMx(float mx)
        {
            this.mx=mx;
        }
}
