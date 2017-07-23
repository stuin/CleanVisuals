package com.stuin.cleanvisuals.Drift;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.stuin.cleanvisuals.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Created by Stuart on 7/13/2017.
 */
public class Plane extends RelativeLayout {
    private List<Object> objects = new ArrayList<>();
    private boolean unset = true;
    private int updateTime = 1;
    private int time = 0;

    Stack<Object> waiting = new Stack<>();
    Random rand;

    public boolean on = true;
    public boolean vertical;
    public int length;
    public int width;
    public int start;
    public Range speed;
    public int addTime;
    public Drawable drawable;
    public int objectLength;

    //Various constructors
    public Plane(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Plane(Context context) {
        super(context);
    }

    //Actual setup
    public void setup() {
        rand = new Random();
        unset = false;

        //Set dimensions
        if(vertical) {
            length = getHeight();
            width = getWidth();
        } else {
            length = getWidth();
            width = getHeight();
        }

        if(on) postDelayed(updateRunnable, updateTime);
    }

    private Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            update();
        }
    };

    public void update() {
        if(unset) setup();
        if(on) {
            //update all objects
            for(Object object : objects) object.update();
            if(time % addTime == 0) add();
            time++;
        } else if(waiting.size() < objects.size()) {
            //hide all objects
            for(Object object : objects) object.hide();
        }
        postDelayed(updateRunnable, updateTime);
    }

    public Object add() {
        Object object;
        if(waiting.isEmpty()) {
            object = new Object(this);
            addView(object);
            objects.add(object);
        } else {
            object = waiting.pop();
        }
        object.start();
        return object;
    }
}
