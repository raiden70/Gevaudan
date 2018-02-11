package com.lyds.gevaudan.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Sacha on 22/01/2018.
 */


/** OK  A faire courrir sur la gauche plus tard**/

public class Sanglier extends Ennemy{

    public Sanglier(int x, int y){
        position = new Vector3(x,y,0);
        texture = new Texture("sanglier.png");
        damage = 40;
    }
}
