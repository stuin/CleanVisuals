package com.stuin.cleanvisuals;

import android.view.View;

/**
 * Created by Stuart on 3/17/2017.
 */
public class SliderSync {
    private Slider primary;
    private Slider secondary;

    public boolean unSet = true;

    public SliderSync(View prime, View second) {
        primary = new Slider(prime);
        secondary = new Slider(second);
    }

    public void setup(boolean side, int startP, int startS, int duration) {
        unSet = false;
        primary.setup(side, startP, duration);
        secondary.setup(side, startS, duration);
    }

    public void endings(Slider.Endings endings) {
        primary.endings = endings;
    }

    public boolean showPrimary() {
        primary.enter();
        return secondary.exit();
    }

    public boolean showSecondary() {
        secondary.enter();
        return primary.exit();
    }

    public boolean hide() {
        return (primary.exit() || secondary.exit());
    }

    public boolean show() {
        return (primary.enter() || secondary.enter());
    }
}
