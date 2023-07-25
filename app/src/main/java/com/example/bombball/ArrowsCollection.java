package com.example.bombball;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

public class ArrowsCollection {
ArrayList<Arrow> ar;
boolean isSplit;
private float wScreen, hScreen;
    public ArrowsCollection(float width,float height)
    {
        this.wScreen=width;
        this.hScreen=height;
        ar = new ArrayList<>();
        isSplit=false;
    }
    public Arrow get(int index) {
        return ar.get(index);
    }
    public void remove(Arrow a) {
        ar.remove(a);
    }
    public void add(Man man)
    {
        if(!isSplit)
        {
            Arrow arrow = new Arrow(wScreen, hScreen, null);
            ar.add(arrow);
            arrow.x=man.x+(man.w/2);
            arrow.x-=arrow.w/2;
            arrow.y=man.y-50;
        }
        else
        {
            Arrow arrow = new Arrow(wScreen, hScreen, null);
            Arrow arrow2 = new Arrow(wScreen, hScreen, null);
            ar.add(arrow);
            ar.add(arrow2);
            arrow.x=man.x+(man.w/2)-30;
            arrow2.x=man.x+(man.w/2)+30;
            arrow.y=man.y-50;
            arrow2.y=man.y-50;
        }

    }
    public void move()
    {
            for(Arrow a : ar)
            {
                a.move();
            }
    }

    public void draw(Canvas canvas)
    {
            for(Arrow a : ar)
            {
                a.draw(canvas);

            }
    }

    public int size()
    {
        int num=0;
        for(Arrow a : ar)
        {
            num++;
        }
        return num;
    }
    public void remove()
    {
        for(int i=0;i<ar.size();i++)
        {
            if(ar.get(i).ifTop()==true)
                ar.remove(ar.get(i));
        }
    }

    public void setIsSplit(boolean isSplit) {
        this.isSplit = isSplit;
    }
}
