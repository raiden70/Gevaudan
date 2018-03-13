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
    private Texture wolf;
    private Integer lifePoints;
    private Vector3 velocity;
    private Rectangle wolf_bounds;

    private Animation wolfAnim;

    public Wolf(int x,int y)
    {
        position=new Vector3(x,y,0);
        velocity=new Vector3(0,0,0);
        lifePoints = 100;
        // wolf=new Texture("wolf1.png");
        Texture texture=new Texture("wolfanime.png");
        wolf = texture;
        wolfAnim=new Animation(new TextureRegion(texture),5,0.5f );
        wolf_bounds = new Rectangle(x, y, getWolf().getTexture().getWidth(), getWolf().getTexture().getHeight());
    }

    public void update(float dt)
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

    public void update_ennemy_lifePoints(int damage){
        int degat = 0, new_lifePoints;
        //Gestion des points de vies
        degat = (damage* 100) / 100;
        new_lifePoints = getLifePoints() - degat;
        setLifePoints(new_lifePoints);
    }

    public void update_bonus_lifePoints(int bonus_points) {
        int b_points = 0, new_lifePoints;

        if (getLifePoints() == 100) {
            return;
        } else {
            //Gestion des points de vies
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
    public Texture getWolfTexture() {
        return wolf;
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

    public void setWolf_bounds(Rectangle wolf_bounds) {
        this.wolf_bounds = new Rectangle(wolf_bounds.x, wolf_bounds.y, wolf_bounds.getWidth(), wolf_bounds.getHeight());
    }
}
