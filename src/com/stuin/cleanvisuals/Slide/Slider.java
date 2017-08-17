package com.stuin.cleanvisuals.Slide;

import android.view.View;
import android.view.animation.TranslateAnimation;

/**
 * Created by Stuart on 3/16/2017.
 */
public class Slider {
    private TranslateAnimation enterAnimation;
    private TranslateAnimation exitAnimation;
    private View view;

    public boolean unSet = true;
    public boolean moving = false;
    public Endings endings;

    public Slider(View view) {
        this.view = view;
    }

    public void setup(boolean side, int start, int duration) {
        unSet = false;

        //Set enter animation
        if(side) enterAnimation = new TranslateAnimation(start, 0, 0, 0);
        else enterAnimation = new TranslateAnimation(0, 0, start, 0);
        enterAnimation.setDuration(duration);

        //Set exit animation
        if(side) exitAnimation = new TranslateAnimation(0, start, 0, 0);
        else exitAnimation = new TranslateAnimation(0, 0, 0, start);
        exitAnimation.setDuration(duration);
    }

    public boolean enter() {
        if(!shown()) {
            //Bring view on screen
            moving = true;
            view.setVisibility(View.VISIBLE);
            view.startAnimation(enterAnimation);

            //Wait till finished
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    moving = false;
                    if(endings != null) endings.enter();
                }
            }, enterAnimation.getDuration());
            return true;
        }
        return false;
    }

    public boolean exit() {
        if(shown()) {
            //Send view off screen
            moving = true;
            view.startAnimation(exitAnimation);

            //Wait till finished
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    moving = false;
                    view.setVisibility(View.GONE);
                    if(endings != null) endings.exit();
                }
            }, exitAnimation.getDuration());
            return true;
        }
        return false;
    }

    public boolean shown() {
        //Detect if shown
        return view.getVisibility() == View.VISIBLE;
    }
}
