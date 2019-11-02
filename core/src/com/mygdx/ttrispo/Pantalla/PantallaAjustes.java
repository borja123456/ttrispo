package com.mygdx.ttrispo.Pantalla;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.ttrispo.Gestores.GestorRecursos;
import com.mygdx.ttrispo.MyGdxGame;
import com.mygdx.ttrispo.Pieza.PiezaI;

public class PantallaAjustes extends PantallaBase{
    private PiezaI piezaI;
    private TextButton CPiezaI, exit;
    private Skin skin;
    Color[] colores = {Color.RED, Color.CYAN, Color.YELLOW, Color.GREEN, Color.VIOLET, Color.ORANGE };
    private int i = 0;

    public PantallaAjustes (final MyGdxGame game) {
        super(game);
        skin = new Skin(Gdx.files.internal("skins/default/skin/uiskin.json"));

        CPiezaI = new TextButton("Next", skin);
        exit = new TextButton("Back", skin);
        CPiezaI.setColor(Color.CYAN);


        Table table = new Table();
        table.add(CPiezaI).size(100,100); //boton para cambiar el Color de la Pieza I
        table.row();
        table.add(exit);

        table.align(Align.center);

        table.setFillParent(true);
        table.setDebug(true);

        CPiezaI.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                i = i + 1;
                CPiezaI.setColor(getNextColorC(colores));
            }
        });
        exit.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.pantallaInicio);
            }
        });

        stage.addActor(table);
    }

    public Color getNextColorC (Color[] colores){ //recorre el array de colores para ir cambiando el color del boton
        for (int n = i; i< colores.length; i++)  {
            return colores[i];
        }
        setColorNuevoPieza(i); //Cambio el color de la pieza que aparece en el tablero
        this.i = 0;
    return colores[i];
    }

    private void setColorNuevoPieza(int i) {  //en teoría esto debería cambiar la textura de la pieza I
        switch (i) {
            case (0):
               piezaI.setTextura(GestorRecursos.get("O.jpg"));
            case (1):
                piezaI.setTextura(GestorRecursos.get("I.jpg"));
            case (2):
                piezaI.setTextura(GestorRecursos.get("T.jpg"));
            case (3):
                piezaI.setTextura(GestorRecursos.get("L.jpg"));
            case (4):
                piezaI.setTextura(GestorRecursos.get("T.jpg"));
            case (5):
                piezaI.setTextura(GestorRecursos.get("Z.jpg"));
        }
    }

   /* public Color getNextColor() {
        switch (i) {
            case (0):
                return Color.RED;
            case (1):
                return Color.CYAN;
            case (2):
                return Color.YELLOW;
            case (3):
                return Color.GREEN;
            case (4):
                return Color.VIOLET;
            case (5):
                return Color.ORANGE;
        }
        return null;
    }*/

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.4f,0.5f,0.8f,1f); //azul

    }
}
