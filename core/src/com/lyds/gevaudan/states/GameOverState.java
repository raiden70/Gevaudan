package com.lyds.gevaudan.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.lyds.gevaudan.Gevaudan;

/**
 * Created by Sacha on 25/02/2018.
 */

public class GameOverState extends State {

    private Skin skin;
    private Stage stage;
    private Texture background;
    private Texture game_over;
    private TextButton[] buttons;
    private int diff;

    protected GameOverState(GameStateManager gsm) {
        super(gsm);
        Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        buttons = new TextButton[4];
        background = new Texture("background1.png");
        game_over = new Texture("game_Over.png");
        cam.setToOrtho(false, Gevaudan.WIDTH,Gevaudan.HEIGHT);
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);
        skin.add("background",new Texture(pixmap));

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
        create_stage();

    }

    public void create_stage(){
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        buttons[0] = new TextButton("Sauver & Recommencer", skin);
        buttons[1] = new TextButton("Sauver & Quitter", skin);
        buttons[2] = new TextButton("Recommencer", skin);
        buttons[3] = new TextButton("Quitter", skin);

        buttons[2].setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , (Gdx.graphics.getHeight()/4)  );
        buttons[3].setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , (Gdx.graphics.getHeight()/8)  );
        int diff1 = (Gdx.graphics.getHeight()/4) - (Gdx.graphics.getHeight()/8);
        diff = diff1;
        int computed = (Gdx.graphics.getHeight()/2) - diff1;
        buttons[0].setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , (Gdx.graphics.getHeight()/2)  );
        buttons[1].setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , computed  );

        stage.addActor(buttons[0]);
        stage.addActor(buttons[1]);
        stage.addActor(buttons[2]);
        stage.addActor(buttons[3]);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void handleInput() {

        buttons[2].addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if ( buttons[2].isPressed()){
                    gsm.set(new PlayState(gsm));
                    dispose();
                }
                return true;
            }
        });

        buttons[3].addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if ( buttons[3].isPressed()){
                    Gdx.app.exit();
                }
                return true;
            }
        });
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(background,cam.position.x-(cam.viewportWidth/2),cam.position.y-(cam.viewportHeight/2)-1);
        spriteBatch.draw(game_over, (Gevaudan.WIDTH/2)-(game_over.getWidth()/2) ,(Gevaudan.HEIGHT)-(game_over.getHeight()));
        spriteBatch.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        skin.dispose();
        background.dispose();
        game_over.dispose();
        stage.dispose();
    }

}
