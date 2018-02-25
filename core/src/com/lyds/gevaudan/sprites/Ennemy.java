package com.lyds.gevaudan.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

/**
 * Created by Sacha on 29/01/2018.
 */

public class Ennemy {

    private Vector2 position;
    private Rectangle bounds;
    private Texture texture;
    private int damage;

    public Ennemy(float x){
        int rand_choose;
        int value_to_check;
        int y = 15;
        String texture_to_check;
        /* We declare a HashMap to set the damage corresponding to the texture of an ennemy*/
        HashMap<Integer, String> hmap = new HashMap<Integer, String>();

        /* We add the elements to the HashMap */
        hmap.put(80,"bear.png");
        hmap.put(30, "badger.png");
        hmap.put(55, "renard.png");
        hmap.put(40, "hérisson.png");
        hmap.put(45,"sanglier.png");

        int tab[] = {80, 30, 55, 40, 45};
        rand_choose = (int)( Math.random()*5);
        value_to_check = tab[rand_choose];
        damage = value_to_check;
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

    public int getDamage() {
        return damage;
    }

    public void reposition(float x){
        position.set(x, 0);
        bounds.setPosition(position.x, position.y);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int width(){
        return texture.getWidth();
    }

    public boolean collides(Rectangle player){
        return player.overlaps(getBounds());
    }
}


