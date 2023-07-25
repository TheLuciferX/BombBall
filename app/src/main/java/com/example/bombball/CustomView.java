package com.example.bombball;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;


public class CustomView extends View {
    boolean holding = false;
    Man man;
    Sprite line;
    SurpriseCollection surpriseCollection;
    BallCollection ballCollection;
    ArrowsCollection arrowsCollection;
    Context context;
    int frame=0;
    int frame2=0;
    int frameForFreeze;
    boolean freeze=false;
    boolean split=false;
    private float wScreen, hScreen;
    Canvas canvas;
    boolean started = false;
    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
            handler.removeCallbacksAndMessages(null);
            handler.postDelayed(runnable, 1000/60);
        }
    };

    public CustomView(Context context) {
        super(context);
        this.context = context;
    }
    public void update()
    {
        frame++;
        frame2++;
        if(freeze)
            frameForFreeze++;
        arrowsCollection.move();
        arrowsCollection.remove();
        intersect();
        intersect2();
        emptyBall();
        ballCollection.move(line.getY());

    }
    protected void onDraw(Canvas canvas) {
        update();
        line.draw(canvas);
        man.draw(canvas);

        if (frame2 >= 100)
        {
            frame2=0;
        }
        if(frameForFreeze>=300)
        {
            if(freeze)
            {
                ballCollection.freeze();
                freeze=false;
            }

            frameForFreeze=0;
        }
        if(holding) {
            if(frame>=5)
            {
              arrowsCollection.setIsSplit(split);
              arrowsCollection.add(man);
              frame=0;
            }
            man.move();
        }

       arrowsCollection.draw(canvas);
        ballCollection.draw(canvas);
        surpriseCollection.draw(canvas);
        if(surpriseCollection.remove(man))
        {
            surpriseCollection.remove(man);
            Random r = new Random();
            int random = r.nextInt(2);
            Log.d("asdasd", "this "+random);
            if(random==0)
            {
                frameForFreeze=0;
                freeze=true;
                ballCollection.freeze();
            }

            if(random==1)
            {
                Log.d("asdasdasd", "got here");
                split=true;
            }
        }
        surpriseCollection.move(line.getY(),line.getH());
        if(!started) {
            started = true;
            handler.post(runnable);
        }
    }
    public void intersect() //ball and arrow
    {
        for(int i=0;i<arrowsCollection.size();i++)
        {
            Arrow a = arrowsCollection.get(i);
            Ball b = ballCollection.intersect(a);
            if(b != null) {
                arrowsCollection.remove(a);
                if(ballCollection.indexEmpty(b))
                {
                    if(ballCollection.getMinR()<=b.getR()/2) {
                        b.split(ballCollection);
                        Random rnd = new Random();
                        int random = rnd.nextInt(3) + 1;
                        if (random ==2)
                        {
                            surpriseCollection.add(b);
                            surpriseCollection.setX(b.getX());
                            surpriseCollection.setY(b.getY());


                        }

                    }
                    ballCollection.remove(b);
                }
            }
        }
    }
    public void intersect2()//ball and man
    {
        for(int i=0;i<ballCollection.size();i++)
        {
            Ball b = ballCollection.intersect(man);
            if(b!=null)
            {
                onSizeChanged((int)wScreen,(int)hScreen,(int)wScreen,(int)hScreen);
            }
        }
    }
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        wScreen = w;
        hScreen = h;
        man = new Man(wScreen, hScreen, null);
        surpriseCollection=new SurpriseCollection(wScreen,hScreen);
        line=new Sprite(0,man.getY()+man.getH(),w*2,w/34,null);
        arrowsCollection=new ArrowsCollection(w,h);
        ballCollection=new BallCollection(w,h);
        }
    public boolean onTouchEvent(MotionEvent event) {
        float ex = event.getX();
        if(event.getAction() == MotionEvent.ACTION_DOWN) holding = true;
        else if(event.getAction() == MotionEvent.ACTION_UP) holding = false;
        man.setMx(ex);
        return true;

    }
    public void emptyBall()
    {
        if(ballCollection.size()==0)
        {
            onSizeChanged((int)wScreen,(int)hScreen,(int)wScreen,(int)hScreen);
        }


    }
}

