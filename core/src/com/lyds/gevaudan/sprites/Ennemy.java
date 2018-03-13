package com.lyds.gevaudan.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import java.util.HashMap;


/**
 * Created by Sacha on 29/01/2018.
 */

public class Ennemy {

    private Vector2 position;
    private Rectangle bounds;
    private Texture texture;
    private int damage;
    private Animation textureAnime;

    public Ennemy(float x){
        int rand_choose;
        int value_to_check;
        int y = 72;
        String texture_to_check;
        /* We declare a HashMap to set the damage corresponding to the texture of an ennemy*/
        HashMap<Integer, String> hmap = new HashMap<Integer, String>();

        /* We add the elements to the HashMap */
        /*hmap.put(80,"bear.png");
        hmap.put(30, "badger.png");*/
        hmap.put(15, "aranea.png");
        hmap.put(25, "bat.png");
        hmap.put(55, "foxy2.png");
        //hmap.put(55, "renard.png");
        hmap.put(40, "aigle_fixe.png");
        hmap.put(45,"sanglier.png");

        int tab[] = {/*80, 30,*/ 15, 25, 55, 40, 45};
        rand_choose = (int)( Math.random()*tab.length);
        value_to_check = tab[rand_choose];
        damage = value_to_check;
        texture_to_check = hmap.get(value_to_check);
        texture = new Texture(texture_to_check);

        if ( value_to_check == 15 ) {
            textureAnime = new Animation(new TextureRegion(texture), 4, 0.5f);
            bounds = new Rectangle(x, y, 1, 6);
        }
        else if ( value_to_check == 25){
            textureAnime = new Animation(new TextureRegion(texture), 4, 0.5f);
            bounds = new Rectangle(x, y, 1, 6);
        }
        else if ( value_to_check == 55){
            textureAnime = new Animation(new TextureRegion(texture), 7, 0.5f);
            bounds = new Rectangle(x, y, 1, 6 );
        }
        else if ( value_to_check == 45){
            textureAnime = new Animation(new TextureRegion(texture), 8, 0.5f);
            bounds = new Rectangle(x, y, 1, 6);
        }
        else{
            textureAnime = new Animation(new TextureRegion(texture), 1, 0.5f);
            bounds = new Rectangle(x, y, getEnnemy().getTexture().getWidth(), getEnnemy().getTexture().getHeight());
        }
        position = new Vector2(x , y);
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

    public int getDamage() {
        return damage;
    }

    public void reposition(float x){
        position.set(x, 72);
        bounds.setPosition(position.x, position.y);
    }

    public int width(){
        return texture.getWidth();
    }

    public boolean collides(Rectangle player){
        return player.overlaps(bounds);
    }

    public TextureRegion getEnnemy() {
        return textureAnime.getFrame();
    }
}

