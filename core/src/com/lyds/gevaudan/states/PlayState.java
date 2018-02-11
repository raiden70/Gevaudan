package com.lyds.gevaudan.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.lyds.gevaudan.Gevaudan;
import com.lyds.gevaudan.sprites.Wolf;
import com.lyds.gevaudan.sprites.Badger;
import com.lyds.gevaudan.sprites.Bear;
import com.lyds.gevaudan.sprites.Biche;
import com.lyds.gevaudan.sprites.Foudre;
import com.lyds.gevaudan.sprites.Fox;
import com.lyds.gevaudan.sprites.Grappe;
import com.lyds.gevaudan.sprites.Herisson;
import com.lyds.gevaudan.sprites.Poule;
import com.lyds.gevaudan.sprites.Rabbit;
import com.lyds.gevaudan.sprites.Sanglier;
import com.lyds.gevaudan.sprites.Sheep;
import com.lyds.gevaudan.sprites.Steak;
import com.lyds.gevaudan.sprites.Bonus;
import com.lyds.gevaudan.sprites.Ennemy;

/**
 * Created by ami on 25/01/2018.
 */

public class PlayState extends State{
    private Wolf wolf;
    private Bear bear;
    private Badger badger;
    private Biche biche;
    private Foudre foudre;
    private Fox fox;
    private Grappe grappe;
    private Herisson herisson;
    private Poule poule;
    private Rabbit rabbit;
    private Steak steak;
    private Sheep sheep;
    private Sanglier sanglier;

    private Array<Ennemy> ennemies_actuel;
    private Array<Bonus> bonus_actuel;

    private Texture background;
    public int playbtn;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        wolf=new Wolf(100,100);
        background=new Texture("view2.png");
        cam.setToOrtho(false, Gevaudan.WIDTH,Gevaudan.HEIGHT);
        ennemies_actuel = Ennemy_list();
        bonus_actuel = Bonus_list();

    }

    public Array<Ennemy> ennemy_ressources(){
        Array<Ennemy> resources = new Array<Ennemy>();
        bear = new Bear(2000,15);
        badger = new Badger(2000,15);
        fox = new Fox(2000,15);
        foudre = new Foudre(2000,15);
        herisson = new Herisson(2000,15);
        sanglier = new Sanglier(2000,15);
        int i = 0;

        while ( i < 20 ) {
            resources.insert(i, bear);
            resources.insert(i + 1, badger);
            resources.insert(i + 2, fox);
            resources.insert(i + 3, herisson);
            resources.insert(i + 4, sanglier);

            i = i + 5;
        }
        return resources;
    }

    public Array<Bonus> bonus_ressources(){
        Array<Bonus> resources_bonus = new Array<Bonus>();
        biche = new Biche(2000,15);
        grappe = new Grappe(2000,15);
        poule = new Poule(2000,15);
        rabbit = new Rabbit(2000,15);
        sheep = new Sheep(2000,15);
        steak = new Steak(2000,15);
        int i = 0;

        while ( i < 20 ) {
            resources_bonus.insert(i, biche);
            resources_bonus.insert(i + 1, grappe);
            resources_bonus.insert(i + 2, poule);
            resources_bonus.insert(i + 3, rabbit);
            resources_bonus.insert(i + 4, sheep);
            resources_bonus.insert(i + 5, steak);

            i = i + 5;
        }

        return resources_bonus;
    }

    public Array<Ennemy> Ennemy_list(){
        Array<Ennemy> ressources = ennemy_ressources();
        Array<Ennemy> actu = new Array<Ennemy>();
        int number_of_ennemy_to_add = 30, rand = 0, index = 0, j = 0;

        while ( j <= number_of_ennemy_to_add) {
            rand  = randomWithRange(0,5);
            while (index <= 5) {
                if (index == rand) {
                    actu.insert(j, ressources.get(index));
                }
                index++;
            }
            index = 0;
            j++;
        }
        return actu;
    }

    public Array<Bonus> Bonus_list(){
        Array<Bonus> ressources = bonus_ressources();
        Array<Bonus> actu = new Array<Bonus>();
        int number_of_bonus_to_add = 30, rand = 0, index = 0, j = 0;

        while ( j <= number_of_bonus_to_add) {
            rand  = randomWithRange(0,5);
            while (index <= 5) {
                if (index == rand) {
                    actu.insert(j, ressources.get(index));
                }
                index++;
            }
            index = 0;
            j++;
        }
        return actu;
    }

    int randomWithRange(int min, int max){
        int range = (max - min) + 1;
        return (int)(Math.random() *range) + min;
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched())
        {
            wolf.jump();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || (Gdx.input.getAccelerometerY() > 0))
        {
            wolf.accelerate();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || (Gdx.input.getAccelerometerY() < 0))
        {
            wolf.deaccelerate();
        }

    }
//hey
    @Override
    public void update(float dt) {
    handleInput();
    wolf.update(dt);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
    spriteBatch.setProjectionMatrix(cam.combined);
    spriteBatch.begin();
    spriteBatch.draw(background,cam.position.x-(cam.viewportWidth/2),0);
    spriteBatch.draw(wolf.getWolf(),wolf.getPosition().x,wolf.getPosition().y);
    spriteBatch.draw(ennemies_actuel.get(0).getTexture(), 3000 ,ennemies_actuel.get(0).getPosition().y);
    spriteBatch.draw(ennemies_actuel.get(1).getTexture(), 8000 ,ennemies_actuel.get(1).getPosition().y);
    spriteBatch.draw(ennemies_actuel.get(2).getTexture(), 15000 ,ennemies_actuel.get(2).getPosition().y);
    spriteBatch.draw(bonus_actuel.get(0).getTexture(), 2000 ,bonus_actuel.get(0).getPosition().y);
    spriteBatch.draw(bonus_actuel.get(1).getTexture(), 20000 ,bonus_actuel.get(1).getPosition().y);
    spriteBatch.draw(bonus_actuel.get(2).getTexture(), 50000 ,bonus_actuel.get(2).getPosition().y);
    spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
