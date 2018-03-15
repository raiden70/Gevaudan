package com.lyds.gevaudan.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by ami on 20/01/2018.
 */

public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    /*Lorsque l'on souhaite passer certaines fonctionnalités du update dans le handleInput
      afin d'obtenir un code plus structurée.
     */
    protected abstract void handleInput();

    /* La fonction update est une fonction qui va permettre une mise à jour automatique et
       continue du jeu en fonction des interactions souhaitées
     */
    public abstract void update(float dt);

    /* La fonction render permet l'affichage et l'animation de la texture de tous les
       élements qui seront crées dans les states.
     */
    public abstract void render(SpriteBatch ab);

    /* C'est grace à cette fonction que l'on va supprimer les objets crées au cours des differents
       stages et ainsi libérer de l'espace mémoire.
     */
    public abstract void dispose();
}
