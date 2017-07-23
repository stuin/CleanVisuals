package com.stuin.cleanvisuals;

import java.util.Random;

/**
 * Created by Stuart on 7/13/2017.
 */
public class Range {
    public int min;
    public int max;

    public Range() {

    }

    public Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getInt(Random rand) {
        return rand.nextInt(max - min) + min;
    }

    public boolean contains(int i) {
        return i > min && i < max;
    }
}
