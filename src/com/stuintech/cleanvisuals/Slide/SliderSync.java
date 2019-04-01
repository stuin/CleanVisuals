package com.stuintech.cleanvisuals.Slide;

import android.view.View;

/**
 * Created by Stuart on 3/17/2017.
 */
public class SliderSync {
    private Slider primary;
    private Slider secondary;

    public boolean unSet = true;
    public Endings endings;

    public SliderSync(View prime, View second) {
        //Set views
        primary = new Slider(prime);
        secondary = new Slider(second);
    }

    public void setup(boolean side, int startP, int startS, int duration) {
        //Prepare both sliders
        unSet = false;
        primary.setup(side, startP, duration);
        secondary.setup(side, startS, duration);

        //Link custom endings through
        primary.endings = new Endings() {
            @Override
            public void enter() {
                if(endings != null) endings.enter();
            }

            @Override
            public void exit() {
                if(endings != null) endings.exit();
            }
        };
    }

    public boolean showPrimary() {
        //Show only primary view
        if(!moving()) {
            secondary.exit();
            return primary.enter();
        }
        return false;
    }

    public boolean showSecondary() {
        //Show only secondary view
        if(!moving()) {
            primary.exit();
            return secondary.enter();
        }
        return false;
    }

    public boolean hide() {
        //Hide both views
        return !moving() && (primary.exit() || secondary.exit());
    }

    public boolean primaryShown() {
        //Detect if primary on screen
        return primary.shown();
    }

    public boolean secondaryShown() {
        //Detect if secondary on screen
        return secondary.shown();
    }
    
    public boolean moving() {
        //Detect if either is moving
        return primary.moving() || secondary.moving();
    }
}
