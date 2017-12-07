package com.stuin.cleanvisuals.Drift;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by Stuart on 12/5/2017.
 */
public class Engine {
	private int updateTime = 1;
	private ArrayList<Runnable> objects = new ArrayList<>();
	private View view;
	private boolean on = false;
	
	//Add new object to engine
	public void add(Runnable run) {
		objects.add(run);
	}
	
	//Start running engine
	public void start(View view) {
		on = true;
		this.view = view;
        view.postDelayed(updateRunnable, updateTime);
	}

	//On each update
    private Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
        	if(on) {
				//Run all objects
				for (Runnable run : objects)
					run.run();

				//Schedule next run
				view.postDelayed(updateRunnable, updateTime);
			}
        }
    };
}