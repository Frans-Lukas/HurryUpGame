package com.hurryup.objects.logic;

/**
 * Created by Klas on 2016-09-23.
 */
public enum LogicColor {
    None (0),
    Red (1),
    Green (2),
    Blue (3);

    private final int val;

    LogicColor(int value){
        val = value;
    }

    public int getId(){
        return val;
    }

}

