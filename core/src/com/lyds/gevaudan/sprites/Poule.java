package com.lyds.gevaudan.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Sacha on 22/01/2018.
 */

public class Poule extends Bonus{

    public Poule(int x, int y){
        position = new Vector3(x,y,0);
        texture = new Texture("cocote.png");
        lifePoints = 25;
    }
}
