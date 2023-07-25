package com.example.bombball;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.Random;

public class Ball extends Sprite {
    private float wScreen, hScreen;
    private float r;
    private int color;
    private int index;
    private int maxIndex;
    private static Random rnd = new Random();
    private float g ;
    private float force;
    private boolean inMove;
    private final String[] colours = new String[]{"#5705f0", "#ff0015", "#cafc14", "#05ff61", "#003cff", "#00ffe5", "#870714", "#ff00d4","#FA8B0E"};
    public Ball(float ws, float hs, Bitmap pic) {
        super(0, 0, 0, 0, pic);
        this.wScreen = ws;
        this.hScreen = hs;
        this.r= ws/8;
        dx=3;
        dy=0;
        g = 0.8f;
        force =-38;
        inMove=true;
        index=40;
        maxIndex=index;
        String colour = colours[rnd.nextInt(colours.length)];
        color = Color.parseColor(colour);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint p=new Paint();
        p.setColor(color);
        canvas.drawCircle(x,y,r,p);
        Paint p2=new Paint();
        p2.setColor(Color.BLACK);
        p2.setTextSize(50);
        p2.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(index+"",this.x,this.y+25,p2);
    }
    public float getR() {
        return r;
    }

    public void move(float wScreen,float Ly) {
        if(inMove)
        {
            super.move();
            dy += g;
            if ( x +r > wScreen || x-r < 0) {
                if (x - r < 0)
                    x = r;
                else
                    x = wScreen - r;
                setDx(-dx);
            }
            if ( y+r >= Ly) {
                y = Ly - r;
                setDy(force);
            }
            if(y-r <= 0)
                setDy(-dy);
        }

    }
    public void split(BallCollection bc)
    {
        float r=this.r;
        float ballX=this.x;
        float ballY=this.y;
        Ball a=new Ball(wScreen,hScreen,null);
        Ball b=new Ball(wScreen,hScreen,null);
        if(!inMove)
        {
            a.freeze();
            b.freeze();
        }
        a.r=r/1.5f;
        b.r=r/1.5f;
        a.x=ballX-r;
        b.x=ballX+r;
        a.y=ballY;
        b.y=ballY;
        a.index = ((int)Math.ceil(maxIndex/2.0));
        b.index = ((int)Math.floor(maxIndex/2.0));
        a.maxIndex = a.index;
        b.maxIndex = b.index;
        if(this.dx>0)
        {
            a.setDx(-dx);
        }
         else
        {
            a.setDx(this.dx);
        }
          if(this.dx<0)
         {
            b.setDx(-dx);
         }
              else {
            b.setDx(this.dx);
        }
        bc.add(a);
        bc.add(b);

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    public void freeze()
    {
        if(inMove)
            inMove=false;
        else
            inMove=true;
    }

}
