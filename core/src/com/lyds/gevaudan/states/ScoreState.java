package com.lyds.gevaudan.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.lyds.gevaudan.Gevaudan;

import java.util.ArrayList;

/**
 * Created by Sacha on 06/03/2018.
 */

public class ScoreState extends State {

    private Skin skin;
    private Stage stage;
    private Texture background;
    private Texture score_title;
    private ImageButton button0;
    private ArrayList<String> scores;
    private ArrayList<Integer> scores_integer;
    private ArrayList<Label> text;
    private boolean one;

    protected ScoreState(GameStateManager gsm, ArrayList<Integer> score) {
        super(gsm);

        one = false;
        text = new ArrayList<Label>(20);
        scores = new ArrayList<String>(100);
        scores_integer = new ArrayList<Integer>();
        scores_integer = score;

        background = new Texture("background1.png");
        score_title = new Texture("Star.png");
        cam.setToOrtho(false, Gevaudan.WIDTH,Gevaudan.HEIGHT);

        create_stage();
        init_score();
    }

    public void create_stage(){
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Texture myTexture0 = new Texture(Gdx.files.internal("menu_boutton.png"));
        TextureRegion myTextureRegion0 = new TextureRegion(myTexture0);
        TextureRegionDrawable myTexRegionDrawable0 = new TextureRegionDrawable(myTextureRegion0);
        button0 = new ImageButton(myTexRegionDrawable0);

        button0.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , 0  );
        stage.addActor(button0);
        Gdx.input.setInputProcessor(stage);
    }

    public void init_score(){
        int i,k;
        String label;
        for(i = 0; i < 10; i++){
            k = i + 1;
            label = "Score n° " + k + " : " + scores_integer.get(i) + "\n";
            scores.add(i,label);
        }

        BitmapFont font = new BitmapFont();
        Label.LabelStyle textStyle;
        textStyle = new Label.LabelStyle();
        textStyle.font = font;

        Label label_var = new Label("", textStyle);
        label_var.setFontScale(8f);

        for(i = 0; i < 10; i++) {
            label  = scores.get(i);
            label_var = new Label("", textStyle);
            label_var.setText(label);
            text.add(i,label_var);
        }
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
        spriteBatch.draw(background,cam.position.x-(cam.viewportWidth/2),cam.position.y-(cam.viewportHeight/2)-1);
        spriteBatch.draw(score_title, (Gevaudan.WIDTH/2)-(score_title.getWidth()/2) ,(Gevaudan.HEIGHT)-(score_title.getHeight()));

        text.get(0).setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , (Gdx.graphics.getHeight()/2) );
        text.get(0).draw(spriteBatch, 1);

        text.get(1).setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , (Gdx.graphics.getHeight()/2) - 35 );
        text.get(1).draw(spriteBatch, 1);

        text.get(2).setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , (Gdx.graphics.getHeight()/2) - 70 );
        text.get(2).draw(spriteBatch, 1);

        text.get(3).setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , (Gdx.graphics.getHeight()/2) - 105 );
        text.get(3).draw(spriteBatch, 1);

        text.get(4).setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , (Gdx.graphics.getHeight()/2) - 140 );
        text.get(4).draw(spriteBatch, 1);

        text.get(5).setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 + 130 , (Gdx.graphics.getHeight()/2) );
        text.get(5).draw(spriteBatch, 1);

        text.get(6).setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 + 130, (Gdx.graphics.getHeight()/2)- 35 );
        text.get(6).draw(spriteBatch, 1);

        text.get(7).setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 + 130, (Gdx.graphics.getHeight()/2) - 70);
        text.get(7).draw(spriteBatch, 1);

        text.get(8).setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 + 130, (Gdx.graphics.getHeight()/2) - 105 );
        text.get(8).draw(spriteBatch, 1);

        text.get(9).setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 + 130 , (Gdx.graphics.getHeight()/2) - 140 );
        text.get(9).draw(spriteBatch, 1);

        spriteBatch.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        skin.dispose();
        background.dispose();
        score_title.dispose();
        stage.dispose();
    }
}
