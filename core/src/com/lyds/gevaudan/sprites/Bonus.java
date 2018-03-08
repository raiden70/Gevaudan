package com.lyds.gevaudan.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;

/**
 * Created by Sacha on 29/01/2018.
 */

public class Bonus {

    private static final int GRAVITY=-10;

    public int MOVEMENTS=300;
    private Vector3 velocity;
    private int lifePoints;
    private Vector2 position;
    private Rectangle bounds;
    private Texture texture;
    private Animation textureAnime;

    public Bonus(float x){
        int rand_choose;
        int value_to_check;
        int y = 65;
        String texture_to_check;
        /* We declare a HashMap to set the damage corresponding to the texture of an ennemy*/
        HashMap<Integer, String> hmap = new HashMap<Integer, String>();
        velocity=new Vector3(0,0,0);

        /* We add the elements to the HashMap */
        //hmap.put(35,"biche.png");
        hmap.put(10, "fraise.png");
        hmap.put(25, "chicken.png");
        hmap.put(15, "rabbit.png");
        hmap.put(30,"mouton.png");
        hmap.put(5, "viande.png");

        int tab[] = {/*35, */10, 25, 15, 30, 5};
        int longueur = tab.length;
        rand_choose = (int)( Math.random()*longueur);
        value_to_check = tab[rand_choose];
        lifePoints = value_to_check;
        texture_to_check = hmap.get(value_to_check);
        texture = new Texture(texture_to_check);

        if ( value_to_check == 30) {
            textureAnime = new Animation(new TextureRegion(texture), 3, 0.5f);
        }
        else if ( value_to_check == 15) {
            textureAnime = new Animation(new TextureRegion(texture), 8, 0.5f);
        }

        else if ( value_to_check == 25) {
            textureAnime = new Animation(new TextureRegion(texture), 10, 0.5f);
        }
        else{
            textureAnime = new Animation(new TextureRegion(texture), 1, 0.5f);
        }
        position = new Vector2(x , y);
        bounds = new Rectangle(x, y, getBonus().getTexture().getWidth(), getBonus().getTexture().getHeight());
    }

    public void update(float dt) {
        textureAnime.update(dt);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public TextureRegion getBonus() {
        return textureAnime.getFrame();
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

    public int getLifePoints() {
        return lifePoints;
    }

}

