package com.mygdx.ttrispo.Pantalla;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.ttrispo.Gestores.GestorRecursos;
import com.mygdx.ttrispo.MyGdxGame;
import com.mygdx.ttrispo.Pieza.PiezaI;

public class PantallaAjustes extends PantallaBase{
    private PiezaI piezaI;
    private TextButton BPiezaI, BPiezaJ, BPiezaL, BPiezaO, BPiezaS, BPiezaT, BPiezaZ;
    private ImageTextButton Home, Play;
    private Skin skin;
    Color[] colores = {Color.CYAN, Color.RED, Color.YELLOW, Color.GREEN, Color.VIOLET, Color.BLUE, Color.ORANGE};
    private Texture fondoAjustes;
    private int i;

    public PantallaAjustes (final MyGdxGame game) {
        super(game);
        skin = new Skin(Gdx.files.internal("skins/default/skin/uiskin.json"));
        fondoAjustes = GestorRecursos.get("fondoInicio.jpg");

        BPiezaI = new TextButton("I", skin);
        BPiezaJ = new TextButton("J", skin);
        BPiezaL = new TextButton("L", skin);
        BPiezaO = new TextButton("O", skin);
        BPiezaS = new TextButton("S", skin);
        BPiezaT = new TextButton("T", skin);
        BPiezaZ = new TextButton("Z", skin);
        Home = new ImageTextButton("", skin, "atras");
        Play = new ImageTextButton("", skin, "start");

        //color inicial de cada boton
        BPiezaI.setColor(colores[0]);
        BPiezaJ.setColor(colores[5]);
        BPiezaL.setColor(colores[3]);
        BPiezaO.setColor(colores[1]);
        BPiezaS.setColor(colores[4]);
        BPiezaT.setColor(colores[2]);
        BPiezaZ.setColor(colores[6]);

        //añadir los botones de cada pieza a la tabla
        Table table = new Table();
        table.add(new Image(GestorRecursos.get("ICompleta.png"))).size(300,300);
        table.add(BPiezaI).size(100,100).expandX();
        table.add(new Image(GestorRecursos.get("SCompleta.png"))).size(300,300);
        table.add(BPiezaS).size(100,100).expandX();
        table.row();
        table.add(new Image(GestorRecursos.get("JCompleta.png"))).size(300,300);
        table.add(BPiezaJ).size(100,100).expandX();
        table.add(new Image(GestorRecursos.get("TCompleta.png"))).size(300,300);
        table.add(BPiezaT).size(100,100).expandX();
        table.row();
        table.add(new Image(GestorRecursos.get("LCompleta.png"))).size(300,300);
        table.add(BPiezaL).size(100,100).expandX();
        table.add(new Image(GestorRecursos.get("ZCompleta.png"))).size(300,300);
        table.add(BPiezaZ).size(100,100).expandX();
        table.row();
        table.add();
        table.add(new Image(GestorRecursos.get("OCompleta.png"))).size(300,300).align(Align.left);
        table.add(BPiezaO).size(100,100).expandX().align(Align.left);
        table.row();
        table.add();
     //botón para jugar
        table.add(Play).size(300,100).left();
        Play.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(GestorRecursos.get("B-start.png")));
        Play.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(GestorRecursos.get("B-start.png")));
    //boton de ir atrás
        table.add(Home).size(100,100).left();
        Home.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(GestorRecursos.get("B-atras.png")));
        Home.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(GestorRecursos.get("B-atras.png")));

        table.align(Align.center);
        table.setFillParent(true);
       // table.setDebug(true); //Muestra las líneas
        EventosBotones();
        super.stage.addActor(table);
    }


    private void EventosBotones () {
        BPiezaI.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                BPiezaI.setColor(getNextColorC(colores));
                System.out.println(i);
                System.out.println("total" + colores.length);
                i = i + 1;
                //   setColorNuevoPieza(i); //TAMBIEN PETA
            }
        });

        BPiezaJ.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                BPiezaJ.setColor(getNextColorC(colores));
                System.out.println(i);
                System.out.println("total" + colores.length);
                i = i + 1;
            }
        });

        BPiezaL.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                BPiezaL.setColor(getNextColorC(colores));
                System.out.println(i);
                System.out.println("total" + colores.length);
                i = i + 1;
            }
        });

        BPiezaO.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                BPiezaO.setColor(getNextColorC(colores));
                System.out.println(i);
                System.out.println("total" + colores.length);
                i = i + 1;
            }
        });

        BPiezaS.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                BPiezaS.setColor(getNextColorC(colores));
                System.out.println(i);
                System.out.println("total" + colores.length);
                i = i + 1;
            }
        });

        BPiezaT.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                BPiezaT.setColor(getNextColorC(colores));
                System.out.println(i);
                System.out.println("total" + colores.length);
                i = i + 1;
            }
        });

        BPiezaZ.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                BPiezaZ.setColor(getNextColorC(colores));
                System.out.println(i);
                System.out.println("total" + colores.length);
                i = i + 1;
            }
        });

        Home.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.pantallaInicio);
            }
        });

        Play.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new Partida(game));
            }
        });

    }

    private void resetI(int i) {
        this.i = 0;
    }

   /*
   public Color getNextColorC (Color[] listacolores){ //recorre el array de colores para ir cambiando el color del boton
       for (; i < colores.length; i++) {
           return colores[i];
       }
       resetI(i); //reseteamos i a 0 (primera posicion del array)
    return listacolores[i];
    }
    */
    /*
    public Color getNextColorC (Color[] listacolores){ //recorre el array de colores para ir cambiando el color del boton
        while (i!=listacolores.length) { //OTRA POSIBILIDAD DE RECORRER LA LISTA DE COLORES
           // setColorNuevoPieza(i); //NO FUNCIONA PETA EVERYTHING
            return listacolores[i];
        }

        resetI(i); //reseteamos i a 0 (primera posicion del array)
     return listacolores[i];
    }
    */

    private Color getNextColorC(Color[] listacolores) {
        if (this.i != colores.length) {
            // setColorNuevoPieza(i); NO FUNCIONA PETA EVERYTHING
            return listacolores[i];
        } else {
            resetI(i);
            return listacolores[i];
        }
    }


    private void setColorNuevoPieza(int i) {  //en teoría esto debería cambiar la textura de la pieza I
        switch (i) {
            case (0):
               piezaI.setTextura(GestorRecursos.get("I.jpg")); //cyan
            case (1):
                piezaI.setTextura(GestorRecursos.get("O.jpg")); //red
            case (2):
                piezaI.setTextura(GestorRecursos.get("T.jpg")); //yellow
            case (3):
                piezaI.setTextura(GestorRecursos.get("L.jpg")); //green
            case (4):
                piezaI.setTextura(GestorRecursos.get("S.jpg")); //violet
            case(5):
                piezaI.setTextura(GestorRecursos.get("J.jpg")); //blue
            case(6):
                piezaI.setTextura(GestorRecursos.get("Z.jpg")); //orange
        }
        System.out.println("ha seleccionado el caso "+  i);
    }

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
        batch.begin();
        batch.draw(fondoAjustes, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
     //   Gdx.gl.glClearColor(0.4f,0.5f,0.8f,1f); //azul
        stage.draw();
    }
}
