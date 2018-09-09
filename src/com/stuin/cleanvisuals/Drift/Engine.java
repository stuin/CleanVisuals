package com.stuin.cleanvisuals.Drift;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by Stuart on 12/5/2017.
 */
public class Engine {
	private ArrayList<Runnable> objects = new ArrayList<>();
	private View view;
	private boolean on = false;
	private int delay;
	
	//Add new object to engine
	public void add(Runnable run) {
		objects.add(run);
	}
	
	//Start running engine
	public void start(View view, int delay) {
		on = true;
		this.view = view;
		this.delay = delay;

        view.postDelayed(updateRunnable, delay);
	}

	public int getDelay() {
		return delay;
	}

	//On each update
    private Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
        	if(on) {
				//Run all objects
				for(Runnable run : objects) {
					run.run();
				}

				//Schedule next run
				view.postDelayed(updateRunnable, delay);
			}
        }
    };
}