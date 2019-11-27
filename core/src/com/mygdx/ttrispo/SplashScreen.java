package com.mygdx.ttrispo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.ttrispo.BaseDeDatos.FirebaseHelper;
import com.mygdx.ttrispo.Gestores.GestorRecursos;
import com.mygdx.ttrispo.Pantalla.PantallaAjustes;
import com.mygdx.ttrispo.Pantalla.PantallaBase;
import com.mygdx.ttrispo.Pantalla.PantallaGameOver;
import com.mygdx.ttrispo.Pantalla.PantallaInicio;
import com.mygdx.ttrispo.com.mygdx.ttrispo.camara.InterfazCamara;

public class SplashScreen implements Screen {
    private SpriteBatch batch;
    private Texture ttrSplash;
    private TextureRegionDrawable bordeLogo, logo;

    private Stage stage;

    private Image loadingFrame;
    private Image loadingBarHidden;
    private Image loadingBg;

    private float startX, endX;
    private float percent;

    private Actor loadingBar;
    private MyGdxGame game;

    private InterfazCamara interfazCamara;

    public SplashScreen(MyGdxGame game, InterfazCamara interfazCamara) {
        this.interfazCamara = interfazCamara;
        batch = new SpriteBatch();
        this.game = game;
        bordeLogo = new TextureRegionDrawable(new Texture("borde-carga-logo.png"));
        logo = new TextureRegionDrawable(new Texture("logo.png"));
        ttrSplash = new Texture("splash-bg.jpg");
    }

    @Override
    public void show() {
        // Tell the manager to load assets for the loading screen
        game.manager.load("data/loading.pack", TextureAtlas.class);
        // Wait until they are finished loading
        game.manager.finishLoading();

        // Initialize the stage where we will place everything
        stage = new Stage();

        // Get our textureatlas from the manager
        TextureAtlas atlas = game.manager.get("data/loading.pack", TextureAtlas.class);

        // Grab the regions from the atlas and create some images
        loadingFrame = new Image(atlas.findRegion("loading-frame"));
        loadingBarHidden = new Image(atlas.findRegion("loading-bar-hidden"));
        loadingBg = new Image(atlas.findRegion("loading-frame-bg"));


        // Add the loading bar animation
        Animation<TextureRegion> anim = new Animation(0.05f, atlas.findRegions("loading-bar-anim") );
        anim.setPlayMode(Animation.PlayMode.LOOP_REVERSED);
        loadingBar = new LoadingBar(anim);
        loadingBar.setSize(bordeLogo.getMinWidth(), bordeLogo.getMinHeight());
        // Or if you only need a static bar, you can do
        //loadingBar = new Image(atlas.findRegion("loading-bar1"));

        // Add all the actors to the stage
        stage.addActor(loadingBar);
        stage.addActor(loadingBg);
        stage.addActor(loadingBarHidden);
        stage.addActor(loadingFrame);

        //PREPARA TO MIENTRAS ESTA EN EL SPLASH SCREEN
        GestorRecursos gestorRecursos = new GestorRecursos();
        gestorRecursos.cargarPrevia(game.pantallaGameOver, interfazCamara);
        gestorRecursos.conversor(game.pantallaGameOver);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(ttrSplash, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bordeLogo.draw(batch, 340*(Gdx.graphics.getWidth()/ttrSplash.getWidth()), 760*(Gdx.graphics.getHeight()/ttrSplash.getHeight()),
                bordeLogo.getMinWidth(), bordeLogo.getMinHeight());
        logo.draw(batch, 346*(Gdx.graphics.getWidth()/ttrSplash.getWidth()), 776*(Gdx.graphics.getHeight()/ttrSplash.getHeight()), logo.getMinWidth(), logo.getMinHeight());
        batch.end();
        // Interpolate the percentage to make it more smooth
        percent = Interpolation.linear.apply(percent, game.VARIABLE_GLOBAL_PROGRESO, 0.1f);
        System.out.println("PERCENT: " + percent);
        //        System.out.println("HOLA ME PINTAS? PERCENT: " + percent + " gestor recursos: " + GestorRecursos.dameManager().getProgress() + " Var: "+ game.VARIABLE_GLOBAL_PROGRESO);
        // Update positions (and size) to match the percentage
        loadingBarHidden.setX(startX + endX * percent);
        loadingBg.setX(loadingBarHidden.getX() + 30);
        loadingBg.setWidth(450 - 450 * percent);
        loadingBg.invalidate();
        stage.draw();

        // Show the loading screen
        stage.act();

        if(percent >= 0.95f){
            game.setScreen(game.pantallaInicio);
        }
    }

    @Override
    public void hide() {
        game.manager.unload("data/loading.pack");
    }

    @Override
    public void resize(int width, int height) {

        // Place the loading frame in the middle of the screen
        loadingFrame.setX((stage.getWidth() - loadingFrame.getWidth()) / 2);
        loadingFrame.setY((stage.getHeight() - loadingFrame.getHeight()) / 2);

        loadingBar.setSize(bordeLogo.getMinWidth(), bordeLogo.getMinHeight());
        // Place the loading bar at the same spot as the frame, adjusted a few px
        loadingBar.setX(loadingFrame.getX() + 15);
        loadingBar.setY(loadingFrame.getY() + 5);


        // Place the image that will hide the bar on top of the bar, adjusted a few px
        loadingBarHidden.setX(loadingBar.getX() + 35);
        loadingBarHidden.setY(loadingBar.getY() - 3);
        // The start position and how far to move the hidden loading bar
        startX = loadingBarHidden.getX();
        endX = 440;
        loadingBg.setSize(bordeLogo.getMinWidth(), bordeLogo.getMinHeight());
        // The rest of the hidden bar
        loadingBg.setSize(450, 50);
        loadingBg.setX(loadingBarHidden.getX() + 30);
        loadingBg.setY(loadingBarHidden.getY() + 3);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        ttrSplash.dispose();
        batch.dispose();
    }
}