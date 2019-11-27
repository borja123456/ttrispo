package com.mygdx.ttrispo;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.ttrispo.BaseDeDatos.FirebaseHelper;
import com.badlogic.gdx.Gdx;
import com.mygdx.ttrispo.Gestores.GestorRecursos;
import com.mygdx.ttrispo.Pantalla.PantallaAjustes;
import com.mygdx.ttrispo.Pantalla.PantallaGameOver;
import com.mygdx.ttrispo.Pantalla.PantallaInicio;
import com.mygdx.ttrispo.com.mygdx.ttrispo.camara.InterfazCamara;

public class MyGdxGame extends Game implements ApplicationListener {
    public static float ratioPixelesHeight, ratioPixelesWidth;
    public static float VARIABLE_GLOBAL_PROGRESO = 0;

    public PantallaInicio pantallaInicio;
    public PantallaGameOver pantallaGameOver;
    public PantallaAjustes pantallaAjustes;
    public static FirebaseHelper firebaseHelper;
    private InterfazCamara interfazCamara;
    private MyGdxGame myGdxGame;

    public MyGdxGame(InterfazCamara interfazCamara){
        this.interfazCamara = interfazCamara;
    }

    public static AssetManager manager = new AssetManager();

    @Override
    public void create() {
        GestorRecursos.cargarImagenes();
        myGdxGame = this;
        ratioPixelesHeight = (float) Gdx.graphics.getHeight()/GestorRecursos.get("background.jpeg").getHeight();
        ratioPixelesWidth = (float) Gdx.graphics.getWidth()/GestorRecursos.get("background.jpeg").getWidth();    //pixeles = pantallaMovil/background
        pantallaInicio = new PantallaInicio(myGdxGame);
        pantallaAjustes = new PantallaAjustes(myGdxGame);
        firebaseHelper = new FirebaseHelper();
        pantallaGameOver = new PantallaGameOver(myGdxGame, interfazCamara);
        SplashScreen splashScreen = new SplashScreen(this, interfazCamara);
        setScreen(splashScreen);
    }

    @Override
    public void dispose() {
        GestorRecursos.limpiarAssets();
        getScreen().dispose();
        Gdx.app.exit();
    }

    @Override
    public void render() {
        super.render();
    }
}
