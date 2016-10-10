package com.hurryup.objects.helper;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Klas on 2016-10-09.
 */
public class ConnectionPair {
    private Vector2 to;
    private Vector2 from;

    public ConnectionPair(Vector2 to, Vector2 from) {
        this.to = to;
        this.from = from;
    }

    public Vector2 getTo() {
        return to;
    }

    public void setTo(Vector2 to) {
        this.to = to;
    }

    public Vector2 getFrom() {
        return from;
    }

    public void setFrom(Vector2 from) {
        this.from = from;
    }
}