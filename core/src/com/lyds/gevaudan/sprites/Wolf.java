package com.lyds.gevaudan.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.lyds.gevaudan.Gevaudan;

/**
 * Created by ami on 25/01/2018.
 */

public class Wolf {

    private static final int GRAVITY=-10;
    private Vector3 position;
    private Texture wolf;
    private Integer lifePoints;
    private Vector3 velocity;
    private Rectangle wolf_bounds;

    public Wolf(int x,int y)
    {
        position=new Vector3(x,y,0);
        velocity=new Vector3(0,0,0);
        lifePoints = 1000;
        wolf=new Texture("wolf1.png");
        wolf_bounds = new Rectangle(x, y, wolf.getWidth(), wolf.getHeight());
    }

    public void update(float dt)
    {
        if(position.y>0)
        {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(velocity.x,velocity.y,0);
        velocity.scl(1/dt);
        if(position.y < 0)
        {
            position.y=0;
        }

        if(position.x<0)
        {
            position.x=0;
        }
        wolf_bounds.setPosition(position.x, position.y);

    }

    public void update_ennemy_lifePoints(int damage){
        int degat = 0, new_lifePoints;
        System.out.println("current lifePoints" + getLifePoints());
        //Gestion des points de vies
        degat = (damage* 1000) / 100;
        new_lifePoints = getLifePoints() - degat;
        setLifePoints(new_lifePoints);
        System.out.println("new life Points" + getLifePoints());
    }

    public void update_bonus_lifePoints(int bonus_points){
        int b_points = 0, new_lifePoints;

        if ( getLifePoints() == 1000){
            System.out.println("Aucun changements");
            return;
        }

        else {
            System.out.println("current lifePoints" + getLifePoints());
            //Gestion des points de vies
            b_points = (bonus_points * 1000) / 100;
            new_lifePoints = getLifePoints() + b_points;

            if ( new_lifePoints >= 1000){
                new_lifePoints = 1000;
            }
            setLifePoints(new_lifePoints);
            System.out.println("new life Points" + getLifePoints());
        }
    }

    public Vector3 getPosition() {
        return position;
    }
    public Texture getWolf() {
        return wolf;
    }
    public void jump()
    {
        velocity.y=(250);
    }
    public void accelerate() {
        velocity.x=(150);
    }
    public void deaccelerate()
    {
        velocity.x=(-150);
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