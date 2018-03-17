package com.lyds.gevaudan.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.lyds.gevaudan.Gevaudan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sacha on 25/02/2018.
 */

public class MenuState extends State {

    private Stage stageM;
    private Texture background;
    private Texture menu_title;
    private ImageButton button_options;
    private ImageButton button_scores;
    private ImageButton button_histoire;
    private ImageButton button_play;
    private ImageButton button_quitter;
    private ArrayList<Integer> scores_to_display;
    private ArrayList<Integer> scores;

    private boolean one;

    /* Constructeur du MenuState
     */
    public MenuState(GameStateManager gsm) {
        super(gsm);

        scores = new ArrayList<Integer>();
        scores_to_display = new ArrayList<Integer>();
        background = new Texture("background1.png");
        menu_title = new Texture("menu_title.png");
        cam.setToOrtho(false, Gevaudan.WIDTH,Gevaudan.HEIGHT);
        create_stage();
    }

    /* Ici on place tous les boutons que l'on souhaite insérer dans le stage. */
    public void create_stage(){
        stageM = new Stage();
        Gdx.input.setInputProcessor(stageM);

        Texture histoire_Texture = new Texture(Gdx.files.internal("histoire.png"));
        TextureRegion histoire_TextureRegion = new TextureRegion(histoire_Texture);
        TextureRegionDrawable histoire_Drawable = new TextureRegionDrawable(histoire_TextureRegion);
        button_histoire = new ImageButton(histoire_Drawable);

        Texture scores_Texture = new Texture(Gdx.files.internal("buttons-scores.png"));
        TextureRegion scores_TextureRegion = new TextureRegion(scores_Texture);
        TextureRegionDrawable scores_Drawable = new TextureRegionDrawable(scores_TextureRegion);
        button_scores = new ImageButton(scores_Drawable);

        Texture options_Texture = new Texture(Gdx.files.internal("buttons-options.png"));
        TextureRegion options_TextureRegion = new TextureRegion(options_Texture);
        TextureRegionDrawable options_Drawable = new TextureRegionDrawable(options_TextureRegion);
        button_options = new ImageButton(options_Drawable);

        Texture quitter_Texture = new Texture(Gdx.files.internal("buttons-exit.png"));
        TextureRegion quitter_TextureRegion = new TextureRegion(quitter_Texture);
        TextureRegionDrawable quitter_Drawable = new TextureRegionDrawable(quitter_TextureRegion);
        button_quitter = new ImageButton(quitter_Drawable);

        Texture play_Texture = new Texture(Gdx.files.internal("buttons-play.png"));
        TextureRegion play_TextureRegion = new TextureRegion(play_Texture);
        TextureRegionDrawable play_Drawable = new TextureRegionDrawable(play_TextureRegion);
        button_play = new ImageButton(play_Drawable);

        int diff2 = (Gdx.graphics.getHeight()) - (Gdx.graphics.getHeight()/9);
        int diff1 = (Gdx.graphics.getHeight()/4) - (Gdx.graphics.getHeight()/8);
        int computed = (Gdx.graphics.getHeight()/2) - diff1;
        button_quitter.setPosition( diff2,(Gdx.graphics.getHeight()/8) - 25 );
        button_options.setPosition( diff2,(Gdx.graphics.getHeight()/4) - 25 );
        button_scores.setPosition( diff2,computed - 25);
        button_histoire.setPosition( diff2,(Gdx.graphics.getHeight()/2) - 25 );
        button_play.setPosition((Gdx.graphics.getHeight() - Gdx.graphics.getWidth()/3) , computed - 25  );

        stageM.addActor(button_histoire);
        stageM.addActor(button_options);
        stageM.addActor(button_play);
        stageM.addActor(button_quitter);
        stageM.addActor(button_scores);
        Gdx.input.setInputProcessor(stageM);
    }

    @Override
    public void handleInput() {

    }

    /* Dans cette fonction overrided update
        on précise vers quel State nous allons migrer:
        PlayState, HistoryState, ScoreState ou quitter le jeu
     */
    @Override
    public void update(float dt) {
        handleInput();

        button_histoire.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {

                if (button_histoire.isPressed()){
                    gsm.set(new HistoryState(gsm));
                    dispose();
                }
                return true;
            }
        });

        button_play.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {

                if (button_play.isPressed()){
                    gsm.set(new PlayState(gsm));
                    dispose();
                }
                return true;
            }
        });

        button_quitter.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {

                if (button_quitter.isPressed()){
                    Gdx.app.exit();
                }
                return true;
            }
        });

        button_scores.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (button_scores.isPressed()){
                    try{
                        if (!one) {
                            save_score();
                            update_best_score();
                            gsm.set(new ScoreState(gsm, scores_to_display));
                            one = true;
                        }
                    }catch (Exception e){
                        return true;
                    }
                }
                return true;
            }
        });
        cam.update();
    }

    /*Fonction d'affichage de tous les élements du menu */
    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(background,cam.position.x-(cam.viewportWidth/2),cam.position.y-(cam.viewportHeight/2)-1);
        spriteBatch.draw(menu_title, (Gevaudan.WIDTH/2)-(menu_title.getWidth()/2) ,(Gevaudan.HEIGHT)-(menu_title.getHeight()));
        spriteBatch.end();
        stageM.act();
        stageM.draw();
    }

    /* C'est la fonction dispose va effetuer la libération de l'espace mémoire alloué pour
       les différents élements déclarés.
       On utilisera clear() pour un objet de Type Stage
     */
    @Override
    public void dispose() {
        background.dispose();
        menu_title.dispose();
        stageM.clear();
    }

    /* Cette fonction est utilisée pour lire notre fichier texte contenant tous nos scores*/
    /* On stocke la liste de tous les scores dans la variable 'scores' */
    public void save_score()throws IOException {
        int score;
        try {
            File f = new File("resultat.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            try {
                String line = br.readLine();

                while (line != null) {
                    score = Integer.parseInt(line);
                    if (!scores.contains(score)) {
                        scores.add(score);
                    }
                    line = br.readLine();
                }
                br.close();
                fr.close();
            } catch (IOException exception) {
                System.out.println("Erreur lors de la lecture : " + exception.getMessage());
            }
        }catch (Exception e){
            System.out.println("Error file");
        }
    }

    /** l'attribut scores_to_display
     * va recevoir les 10 meilleurs scores
     * On va donc afficher scores_to_display
     */
    public void update_best_score(){

        int max = 0;
        int taille = scores.size();
        int i = 0, j;
        for(j = 0; j < 10; j++){
            if (!(scores_to_display.size() >= 10)) {
                while (i < taille) {
                    if (max < scores.get(i) && !scores_to_display.contains(scores.get(i))) {
                        max = scores.get(i);
                    }
                    i++;
                }
                scores_to_display.add(max);
                max = 0;
                i = 0;
            }
        }
    }

}
