package com.stuin.cleanvisuals.Drift;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.stuin.cleanvisuals.Range;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Created by Stuart on 7/13/2017.
 */
public class Plane extends RelativeLayout {
	//Technical variables
    private ArrayList<Drifter> drifters = new ArrayList<>();
    private boolean unset = true;
    private int time = 0;

    Stack<Drifter> waiting = new Stack<>();
    Random rand;

	//Create configuration variables
    public boolean on = true;
    public boolean side;
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
    public void setup(Engine engine) {
        rand = new Random();
        unset = false;

        //Set dimensions
        if(side) {
            length = getWidth();
            width = getHeight();
        } else {
            length = getHeight();
            width = getWidth();
        }
		
		//Connect to engine
		engine.add(updateRunnable);
    }

	//Send forth a drifter object
    public Drifter add() {
        Drifter drifter;
        if(waiting.isEmpty()) {
			//Create a new drifter
            drifter = new Drifter(this);
            addView(drifter);
            drifters.add(drifter);
        } else {
			//Recycle an old drifter
            drifter = waiting.pop();
        }
		
		//Start chosen drifter
        drifter.start();
        return drifter;
    }

	//On each update
    private Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            if(on && !unset) {
				//Update all drifters
				for(Drifter drifter : drifters) drifter.update();
				
				//Check for adding another
				if(time % addTime == 0) add();
				time++;
			} else if(waiting.size() < drifters.size()) {
				//Hide all drifters
				for(Drifter drifter : drifters) drifter.hide();
			}
        }
    };
}
