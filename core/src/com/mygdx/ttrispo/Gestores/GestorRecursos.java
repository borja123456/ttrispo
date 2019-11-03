package com.mygdx.ttrispo.Gestores;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public  class GestorRecursos {
    private static AssetManager manager = new AssetManager();
    private static int contador = 0;
    public static void cargarImagenes() {
        manager.load("fondoInicio.jpg", Texture.class);
        manager.load("tetris.png", Texture.class);
        manager.load("B-start.png", Texture.class);
        manager.load("B-ajustes.png", Texture.class);
        manager.load("colorPiezas.png", Texture.class);
        manager.load("B-atras.png", Texture.class);
        manager.load("B-retry.png", Texture.class);
        manager.load("B-home.png", Texture.class);
        manager.load("colorPiezas.png", Texture.class);

        manager.load("B-dere.jpg", Texture.class);
        manager.load("BP-dere.jpeg", Texture.class);
        manager.load("B-iz.jpg", Texture.class);
        manager.load("BP-iz.jpeg", Texture.class);
        manager.load("B-Rot.jpg", Texture.class);
        manager.load("BP-Rot.jpeg", Texture.class);
        manager.load("B-abajo.jpg", Texture.class);
        manager.load("BP-abajo.jpeg", Texture.class);

        manager.load("T.jpg", Texture.class);
        manager.load("S.jpg", Texture.class);
        manager.load("Z.jpg", Texture.class);
        manager.load("J.jpg", Texture.class);
        manager.load("L.jpg", Texture.class);
        manager.load("I.jpg", Texture.class);
        manager.load("O.jpg", Texture.class);
        manager.load("TCompleta.png", Texture.class);
        manager.load("SCompleta.png", Texture.class);
        manager.load("ZCompleta.png", Texture.class);
        manager.load("JCompleta.png", Texture.class);
        manager.load("LCompleta.png", Texture.class);
        manager.load("ICompleta.png", Texture.class);
        manager.load("OCompleta.png", Texture.class);
        manager.load("background.jpeg", Texture.class);
        manager.load("GameOver.jpeg", Texture.class);
        while (!manager.update()) {
            //System.out.println("Cargando...");
            contador++;
        }
    }

    public static void limpiarAssets(){
        manager.clear();
    }

    public static Texture get(String s) {
        return manager.get(s);
    }

}
