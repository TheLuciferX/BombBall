package com.example.bombball;

import android.graphics.Bitmap;

public class Arrow extends Sprite {
    private float wScreen, hScreen;

    public Arrow(float ws, float hs, Bitmap pic) {
        super(0, 0, 0, 0, pic);
        this.wScreen = ws;
        this.hScreen = hs;
        w = ws/22;
        h = w;
        x = ws / 2 - w / 2;
        y = hs - h*2;

    }


    public void move()
    {
        this.y=y;
            y-=10;

    }
        public boolean ifTop()
        {
            this.y=y;
            if(y<=0)
                return true ;
            return false;
        }

}
