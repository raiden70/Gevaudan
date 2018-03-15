package com.lyds.gevaudan.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

/**
 * Created by Sacha on 05/03/2018.
 */

public class LifePoints {

    private Vector2 position;
    private Texture texture;
    private HashMap<Integer, String> barre_points_vie;

    public LifePoints(){
        int y = 420, x = 5;
        String texture_to_check;
        /* On déclare une hashmap pour définir les points de vies en fonctions de la texture de la barre de points de vie */
        barre_points_vie = new HashMap<Integer, String>();

        /* Ici on ajoute les éléments à la hashmap */
        barre_points_vie.put(5,"vie5.png");
        barre_points_vie.put(10, "vie10.png");
        barre_points_vie.put(20, "vie20.png");
        barre_points_vie.put(30, "vie30.png");
        barre_points_vie.put(40,"vie40.png");
        barre_points_vie.put(50,"vie50.png");
        barre_points_vie.put(60,"vie60.png");
        barre_points_vie.put(70,"vie70.png");
        barre_points_vie.put(80,"vie80.png");
        barre_points_vie.put(90,"vie90.png");
        barre_points_vie.put(95,"vie95.png");
        barre_points_vie.put(100,"vie100.png");

        texture_to_check = barre_points_vie.get(100);
        texture = new Texture(texture_to_check);
        position = new Vector2(x , y);
    }

        /*  Cette fonction permet de vérifier les pojnts de vie du loup et charger l'image correspondante à
            sa quantité de points de vie actuel
        */

    public Texture check_lifePoints(int pv){
        Texture texture_to_set;
        String image_to_get;

        if ( pv == 100){
            image_to_get = barre_points_vie.get(100);
            texture_to_set = new Texture(image_to_get);
            return texture_to_set;
        }

        if ( 95 <= pv && pv < 100){
            image_to_get = barre_points_vie.get(95);
            texture_to_set = new Texture(image_to_get);
            return texture_to_set;
        }
        else if ( 90 <= pv && pv < 95){
            image_to_get = barre_points_vie.get(90);
            texture_to_set = new Texture(image_to_get);
            return texture_to_set;
        }
        else if ( 80 <= pv && pv < 90){
            image_to_get = barre_points_vie.get(80);
            texture_to_set = new Texture(image_to_get);
            return texture_to_set;
        }
        else if ( 70 <= pv && pv < 80){
            image_to_get = barre_points_vie.get(70);
            texture_to_set = new Texture(image_to_get);
            return texture_to_set;
        }
        else if ( 60 <= pv && pv < 70){
            image_to_get = barre_points_vie.get(60);
            texture_to_set = new Texture(image_to_get);
            return texture_to_set;
        }
        else if ( 50 <= pv && pv < 60){
            image_to_get = barre_points_vie.get(50);
            texture_to_set = new Texture(image_to_get);
            return texture_to_set;
        }
        else if ( 40 <= pv && pv < 50){
            image_to_get = barre_points_vie.get(40);
            texture_to_set = new Texture(image_to_get);
            return texture_to_set;
        }
        else if ( 30 <= pv && pv < 40){
            image_to_get = barre_points_vie.get(30);
            texture_to_set = new Texture(image_to_get);
            return texture_to_set;
        }
        else if ( 20 <= pv && pv < 30){
            image_to_get = barre_points_vie.get(20);
            texture_to_set = new Texture(image_to_get);
            return texture_to_set;
        }
        else if ( 10 <= pv && pv < 20){
            image_to_get = barre_points_vie.get(10);
            texture_to_set = new Texture(image_to_get);
            return texture_to_set;
        }
        else if ( 0 <= pv && pv < 10) {
            image_to_get = barre_points_vie.get(5);
            texture_to_set = new Texture(image_to_get);
            return texture_to_set;
        }
        return getTexture();
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPositionX(int x) {
        this.position.set(x, position.y);
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }
}
