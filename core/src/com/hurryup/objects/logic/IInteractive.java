package com.hurryup.objects.logic;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Administrator on 2016-09-23.
 */
public interface IInteractive {
    void activate(int whichToActivate);
    void deactivate(int whichToDeactivate);
    Vector2 getVector2();
    Vector2 getConnectionInPos();

    Vector2 getFirstOutPos();
    Vector2 getSecondOutPos();
}
