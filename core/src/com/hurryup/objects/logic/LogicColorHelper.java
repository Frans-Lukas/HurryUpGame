package com.hurryup.objects.logic;

/**
 * Created by Klas on 2016-09-23.
 */
public class LogicColorHelper {

    private final static LogicColor[] values = LogicColor.values();

    private LogicColorHelper(){
    }

    public static LogicColor fromInt(int i){
        if(i < 0 || i > values.length-1)
            return LogicColor.None;
        else
            return values[i];
    }
}
