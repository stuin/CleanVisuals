package com.stuin.cleanvisuals.Slide;

import android.view.View;
import com.stuin.cleanvisuals.Slide.Slider;

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
        primary.enter();
        return secondary.exit();
    }

    public boolean showSecondary() {
        //Show only secondary view
        secondary.enter();
        return primary.exit();
    }

    public boolean hide() {
        //Hide both views
        return (primary.exit() || secondary.exit());
    }

    public boolean primaryShown() {
        return primary.shown();
    }

    public boolean secondaryShown() {
        return secondary.shown();
    }
}
