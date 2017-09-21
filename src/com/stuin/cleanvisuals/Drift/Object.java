package com.stuin.cleanvisuals.Drift;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by Stuart on 7/13/2017.
 */
public class Object extends ImageView {
    public Plane plane;

    public int offset;
    public int distance;
    public int speed;
    public boolean shown;

    public Object(Plane plane) {
        super(plane.getContext());
        this.plane = plane;

        //Set up basic appearance
        setMinimumHeight(plane.objectLength);
        setMinimumWidth(plane.objectLength);
        setImageDrawable(plane.drawable);
    }

    public void start() {
        //Set new variables
        offset = plane.rand.nextInt(plane.width);
        speed = plane.speed.getInt(plane.rand);
        distance = plane.start;

        //Set new position
        if(plane.side) setTranslationY(offset);
        else setTranslationX(offset);
        shift();

        //start movement
        shown = true;
        setVisibility(View.VISIBLE);
    }

    public void update() {
        //Move once
        if(shown) {
            distance += speed;
            shift();

            //When reached end
            if(Math.abs(distance - plane.start) > plane.length + plane.objectLength) {
                hide();
            }
        }
    }

    public void hide() {
        if(shown) {
            shown = false;
            setVisibility(View.GONE);
            plane.waiting.push(this);
        }
    }

    private void shift() {
        //Position object
        if(plane.side) setTranslationX(distance);
        else setTranslationY(distance);
    }
}
