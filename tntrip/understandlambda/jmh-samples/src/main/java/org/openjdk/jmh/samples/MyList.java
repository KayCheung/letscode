package org.openjdk.jmh.samples;

import java.util.ArrayList;

/**
 * Created by nuc on 2016/12/8.
 */
public class MyList<E> extends ArrayList<E> {
    @Override
    public  boolean add(E e) {
        return super.add(e);
    }

    @Override
    public  void clear() {
        super.clear();
    }
}
