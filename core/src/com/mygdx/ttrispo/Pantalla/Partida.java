package com.mygdx.ttrispo.Pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.ttrispo.Gestores.GestorEstado;
import com.mygdx.ttrispo.Gestores.GestorPiezas;
import com.mygdx.ttrispo.Gestores.GestorRecursos;
import com.mygdx.ttrispo.MyGdxGame;
import com.mygdx.ttrispo.Pieza.Pieza;
import com.mygdx.ttrispo.Tablero;
import com.mygdx.ttrispo.Procesador;

public class Partida extends PantallaBase {
    private Texture fondoPartida;
    private Tablero tablero;
    private ProgresoPartida progresoPartida;
    private GestorEstado gestorEstado;
    private GestorPiezas gestorPiezas;
    public static float posicionX, posicionY;
    private Procesador procesador;
    private static long puntuacion;
    public static Partida partidaAux;
    private int longitudPuntos;
    private ImageButton derecha, izquierza, giro, abajo;

    public Partida(MyGdxGame game) {
        super(game);
        gestorEstado = new GestorEstado(this);
        gestorPiezas = new GestorPiezas(this);
        procesador = new Procesador(gestorEstado);
        fondoPartida = GestorRecursos.get("background.jpeg");

        tablero = new Tablero(this);
        tablero.setPosition(posicionX, posicionY);
        progresoPartida = new ProgresoPartida(this);

        stage.addActor(tablero);
        stage.addActor(progresoPartida);
        this.longitudPuntos = 0;
        this.puntuacion = 0;

        //Botones
        Skin skin = new Skin(Gdx.files.internal("skins/default/skin/uiskin.json"));

        //Boton derecha
        derecha = new ImageButton(skin, "derecha");
        derecha.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(GestorRecursos.get("B-dere.jpg")));
        derecha.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(GestorRecursos.get("B-dere.jpg")));
        derecha.setSize(derecha.getStyle().imageUp.getMinWidth()-30, derecha.getStyle().imageUp.getMinHeight()-30);
        derecha.setPosition((Gdx.graphics.getWidth() - derecha.getStyle().imageUp.getMinWidth()) / 1.2f, 0.09f*Gdx.graphics.getHeight());

        derecha.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gestorEstado.setEstado(GestorEstado.DERECHA);
            }
        });
        super.stage.addActor(derecha);

        //Boton abajo
        abajo = new ImageButton(skin, "abajo");
        abajo.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(GestorRecursos.get("B-abajo.jpg")));
        abajo.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(GestorRecursos.get("B-abajo.jpg")));
        abajo.setSize(derecha.getStyle().imageUp.getMinWidth()-40, derecha.getStyle().imageUp.getMinHeight()-40);
        abajo.setPosition((Gdx.graphics.getWidth() - derecha.getStyle().imageUp.getMinWidth()) / 2.0f, 0.01f*Gdx.graphics.getHeight());

        abajo.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gestorEstado.setEstado(GestorEstado.CAER);
            }
        });
        super.stage.addActor(abajo);

        //Boton izquierza
        izquierza = new ImageButton(skin, "izquierda");
        izquierza.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(GestorRecursos.get("B-iz.jpg")));
        izquierza.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(GestorRecursos.get("B-iz.jpg")));
        izquierza.setSize(derecha.getStyle().imageUp.getMinWidth()-30, derecha.getStyle().imageUp.getMinHeight()-30);
        izquierza.setPosition((Gdx.graphics.getWidth() - derecha.getStyle().imageUp.getMinWidth()) / 6.0f, 0.09f*Gdx.graphics.getHeight());

        izquierza.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gestorEstado.setEstado(GestorEstado.IZQUIERDA);
            }
        });
        super.stage.addActor(izquierza);

        //Boton giro
        giro = new ImageButton(skin, "rotar");
        giro.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(GestorRecursos.get("B-Rot.jpg")));
        giro.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(GestorRecursos.get("B-Rot.jpg")));
        giro.setSize(derecha.getStyle().imageUp.getMinWidth()-40, derecha.getStyle().imageUp.getMinHeight()-40);
        giro.setPosition((Gdx.graphics.getWidth() - derecha.getStyle().imageUp.getMinWidth()) / 2.0f, 0.13f*Gdx.graphics.getHeight());

        giro.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gestorEstado.setEstado(GestorEstado.GIRO);
            }
        });
        super.stage.addActor(giro);
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(procesador);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(fondoPartida, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        cicloDeVida(delta); // Ciclo de vida

        stage.draw();  // Pintar los actores
    }

    private void cicloDeVida(float delta) {
        switch (gestorEstado.getEstado(delta)) {

            case (GestorEstado.REPOSO): //Si el Gestor esta en reposo
                if (gestorPiezas.getPiezaActual() == null) { //Y no hay pieza siguiente
                    gestorEstado.setEstado(GestorEstado.SINPIEZA); //Modo Sin Pieza
                }
                break;

            case (GestorEstado.SINPIEZA):
                estadoGestorSinPieza(); //Selecciona una nueva Pieza y vuelve al modo de Reposo
                gestorEstado.setVelocity(gestorEstado.getVelocity()-0.005f); //Velocdad
                break;

            case (GestorEstado.IZQUIERDA):
                estadoGestorDesplazarIzq();
                break;

            case (GestorEstado.DERECHA):
                moverDerechaState();
                break;
            // La pieza intenta caer
            case (GestorEstado.CAER):
                caerState();
                break;
            case (GestorEstado.GIRO):
                giroState();
                break;
            case (GestorEstado.BLOQUEAR):
                bloquearPieza();
                break;
        }
    }
    @Override
    public void dispose() {
        super.dispose();
    }

    private void bloquearPieza() {
        // La pieza no puede bajar
        Pieza currentPieza = gestorPiezas.getPiezaActual();
        tablero.insertarBloquesDePieza(currentPieza.getPosicionPieza(), currentPieza.getTipo());
        partidaAux = this;
        if (tablero.comprobarGameOver(currentPieza.getPosicionPieza())) {
            this.dispose();
            game.setScreen(game.pantallaGameOver);
        }
        tablero.comprobarLineaCompleta();
        gestorPiezas.bloquearPieza();
        gestorEstado.setEstado(GestorEstado.SINPIEZA);
    }

    private void giroState() {
        Pieza currentPieza;
        currentPieza = gestorPiezas.getPiezaActual();
        tablero.insertarBloquesDePieza(currentPieza.getPosicionPieza(), Pieza.VACIA);
        currentPieza.girarDer();
        int piezaGirada[][] = currentPieza.getPosicionPieza();
        if (tablero.seProduceColision(piezaGirada)) {
            // La pieza no puede girar
            currentPieza.girarIz();
            tablero.insertarBloquesDePieza(currentPieza.getPosicionPieza(), currentPieza.getTipo());
        } else {
            tablero.insertarBloquesDePieza(currentPieza.getPosicionPieza(), currentPieza.getTipo());
        }
        gestorEstado.setEstado(GestorEstado.REPOSO);
    }

    private void caerState() {
        Pieza currentPieza;
        currentPieza = gestorPiezas.getPiezaActual();

        int posicionPiezaAbajo[][] = currentPieza.getPosicionAbajo();
        tablero.insertarBloquesDePieza(currentPieza.getPosicionPieza(), Pieza.VACIA);
        if (tablero.seProduceColision(posicionPiezaAbajo)) {
            gestorEstado.setEstado(GestorEstado.BLOQUEAR);
        } else {
            // La pieza puede baja
            tablero.insertarBloquesDePieza(posicionPiezaAbajo, currentPieza.getTipo());
            currentPieza.setFila(currentPieza.getFila() + 1);
            gestorEstado.setEstado(GestorEstado.REPOSO);
        }
    }

    private void moverDerechaState() {
        Pieza currentPieza;
        currentPieza = gestorPiezas.getPiezaActual();
        int posicionPiezaDerecha[][] = currentPieza.getPosicionDerecha();
        tablero.insertarBloquesDePieza(currentPieza.getPosicionPieza(), Pieza.VACIA);
        if (tablero.seProduceColision(posicionPiezaDerecha)) {
            tablero.insertarBloquesDePieza(currentPieza.getPosicionPieza(), currentPieza.getTipo());
            //si no puede seguir moviendo a la derecha pues ahi se queda
        } else {
            tablero.insertarBloquesDePieza(posicionPiezaDerecha, currentPieza.getTipo());
            currentPieza.setColumna(currentPieza.getColumna() + 1);
        }
        //Cambia a reposo. pero esto hay que refactorizarlo por el amor de dios
        gestorEstado.setEstado(gestorEstado.REPOSO);
    }

    private void estadoGestorDesplazarIzq() {
        Pieza piezaActual = gestorPiezas.getPiezaActual();

        int posicionPiezaIzquierda[][] = piezaActual.getPosicionIzquierda();
        tablero.insertarBloquesDePieza(piezaActual.getPosicionPieza(), Pieza.VACIA);

        if (tablero.seProduceColision(posicionPiezaIzquierda)) {
            tablero.insertarBloquesDePieza(piezaActual.getPosicionPieza(), piezaActual.getTipo());
            //si no puede seguir moviendo a la izquierda pues ahi se queda
        } else {
            tablero.insertarBloquesDePieza(posicionPiezaIzquierda, piezaActual.getTipo());
            piezaActual.setColumna(piezaActual.getColumna() - 1);
        }
        //Cambia a reposo. pero esto hay que refactorizarlo por el amor de dios
        gestorEstado.setEstado(gestorEstado.REPOSO);
    }

    private void estadoGestorSinPieza() {
        Pieza pieza = gestorPiezas.getPiezaActual();
        tablero.insertarBloquesDePieza(pieza.getPosicionPieza(), pieza.getTipo());
        tablero.setImagenPiezaSiguiente(gestorPiezas.getImagenPiezaSiguiente());
        gestorEstado.setEstado(GestorEstado.REPOSO);
    }

    public Texture getTexturaPieza(int tipo){
        return gestorPiezas.getTexturaBloque(tipo);
    }
    
    public long getPuntuacion(){
        return this.puntuacion;
    }
    
    public void setPuntuacion(int i) {
        long nuevaPuntuacion = puntuacion + i;
        if(String.valueOf(puntuacion).length() < (String.valueOf(nuevaPuntuacion).length())){
            System.out.println("ESTADO TRUE");
            longitudPuntos++;
        }else{
            System.out.println("ESTADO FALSE");
        }
        puntuacion = nuevaPuntuacion;
    }

    public int getLongitudPuntos(){
        return this.longitudPuntos;
    }
}
