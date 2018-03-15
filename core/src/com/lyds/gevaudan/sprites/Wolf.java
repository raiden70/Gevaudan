package com.lyds.gevaudan.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by ami on 25/01/2018.
 */

public class Wolf {

    private static final int GRAVITY=-10;

    public int MOVEMENTS=300;
    private Vector3 position;
    private Integer lifePoints;
    private Vector3 velocity;
    private Rectangle wolf_bounds;
    private Animation wolfAnim;

    public Wolf(int x,int y)
    {
        position=new Vector3(x,y,0);
        velocity=new Vector3(0,0,0);
        lifePoints = 100;
        Texture texture=new Texture("wolfanime.png");
        wolfAnim=new Animation(new TextureRegion(texture),5,0.5f );
        wolf_bounds = new Rectangle(x, y, getWolf().getTexture().getWidth(), getWolf().getTexture().getHeight());
    }

    /* Cette fonction update permet de gérer les nouvelles positions du loup,
        en fonction de sa vitesse
     */
    public void update(float dt) /* dt -> delta time */
    {
        int reste = 0;

        wolfAnim.update(dt);
        if(position.y>0)
        {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(MOVEMENTS*dt,velocity.y,0);

        velocity.scl(1/dt);
        if(position.y < 70)
        {
            position.y=70;
        }
        if ( MOVEMENTS < 650){
            reste = 650 - MOVEMENTS;
            wolf_bounds.setPosition(getPosition().x - MOVEMENTS - reste, getPosition().y);
        }
        else
            wolf_bounds.setPosition(getPosition().x - MOVEMENTS, getPosition().y);
    }

    /* Fonction de diminution des points du loup */
    public void update_ennemy_lifePoints(int damage){
        int degat, new_lifePoints;
        degat = (damage* 100) / 100;
        new_lifePoints = getLifePoints() - degat;
        setLifePoints(new_lifePoints);
    }

    /* Fonction d'augmentation des points du loup */
    public void update_bonus_lifePoints(int bonus_points) {
        int b_points, new_lifePoints;

        if (getLifePoints() == 100) {
            return;
        } else {
            b_points = (bonus_points * 100) / 100;
            new_lifePoints = getLifePoints() + b_points;

            if (new_lifePoints >= 100) {
                new_lifePoints = 100;
            }
            setLifePoints(new_lifePoints);
        }
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getWolf() {
        return wolfAnim.getFrame();
    }

    public void jump()
    {
        velocity.y=(600);
    }

    public Rectangle getWolf_bounds(){
        return wolf_bounds;
    }

    public Integer getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(Integer lifePoints) {
        this.lifePoints = lifePoints;
    }

}
