package com.sf.brinkstest.async;

import com.squareup.otto.Bus;

/**
 * Created by sf on 2/13/17.
 */

public class MyBus {
    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }
}
