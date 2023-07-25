package com.example.bombball;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

public class SurpriseCollection {
    ArrayList<Surprise> sp;
    private float wScreen, hScreen;
    public SurpriseCollection(float width,float height)
    {
        this.wScreen=width;
        this.hScreen=height;
        sp = new ArrayList<>();
    }
    public void add (Ball ball)
    {
        Surprise surprise = new Surprise( wScreen, hScreen, null);
       sp.add(surprise);
    }
    public Surprise get(int index) {
        return sp.get(index);
    }
    public boolean remove(Sprite sprite) {
        for(Surprise s : sp)
        {
            if( s.intersect(sprite))
            {
                sp.remove(s);
                return true;
            }
        }
        return false;
    }

    public void move(float Ly,float Lh)
    {
        for(Surprise s : sp)
        {
            s.move( Ly, Lh);
        }
    }

    public void draw(Canvas canvas)
    {
        for(Surprise s : sp)
        {
            s.draw(canvas);

        }
    }


    public int size()
    {
        int index=0;
        for(Surprise s:sp)
        {
            index++;
        }
        return index;
    }
    public void setX(float x)
    {
        for(Surprise s : sp)
        {
            s.setX(x);
        }
    }
    public void setY(float y)
    {
        for(Surprise s : sp)
        {
            s.setY(y);
        }
    }

}
