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

    private int lifePoints;
    private Vector2 position;
    private Rectangle bounds;
    private Texture texture;
    private Animation textureAnime;

    public Bonus(float x){
        int rand_choose, value_to_check, y = 72, tab[] = {/*35, */10, 25, 15, 30, 5};
        String texture_to_check;
        /* On déclare une hashmap pour définir les points bonus en plus en fonctions de la texture de l'item Bonus  */
        HashMap<Integer, String> hmap = new HashMap<Integer, String>();

        /* Ici on ajoute les éléments à la hashmap */
        //hmap.put(35,"biche.png");
        hmap.put(10, "fraise.png");
        hmap.put(25, "chicken.png");
        hmap.put(15, "rabbit.png");
        hmap.put(30,"mouton.png");
        hmap.put(5, "viande.png");

        /* C'est ici que le nouvel ennemit est définit aléatoirement grâce à la fonction random */
        rand_choose = (int)( Math.random()*tab.length);
        value_to_check = tab[rand_choose];
        lifePoints = value_to_check;
        texture_to_check = hmap.get(value_to_check);
        texture = new Texture(texture_to_check);

        /* En fonction de la texture choisit nous définissons les limites du bound de l'image */
        if ( value_to_check == 30) {
            textureAnime = new Animation(new TextureRegion(texture), 3, 0.5f);
            bounds = new Rectangle(x, y, 1, 6);
        }
        else if ( value_to_check == 15) {
            textureAnime = new Animation(new TextureRegion(texture), 8, 0.5f);
            bounds = new Rectangle(x, y, 1, 6);
        }

        else if ( value_to_check == 25) {
            textureAnime = new Animation(new TextureRegion(texture), 10, 0.5f);
            bounds = new Rectangle(x, y, 1, 6);
        }
        else{
            textureAnime = new Animation(new TextureRegion(texture), 1, 0.5f);
            bounds = new Rectangle(x, y, getBonus().getTexture().getWidth(), getBonus().getTexture().getHeight());
        }
        position = new Vector2(x , y);
    }

    public void update(float dt) {
        textureAnime.update(dt);
    }

    public Vector2 getPosition() {
        return position;
    }

    public TextureRegion getBonus() {
        return textureAnime.getFrame();
    }

    /* On appelle cette fonction lorsque l'on souhaite repositionner le bonus */
    public void reposition(float x){
        position.set(x, 72);
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

