package com.lyds.gevaudan.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Sacha on 29/01/2018.
 */

public class Ennemy {

    protected Vector3 position;
    protected Texture texture;
    protected int damage;

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}

