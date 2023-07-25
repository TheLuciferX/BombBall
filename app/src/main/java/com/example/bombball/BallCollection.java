package com.example.bombball;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

public class BallCollection {
    ArrayList<Ball> balls;
    private float minR;
    private float wScreen, hScreen;
    public BallCollection(float wScreen,float hScreen) {
        this.hScreen=hScreen;
        this.wScreen=wScreen;
        balls=new ArrayList<>();
        add();
        minR=wScreen/40;
    }
    public void add() {
            Ball ball = new Ball( wScreen, hScreen, null);
            balls.add(ball);
            float x = 150;
            float y = hScreen/2;
            ball.x = x;
            ball.y = y;


    }
    public void draw(Canvas canvas)
    {
        for(Ball b: balls) {
            b.draw(canvas);
        }
    }
    public void add(Ball b) {
        balls.add(b);
    }
    public void remove(Ball b) {
        balls.remove(b);
    }


    public void move(float Ly)
    {
        for(Ball b : balls)
        {
            b.move(wScreen,Ly);
        }
    }
    public Ball intersect(Sprite s)
    {
        for(int i=0;i<balls.size();i++)
        {
           Ball b = balls.get(i);
           float cx = 0;
           float cy = 0;
           if(s.x > b.x) {
               cx = s.x;
           } else if(s.x + s.w < b.x) {
               cx = s.x + s.w;
           } else {
               cx = b.x;
           }

            if(s.y > b.y) {
                cy = s.y;
            } else if(s.y + s.h < b.y) {
                cy = s.y + s.h;
            } else {
                cy = b.y;
            }
            float distX = b.x - cx;
            float distY = b.y - cy;
            if(distX*distX + distY*distY < b.getR()*b.getR()) {
                b.setIndex(b.getIndex()-1);
                return b;
            }
        }
        return null;
    }
    public boolean indexEmpty(Ball b)
    {
        return (b.getIndex()<=0);
    }

    public float getMinR() {
        return minR;
    }
    public int size()
    {
        int num=0;
        for(Ball b : balls)
        {
            num++;
        }
        return num;
    }
    public void freeze()
    {
        for(Ball b : balls)
        {
            b.freeze();
        }
    }
}
