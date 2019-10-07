package com.mygdx.ttrispo.Pantalla;

import com.badlogic.gdx.Gdx;
import com.mygdx.ttrispo.Procesador;
import com.mygdx.ttrispo.Tablero;

public class Partida extends PantallaBase  {
    private Tablero tablero;
    private GestorEstado gEstado;
    private GestorPiezas gPieza;
    public static float x, y;
    private Procesador pc;
    private GameOver gameOver;

    public Partida(){
        gEstado = new GestorEstado(this);
        gPieza = new GestorPiezas(this);
        pc = new Procesador(gEstado);
        gameOver = new GameOver(this);

        Gdx.input.setInputProcessor(pc);


        this.x = Gdx.graphics.getWidth()/4;
        this.y = -(Gdx.graphics.getWidth()/3);
    }

    @Override
    public void show() {
        super.show();
        tablero = new Tablero(x,y);
        stage.addActor(tablero);
        tablero.setPosition(x, y);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.1f, 0.1f,0.1f, 1f);
        // Ciclo de vida
        cicloDeVida(delta);
        stage.draw();
    }

    private void cicloDeVida(float delta) {
        Pieza currentPieza;
        switch (gEstado.getEstado(delta)){
            // Pieza en reposo
            case (GestorEstado.REPOSO):

                break;
            case (GestorEstado.SINPIEZA):
                this.insertarNextPieza();
                gEstado.setFlagSinFicha(false);
                tablero.setImg3(Pieza.getImagen(GestorPiezas.piezasEncoladas.peek().getTipo()));
                break;
            case (GestorEstado.IZQUIERDA):
                currentPieza = gPieza.getCurrentPieza();
                int posicionPiezaIzquierda [][] = currentPieza.getPosicionIzquierda();
                tablero.cambiarBloque(currentPieza.getPosicionPieza(),Pieza.VACIA);
                if(tablero.isColision(posicionPiezaIzquierda)){
                    tablero.cambiarBloque(currentPieza.getPosicionPieza() ,currentPieza.getTipo());
                    //si no puede seguir moviendo a la izquierda pues ahi se queda
                }else{
                    tablero.cambiarBloque(posicionPiezaIzquierda ,currentPieza.getTipo());
                    currentPieza.setC(currentPieza.c -1);
                }
                //Cambia a reposo. pero esto hay que refactorizarlo por el amor de dios
                gEstado.setEstado(gEstado.REPOSO);
                break;

            case (GestorEstado.DERECHA):
                currentPieza = gPieza.getCurrentPieza();
                int posicionPiezaDerecha [][] = currentPieza.getPosicionDerecha();
                tablero.cambiarBloque(currentPieza.getPosicionPieza(),Pieza.VACIA);
                if(tablero.isColision(posicionPiezaDerecha)){
                    tablero.cambiarBloque(currentPieza.getPosicionPieza() ,currentPieza.getTipo());
                    //si no puede seguir moviendo a la derecha pues ahi se queda
                }else{
                    tablero.cambiarBloque(posicionPiezaDerecha ,currentPieza.getTipo());
                    currentPieza.setC(currentPieza.c + 1);
                }
                //Cambia a reposo. pero esto hay que refactorizarlo por el amor de dios
                gEstado.setEstado(gEstado.REPOSO);
                break;
            // La pieza intenta caer
            case (GestorEstado.CAER):
                currentPieza = gPieza.getCurrentPieza();

                int posicionPiezaAbajo [][] = currentPieza.getPosicionAbajo();
                tablero.cambiarBloque(currentPieza.getPosicionPieza(),Pieza.VACIA);
                if(tablero.isColision(posicionPiezaAbajo)){
                    // La pieza no puede bajar
                    tablero.cambiarBloque(currentPieza.getPosicionPieza() ,currentPieza.getTipo());
                    if (tablero.comprobarGameOver(currentPieza.getPosicionPieza())) {
                        stage.clear();
                        stage.addActor(gameOver);

                    }
                    tablero.comprobarLineaCompleta();
                    gEstado.setFlagSinFicha(true);


                }else{
                    // La pieza puede baja
                    tablero.cambiarBloque(posicionPiezaAbajo ,currentPieza.getTipo());
                    currentPieza.setF(currentPieza.f + 1);
                }
                break;
            case (GestorEstado.GIRO):
                currentPieza = gPieza.getCurrentPieza();
                int piezaChocada [][] = currentPieza.getPosicionAbajo();
                tablero.cambiarBloque(currentPieza.getPosicionPieza(),Pieza.VACIA);
                if(tablero.isColision(piezaChocada)){
                    // La pieza no puede bajar
                    tablero.cambiarBloque(currentPieza.getPosicionPieza() ,currentPieza.getTipo());
                    tablero.comprobarLineaCompleta();
                    gEstado.setFlagSinFicha(true);
                }else {
                    if (currentPieza.getGiro()<4){
                        currentPieza.setGiro(1);
                    }else {
                        currentPieza.setGiroInicial();
                    }
                }
                System.out.println("Giro: "+ currentPieza.getGiro());
                gEstado.setEstado(GestorEstado.CAER);
                break;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public boolean insertarNextPieza() {
        Pieza pieza = gPieza.getNextPieza();
        tablero.cambiarBloque(pieza.getPosicionPieza(),pieza.getTipo());
        return false;
    }
}
