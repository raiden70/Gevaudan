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
    public void pop()
    {
        states.pop();
    }
    public void set(State state)
    {
        states.pop();
        states.push(state);
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
