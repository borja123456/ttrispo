package com.mygdx.ttrispo.Pantalla;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.ttrispo.Gestores.GestorRecursos;
import com.mygdx.ttrispo.MyGdxGame;
import com.mygdx.ttrispo.Pieza.Pieza;
import com.mygdx.ttrispo.Pieza.PiezaI;

import java.util.ArrayList;

public class PantallaAjustes extends PantallaBase{
    private TextButton BPiezaI, BPiezaJ, BPiezaL, BPiezaO, BPiezaS, BPiezaT, BPiezaZ;
    private ImageButton Home, Play;
    private Skin skin;
    private Color[] colores = {null, Color.YELLOW, Color.VIOLET, Color.ORANGE , Color.CYAN, Color.RED, Color.GREEN, Color.BLUE };
    private Texture fondoAjustes;
    private TextureRegion fraseAjustes;
    public int i, j, l, o, s, t, z;
    private static boolean coloresPersonalizados;
    public static ArrayList<Texture> texturaPiezas = new ArrayList<>();
    private Sprite paraGirar1, paraGirar2;
    private final long switchfps = 10;
    private boolean cambio;
    private long tiempoInicial;
    //supongamos que se guardan con el siguiente orden, para seguir el patron que tiene GestorPieza en su array de Color[]:
    //texturaPiezas = [T, S, Z, I, O, L, J], solo guarda la textura.

    public PantallaAjustes (final MyGdxGame game) {
        super(game);
        skin = new Skin(Gdx.files.internal("skins/default/skin/uiskin.json"));
        fondoAjustes = GestorRecursos.get("fondoInicio.jpg");
        fraseAjustes = new TextureRegion(GestorRecursos.get("colorPiezas.png"));
        coloresPersonalizados = false;
        cambio = false;
        tiempoInicial = 0;
        paraGirar1 = new Sprite(fondoAjustes);
        paraGirar2 = new Sprite(fondoAjustes);

        BPiezaT = new TextButton("T", skin);
        BPiezaS = new TextButton("S", skin);
        BPiezaZ = new TextButton("Z", skin);
        BPiezaI = new TextButton("I", skin);
        BPiezaO = new TextButton("O", skin);
        BPiezaL = new TextButton("L", skin);
        BPiezaJ = new TextButton("J", skin);

        Home = new ImageButton(skin, "atras");
        Play = new ImageButton(skin, "start");

        //color inicial de cada boton
        BPiezaT.setColor(colores[1]);
        BPiezaS.setColor(colores[2]);
        BPiezaZ.setColor(colores[3]);
        BPiezaI.setColor(colores[4]);
        BPiezaO.setColor(colores[5]);
        BPiezaL.setColor(colores[6]);
        BPiezaJ.setColor(colores[7]);

        t=1; s=2; z=3; i=4; o=5; l=6; j=7;

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
       //table.setDebug(true); //Muestra las líneas
        super.stage.addActor(table);
        EventosBotones();
    }

    public static void setColoresPersonalizados(boolean b) {
        coloresPersonalizados = b;
    }


    private void EventosBotones () {
        BPiezaI.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                i = resetContador(i);
                BPiezaI.setColor(colores[i]);
            }
        });

        BPiezaJ.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                j = resetContador(j);
                BPiezaJ.setColor(colores[j]);
            }
        });

        BPiezaL.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                l = resetContador(l);
                BPiezaL.setColor(colores[l]);
            }
        });

        BPiezaO.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                o = resetContador(o);
                BPiezaO.setColor(colores[o]);
            }
        });

        BPiezaS.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                s = resetContador(s);
                BPiezaS.setColor(colores[s]);
            }
        });

        BPiezaT.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                t = resetContador(t);
                BPiezaT.setColor(colores[t]);
            }
        });

        BPiezaZ.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                z = resetContador(z);
                BPiezaZ.setColor(colores[z]);
            }
        });

        Home.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.pantallaInicio);
            }
        });

        Play.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                coloresPersonalizados = true;
                texturaPiezas.add(null);//posicion 0, siempre es recomendable
                texturaPiezas.add(getColorNuevoPieza(t)); // T = 1
                System.out.println("Color pieza T: " + t);
                texturaPiezas.add(getColorNuevoPieza(s)); // S = 2
                System.out.println("Color pieza S: " + s);
                texturaPiezas.add(getColorNuevoPieza(z)); // Z = 3
                System.out.println("Color pieza Z: " + z);
                texturaPiezas.add(getColorNuevoPieza(i)); // I = 4
                System.out.println("Color pieza I: " + i);
                texturaPiezas.add(getColorNuevoPieza(o)); // O = 5
                System.out.println("Color pieza O: " + o);
                texturaPiezas.add(getColorNuevoPieza(l)); // L = 6
                System.out.println("Color pieza L: " + l);
                texturaPiezas.add(getColorNuevoPieza(j)); // J = 7
                System.out.println("Color pieza J: " + j);

                game.setScreen(new Partida(game));
            }
        });

    }
    public static boolean getColoresPersonalizados(){
        return coloresPersonalizados;
    }

    private int resetContador(int letra) {
        if(letra == 7){
            letra = 1;
        }else{
            letra+=1;
        }
        return letra;
    }

    private Texture getColorNuevoPieza(int letra) {  //en teoría esto debería cambiar la textura de la pieza I
        Texture textura;
        switch (letra) {
            case 1:
                textura = GestorRecursos.get("T.jpg");
                 //yellow
               break;
            case 2:
                textura = GestorRecursos.get("S.jpg");
                 //violet
                break;
            case 3:
                textura = GestorRecursos.get("Z.jpg");
                 //orange
                break;
            case 4:
                textura = GestorRecursos.get("I.jpg");
                 //blue
                break;
            case 5:
                textura = GestorRecursos.get("O.jpg");
                 //red
                break;
            case 6:
                textura = GestorRecursos.get("L.jpg");
                 //green
                break;
            case 7:
                textura = GestorRecursos.get("J.jpg");
                //cyan
                break;
            default:
                textura = null;
        }
        return textura;
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
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
        paraGirar1.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        paraGirar1.rotate((float) 0.1);
        paraGirar1.draw(batch, 100);
        paraGirar2.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        paraGirar2.rotate((float) -0.1);
        paraGirar2.draw(batch, 100);
        batch.draw(fraseAjustes, 0.1f*Gdx.graphics.getWidth(), 0.9f*Gdx.graphics.getHeight(), 0.5f*fraseAjustes.getRegionWidth(), 0.5f*fraseAjustes.getRegionHeight());
        if(cambio){
            ((OrthographicCamera)stage.getCamera()).zoom-=0.001f;
            tiempoInicial++;
            if(tiempoInicial==switchfps){
                tiempoInicial=0;
                cambio = false;
            }
        }else {
            ((OrthographicCamera)stage.getCamera()).zoom+=0.001f;
            tiempoInicial++;
            if(tiempoInicial==switchfps){
                tiempoInicial=0;
                cambio = true;
            }
        }
        batch.end();
        stage.draw();
    }
}
