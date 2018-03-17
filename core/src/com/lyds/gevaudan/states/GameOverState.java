package com.lyds.gevaudan.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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

    private Stage stageG;
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
    private Label text;
    private Label text1;
    private boolean one;

    /* Construction de la classe GameOver qui sera appelé lorsque les points de
        vie du loup sont à 0
     */
    public GameOverState(GameStateManager gsm, String new_score) {
        super(gsm);

        /* On définit un premier style de texte pour le résultat du score */
        BitmapFont font = new BitmapFont();
        Color color = Color.RED;
        Label.LabelStyle textStyle;
        textStyle = new Label.LabelStyle();
        textStyle.font = font;
        textStyle.fontColor = color;
        text = new Label("", textStyle);
        text.setFontScale(3f);
        text.setText(new_score);

         /* On définit un premier style de texte pour le texte : "Voici votre score"  */
        BitmapFont font1 = new BitmapFont();
        Label.LabelStyle textStyle1;
        textStyle1 = new Label.LabelStyle();
        textStyle1.font = font1;
        textStyle1.fontColor = color;
        text1 = new Label("", textStyle1);
        text1.setFontScale(2f);
        String texte_written = "Voici votre score";
        text1.setText(texte_written);

        one = false;
        score_to_add = Integer.valueOf(new_score);
        scores = new ArrayList<Integer>();
        scores_to_comp = new ArrayList<Integer>();
        scores_to_display = new ArrayList<Integer>();

        background = new Texture("view3.png");
        game_over = new Texture("game_Over.png");
        cam.setToOrtho(false, Gevaudan.WIDTH,Gevaudan.HEIGHT);

        create_stage();
    }

    /*
        On définit un objet de type Stage à travers lequel nous allons ajouter les
        différentes bouttons.
     */
    public void create_stage(){
        stageG = new Stage();
        Gdx.input.setInputProcessor(stageG);

        /* On commence par définir la texture de chacun de ces bouttons */
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

        /* On définit la position de ces bouttons par rapport à l'écran */
        button3.setPosition(Gdx.graphics.getWidth()/2 - button3.getWidth()/2 , (Gevaudan.HEIGHT)/6 - button3.getHeight());
        button1.setPosition((Gdx.graphics.getWidth()/2 -  button1.getWidth()/2), 2*(Gevaudan.HEIGHT)/5 - 2*button1.getHeight());
        button0.setPosition(Gdx.graphics.getWidth()/2 - button0.getWidth()/2 , 2*(Gevaudan.HEIGHT)/2 - 25*button0.getHeight()/4);

        button2.setPosition((Gdx.graphics.getWidth()/2 - button2.getWidth()/2) , 2*(Gevaudan.HEIGHT)/3 - 2*button1.getHeight());

        text1.setPosition((Gdx.graphics.getWidth()/12), (Gdx.graphics.getHeight()/2) - 10);
        text.setPosition((Gdx.graphics.getWidth()/8), (Gdx.graphics.getHeight()/3));

        /* On ajoute tous les élements à l'objet Stage */
        stageG.addActor(button0);
        stageG.addActor(button1);
        stageG.addActor(button2);
        stageG.addActor(button3);
        stageG.addActor(text);
        stageG.addActor(text1);
        Gdx.input.setInputProcessor(stageG);
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
        stageG.act();
        stageG.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        game_over.dispose();
        stageG.clear();
    }

    /* On sauve tous les anciens scores de notre fichier texte dans une variable */
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

    /* on enregistre le nouveau score dans notre variable globale afin de pouvoir l'insérer dans
       notre fichier texte.
     */
    public void add_new_score(Integer new_score){
        scores.add(new_score);
    }

    /*A travers cette fonction on enregistre tous les scores dans un fichier texte*/
    public void write_score_into_file(){
        try{
            String chaine;
            int i=0;
            File file;
            FileWriter fileWriter;
            /* On définit le fichier ou l'on va insérer les résultats de tous les scores joués */
            file = new File("resultat.txt");

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

    /** Dans cette fonction on remet à jour les 10 meilleurs scores
     * l'attribut scores_to_display recevra ces 10 meilleurs scores
     */
    public void update_best_score(){
        int max = 0, i=0, j, taille = scores.size();
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

    /* Fonction de test pour afficher les scores sur le terminal
    public void afficher_Scores(){
        int i = 1;
        System.out.println("Voici la liste des 10 meilleurs scores.\n");
        while ( i <= scores_to_display.size()){
            System.out.println("Score n° " + i + " : "+ scores_to_display.get(i-1) + "\n");
            i++;
        }
    }*/
}
