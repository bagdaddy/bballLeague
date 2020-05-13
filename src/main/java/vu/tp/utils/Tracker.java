package vu.tp.utils;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class Tracker implements Serializable {
    private int count = 0;

    public int track(){
        return this.count++;
    }

    public int getCount(){
        return this.count;
    }
}
