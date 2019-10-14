package com.mygdx.ttrispo.Gestores;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.ttrispo.Pantalla.Partida;
import com.mygdx.ttrispo.Pieza.Pieza;
import com.mygdx.ttrispo.Pieza.PiezaI;
import com.mygdx.ttrispo.Pieza.PiezaJ;
import com.mygdx.ttrispo.Pieza.PiezaL;
import com.mygdx.ttrispo.Pieza.PiezaO;
import com.mygdx.ttrispo.Pieza.PiezaS;
import com.mygdx.ttrispo.Pieza.PiezaT;
import com.mygdx.ttrispo.Pieza.PiezaZ;

import java.util.ArrayList;
import java.util.Random;

public class GestorPiezas {
    private final Partida partida;
    // Colecion de pieza
    private Pieza piezas[];
    public static ArrayList<Integer> piezasEncoladas;
    private Pieza currentPieza;
    public static int aleatorio;
    private Random rand;

    /**
     * Constructor de las clase {@link GestorPiezas}
     * Inicializa un {@link ArrayList} de piezas encoladas y lo rellena.
     * @param partida partida que se esta ejecutando en curso.
     */
    public GestorPiezas(Partida partida) {
        piezasEncoladas = new ArrayList<Integer>();
        this.partida = partida;
        //relleno de las piezas de partida.
        this.addPiezas();
        rellenarCola();
    }

    /**
     * Encola hasta un maximo de 4 piezas
     */
    private void rellenarCola() {
        while (piezasEncoladas.size() < 4) {
            piezasEncoladas.add(getTypePiezaRandom());
        }
    }

    /**
     * Retorna un numero alateatorio que sera el numero perteneciente al tipo de pieza.
     * Genera un random y recoge el resultado.
     * @return Integer, el nunmero random que representa el tipo de pieza
     */
    private int getTypePiezaRandom() {
        this.rand = new Random();
        return rand.nextInt(7) + 1;
    }

    /**
     * Inicializa y rellena el array de piezas.
     * Inicializacion de 7 piezas con la fila 0 y la columna 5.
     */
    private void addPiezas() {
        this.piezas = new Pieza[8];
        //Las piezas estan numeradas, cada una se coloca en su posicion dentro del array
        piezas[Pieza.T] = new PiezaT(0, 5);
        piezas[Pieza.S] = new PiezaS(0, 5);
        piezas[Pieza.Z] = new PiezaZ(0, 5);
        piezas[Pieza.I] = new PiezaI(0, 5);
        piezas[Pieza.O] = new PiezaO(0, 5);
        piezas[Pieza.L] = new PiezaL(0, 5);
        piezas[Pieza.J] = new PiezaJ(0, 5);
    }

    /**
     * Obtiene la pieza actual.
     * Si no encontramos el objeto pieza, obtenemos una {@link Pieza} nueva y rellenamos la cola.
     * @return retornamos una {@link Pieza}
     */
    public Pieza getCurrentPieza() {
        if (currentPieza == null) {
            currentPieza = piezas[piezasEncoladas.remove(0)];
            rellenarCola();
        }
        return currentPieza;
    }

    /**
     * Metodo que pide a la pieza que se bloquee y coloca la currentPieza a null, debido a que al e
     * estar bloqueada ya no puede seguir interactuando.
     */
    public void bloquearPieza() {
        currentPieza.bloquear();
        currentPieza = null;
    }

    /**
     *
     * @return
     */
    public Texture getImagenNextPieza() {
        Pieza nextPiezas = piezas[piezasEncoladas.get(0)];
        //¿?¿?¿?
        if (nextPiezas == null) {
            rellenarCola();
            nextPiezas = piezas[piezasEncoladas.get(0)];
        }
        return nextPiezas.getImagen();
    }

    /**
     * Obtiene la textura de la {@link Pieza} que se pasa por parametro
     * @param tipo {@link Pieza} que se quiere obtener la textura
     * @return la {@link Texture} que corresponde a la {@link Pieza}
     */
    public Texture getTexturaBloque(int tipo) {
        return piezas[tipo].getTexture();
    }
}
