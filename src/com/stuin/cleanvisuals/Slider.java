package com.stuin.cleanvisuals;

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

    public void setup(boolean side, int start, int time) {
        unSet = false;

        if(side) enterAnimation = new TranslateAnimation(start, 0, 0, 0);
        else enterAnimation = new TranslateAnimation(0, 0, start, 0);
        enterAnimation.setDuration(time);

        if(side) exitAnimation = new TranslateAnimation(0, start, 0, 0);
        else exitAnimation = new TranslateAnimation(0, 0, 0, start);
        exitAnimation.setDuration(time);
    }

    public boolean enter() {
        if(!shown()) {
            moving = true;
            view.setVisibility(View.VISIBLE);
            view.startAnimation(enterAnimation);

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
            moving = true;
            view.startAnimation(exitAnimation);

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
        return view.getVisibility() == View.VISIBLE;
    }

    public interface Endings {
        void enter();
        void exit();
    }
}
