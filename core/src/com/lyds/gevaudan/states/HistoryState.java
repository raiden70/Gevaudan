package com.lyds.gevaudan.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.lyds.gevaudan.Gevaudan;

/**
 * Created by Sacha on 06/03/2018.
 */

public class HistoryState extends State {

    private Stage stage;
    private Texture background;
    private ImageButton button0;
    private Texture history_1;
    private Texture history_2;
    private Skin skin;

    protected HistoryState(GameStateManager gsm) {
        super(gsm);

        background = new Texture("background1.png");
        history_1 = new Texture("histoire1.png");
        history_2 = new Texture("histoire2.png");
        cam.setToOrtho(false, Gevaudan.WIDTH,Gevaudan.HEIGHT);

        create_stage();
    }

    public void create_stage(){
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Texture myTexture0 = new Texture(Gdx.files.internal("menu_boutton.png"));
        TextureRegion myTextureRegion0 = new TextureRegion(myTexture0);
        TextureRegionDrawable myTexRegionDrawable0 = new TextureRegionDrawable(myTextureRegion0);
        button0 = new ImageButton(myTexRegionDrawable0);
        button0.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , (Gdx.graphics.getHeight()/8)  );
        stage.addActor(button0);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void handleInput() {
    }

    @Override
    public void update(float dt) {
        handleInput();

        button0.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if ( button0.isPressed()){
                    gsm.set(new MenuState(gsm));
                }
                return true;
            }
        });
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        int diff2 = (Gevaudan.HEIGHT)-(history_1.getHeight());
        int diff2_2 = (Gevaudan.HEIGHT)-(history_1.getHeight() + history_2.getHeight());
        spriteBatch.draw(background,cam.position.x-(cam.viewportWidth/2),cam.position.y-(cam.viewportHeight/2)-1);
        spriteBatch.draw(history_1, (Gevaudan.WIDTH)/20 ,diff2);
        spriteBatch.draw(history_2, (Gevaudan.WIDTH)/20 ,diff2_2);

        button0.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , (Gdx.graphics.getHeight() - (Gdx.graphics.getHeight()) ));

        spriteBatch.end();
        stage.act();
        stage.draw();
    }


    @Override
    public void dispose() {
        background.dispose();
        history_1.dispose();
        stage.dispose();
    }
}
