package com.mygdx.ttrispo;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.ttrispo.BaseDeDatos.FirebaseHelper;
import com.badlogic.gdx.Gdx;
import com.mygdx.ttrispo.Gestores.GestorRecursos;
import com.mygdx.ttrispo.Pantalla.PantallaAjustes;
import com.mygdx.ttrispo.Pantalla.PantallaGameOver;
import com.mygdx.ttrispo.Pantalla.PantallaInicio;
import com.mygdx.ttrispo.com.mygdx.ttrispo.camara.InterfazCamara;

public class MyGdxGame extends Game implements ApplicationListener {
    public static float ratioPixelesHeight, ratioPixelesWidth;

    public PantallaInicio pantallaInicio;
    public PantallaGameOver pantallaGameOver;
    public PantallaAjustes pantallaAjustes;
    public static FirebaseHelper firebaseHelper;
    private InterfazCamara interfazCamara;
    private MyGdxGame myGdxGame;

    public MyGdxGame(InterfazCamara interfazCamara){
        this.interfazCamara = interfazCamara;
    }

    private static long SPLASH_MINIMUM_MILLIS = 2000L;

    @Override
    public void create() {

        myGdxGame = this;

        setScreen(new SplashScreen());

        final long splash_start_time = System.currentTimeMillis();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        //PREPARA TO MIENTRAS ESTA EN EL SPLASH SCREEN
                        GestorRecursos.cargarImagenes();
                        firebaseHelper = new FirebaseHelper();
                        pantallaGameOver = new PantallaGameOver(myGdxGame, interfazCamara);
                        GestorRecursos.cargarPrevia(pantallaGameOver);

                        ratioPixelesHeight = (float) Gdx.graphics.getHeight()/GestorRecursos.get("background.jpeg").getHeight();
                        ratioPixelesWidth = (float) Gdx.graphics.getWidth()/GestorRecursos.get("background.jpeg").getWidth();    //pixeles = pantallaMovil/background
                        pantallaInicio = new PantallaInicio(myGdxGame);
                        pantallaAjustes = new PantallaAjustes(myGdxGame);
                        //myGdxGame.setScreen(pantallaInicio);

                        // Se muestra el menu principal tras la SpashScreen
                        long splash_elapsed_time = System.currentTimeMillis() - splash_start_time;
                        if (splash_elapsed_time < myGdxGame.SPLASH_MINIMUM_MILLIS) {
                            Timer.schedule(
                                    new Timer.Task() {
                                        @Override
                                        public void run() {
                                            myGdxGame.setScreen(pantallaInicio);
                                        }
                                    }, (float)(myGdxGame.SPLASH_MINIMUM_MILLIS - splash_elapsed_time) / 1000f);
                        } else {
                            myGdxGame.setScreen(pantallaInicio);
                        }
                    }
                });
            }
        }).start();
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
