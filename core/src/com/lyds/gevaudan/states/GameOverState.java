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
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sacha on 25/02/2018.
 */

public class GameOverState extends State {

    private Stage stage;
    private Texture background;
    private Texture game_over;
    private ImageButton button0;
    private ImageButton button1;
    private ImageButton button2;
    private ImageButton button3;
    private ArrayList<Integer> scores;
    private ArrayList<Integer> scores_to_comp;
    private ArrayList<Integer> scores_to_display;
    private Integer score_to_add;

    private boolean one;

    protected GameOverState(GameStateManager gsm, String new_score) {
        super(gsm);

        one = false;
        score_to_add = Integer.valueOf(new_score);
        scores = new ArrayList<Integer>();
        scores_to_comp = new ArrayList<Integer>();
        scores_to_display = new ArrayList<Integer>();

        background = new Texture("background1.png");
        game_over = new Texture("game_Over.png");
        cam.setToOrtho(false, Gevaudan.WIDTH,Gevaudan.HEIGHT);

        create_stage();
    }

    public void create_stage(){
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Texture myTexture0 = new Texture(Gdx.files.internal("sauverrec.png"));
        TextureRegion myTextureRegion0 = new TextureRegion(myTexture0);
        TextureRegionDrawable myTexRegionDrawable0 = new TextureRegionDrawable(myTextureRegion0);
        button0 = new ImageButton(myTexRegionDrawable0);

        Texture myTexture1 = new Texture(Gdx.files.internal("sauverqui.png"));
        TextureRegion myTextureRegion1 = new TextureRegion(myTexture1);
        TextureRegionDrawable myTexRegionDrawable1 = new TextureRegionDrawable(myTextureRegion1);
        button1 = new ImageButton(myTexRegionDrawable1);

        Texture myTexture2 = new Texture(Gdx.files.internal("recommencer.png"));
        TextureRegion myTextureRegion2 = new TextureRegion(myTexture2);
        TextureRegionDrawable myTexRegionDrawable2 = new TextureRegionDrawable(myTextureRegion2);
        button2 = new ImageButton(myTexRegionDrawable2);

        Texture myTexture3 = new Texture(Gdx.files.internal("quitter.png"));
        TextureRegion myTextureRegion3 = new TextureRegion(myTexture3);
        TextureRegionDrawable myTexRegionDrawable3 = new TextureRegionDrawable(myTextureRegion3);
        button3 = new ImageButton(myTexRegionDrawable3);

        int diff1 = (Gdx.graphics.getHeight()/4) - (Gdx.graphics.getHeight()/8);
        int computed = (Gdx.graphics.getHeight()/2) - diff1;
        button3.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , (Gdx.graphics.getHeight()/8) - 25 );
        button1.setPosition((Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8), (Gdx.graphics.getHeight()/4) - 25 );
        button0.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , computed - 25);
        button2.setPosition((Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8) + 25 , (Gdx.graphics.getHeight()/2) - 25 );

        stage.addActor(button0);
        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button3);
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

                if (button0.isPressed()){
                    int nb = score_to_add;
                    try {
                        save_score();
                    }catch (Exception e){
                    }
                    add_new_score(nb);
                    write_score_into_file();
                    update_best_score();
                    gsm.set(new PlayState(gsm));
                    dispose();
                }
                return true;
            }
        });

        button1.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (button1.isPressed()){
                    try{
                        int nb = score_to_add;
                        if (!one) {
                        save_score();
                        add_new_score(nb);
                        write_score_into_file();
                        update_best_score();
                        one = true;
                        }
                    Gdx.app.exit();
                    }catch (Exception e){
                        return true;
                    }
                }
                return true;
            }
        });

        button2.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if ( button2.isPressed()){
                    gsm.set(new PlayState(gsm));
                    dispose();
                }
                return true;
            }
        });

        button3.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if ( button3.isPressed()){
                    Gdx.app.exit();
                }
                return true;
            }
        });

        cam.update();
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
        background.dispose();
        game_over.dispose();
        stage.dispose();
    }

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

    public void add_new_score(Integer new_score){
        scores.add(new_score);
    }

    public void write_score_into_file(){
        /* saver le score dans un fichier*/
        String chaine;
        try{
            int i=0;
            File file;
            FileWriter fileWriter;
            file = new File("resultat.txt"); // définir l'arborescence

            file.createNewFile();
            fileWriter = new FileWriter(file);
            while (i < scores.size()){
                if (!scores_to_comp.contains(scores.get(i))) {
                    chaine = scores.get(i).toString();
                    fileWriter.write(chaine);
                    fileWriter.write("\n");
                    scores_to_comp.add(scores.get(i));
                }
                i++;
            }
            fileWriter.close(); // fermer le fichier à la fin des traitements
        } catch (Exception e) {}
    }

    public void update_best_score(){
        /** l'attribut scores_to_show
         * va recevoir les 10 meilleurs scores
         */
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

    public void afficher_Scores(){
        int i = 1;
        System.out.println("Voici la liste des 10 meilleurs scores.\n");
        while ( i <= scores_to_display.size()){
            System.out.println("Score n° " + i + " : "+ scores_to_display.get(i-1) + "\n");
            i++;
        }
    }
}
