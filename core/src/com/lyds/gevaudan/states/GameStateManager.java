package com.lyds.gevaudan.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by ami on 20/01/2018.
 */

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager() {
       states=new Stack<State>();
    }

    public void push(State state)
    {
        states.push(state);
    }

    /* Définir un nouveau State dans le jeu */
    public void set(State state)
    {
        states.pop(); // on dépile l'écran précdènt
        states.push(state); // on met en avant le nouvel écran
    }

    public void update(float dt)
    {
        states.peek().update(dt);
    }

    public void render(SpriteBatch spriteBatch)
    {
        states.peek().render(spriteBatch);
    }
}
