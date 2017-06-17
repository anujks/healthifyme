package com.android.healthifyme.model;

import java.sql.Date;
import java.util.HashMap;

/**
 * Created by anuj on 15/06/17.
 */

public class Slot {

    HashMap<String,Daytime> slot;

    public Slot(HashMap<String, Daytime> slot) {
        this.slot = slot;
    }

    public HashMap<String, Daytime> getSlot() {
        return slot;
    }

    public void setSlot(HashMap<String, Daytime> slot) {
        this.slot = slot;
    }
}
