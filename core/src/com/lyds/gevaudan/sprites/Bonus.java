package com.lyds.gevaudan.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

/**
 * Created by Sacha on 29/01/2018.
 */

public class Bonus {

    private int lifePoints;
    private Vector2 position;
    private Rectangle bounds;
    private Texture texture;

    public Bonus(float x){
        int rand_choose;
        int value_to_check;
        int y = 15;
        String texture_to_check;
        /* We declare a HashMap to set the damage corresponding to the texture of an ennemy*/
        HashMap<Integer, String> hmap = new HashMap<Integer, String>();

        /* We add the elements to the HashMap */
        hmap.put(35,"biche.png");
        hmap.put(10, "grape.png");
        hmap.put(25, "cocote.png");
        hmap.put(15, "lapin.png");
        hmap.put(30,"brebis.png");
        hmap.put(5, "viande.png");

        int tab[] = {35, 10, 25, 15, 30, 5};
        rand_choose = (int)( Math.random()*6);
        value_to_check = tab[rand_choose];
        lifePoints = value_to_check;
        texture_to_check = hmap.get(value_to_check);
        texture = new Texture(texture_to_check);
        position = new Vector2(x , y);
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());

    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public void reposition(float x){
        position.set(x, 15);
        bounds.setPosition(position.x, position.y);
    }

    public int width(){
        return texture.getWidth();
    }

    public boolean collides(Rectangle player){
        return player.overlaps(bounds);
    }

    public int getLifePoints() {
        return lifePoints;
    }

}

