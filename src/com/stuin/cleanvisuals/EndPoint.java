package com.stuin.cleanvisuals;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stuart on 3/31/2017.
 */
public class EndPoint {
    String address;
    List<String> propIds;
    List<String> propVals;
    boolean secure = false;

    public EndPoint(String address) {
        this.address = address;
        propIds = new ArrayList<>();
        propVals = new ArrayList<>();
    }

    public void addProp(String id, String val) {
        secure = true;
        propIds.add(id);
        propVals.add(val);
    }
}
