package com.mygdx.ttrispo.Pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.ttrispo.BaseDeDatos.FirebaseCallback;
import com.mygdx.ttrispo.BaseDeDatos.Jugador;
import com.mygdx.ttrispo.Gestores.GestorRecursos;
import com.mygdx.ttrispo.MyGdxGame;

import java.util.ArrayList;

import static com.mygdx.ttrispo.MyGdxGame.firebaseHelper;
import static java.lang.Thread.sleep;

public class PantallaGameOver extends PantallaBase {
    private Skin skin;
    private TextButton retry;
    private TextButton home;
    private Texture fondoGameOver;
    private BitmapFont font;
    private boolean isRankingLoaded;
    private ArrayList<Jugador> listaRanking;
    private Table table;
    private Label label, labelID;
    private GlyphLayout glyphLayout;

    public PantallaGameOver(final MyGdxGame game){
        super(game);
        fondoGameOver = GestorRecursos.get("GameOver.jpeg");
        skin = new Skin(Gdx.files.internal("skins/default/skin/uiskin.json"));
        retry = new TextButton("Retry", skin);
        home = new TextButton("Home", skin);
        font = new BitmapFont();
        isRankingLoaded = false;
        table = new Table();
        glyphLayout = new GlyphLayout();

        Container<Table> tableContainer = new Container<>();
        float sw = Gdx.graphics.getWidth();
        float sh = Gdx.graphics.getHeight();
        float cw = sw * 0.2f;
        float ch = sh * 0.8f;
        tableContainer.setSize(cw, ch);
        tableContainer.setPosition((sw-cw)/2.0f, (sh-ch)/1.1f);
        tableContainer.fillX();
        table.setSkin(skin);

        retry.setSize(300,100);
        retry.setPosition(Gdx.graphics.getWidth()/2.65f, Gdx.graphics.getHeight()/6);

        home.setSize(300,100);
        home.setPosition(Gdx.graphics.getWidth() / 2.65f, Gdx.graphics.getHeight() / 10);

        tableContainer.setActor(table);
        stage.addActor(retry);
        stage.addActor(home);
        stage.addActor(tableContainer);

        retry.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(listaRanking!=null){
                    listaRanking.clear();
                }
                table.reset();
                game.setScreen(new Partida(game));
            }
        });
        home.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(listaRanking!=null){
                    listaRanking.clear();
                }
                table.reset();
                game.setScreen(game.pantallaInicio);
            }
        });
    }

    @Override
    public void show() {
        super.show();
        firebaseHelper.rellenarArrayDeRanking(new FirebaseCallback() {
            @Override
            public void onCallback(ArrayList<Jugador> lista) {
                firebaseHelper.insertarPuntuacionEnRanking("Alias ttrispo", Partida.partidaAux.getPuntuacion());
                listaRanking = lista;
                isRankingLoaded = true;
            }
        });
    }

    @Override
    public void hide() {
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(fondoGameOver, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.getData().setScale(3);
        glyphLayout.setText(font, "TOP 10 MEJORES PUNTUACIONES");
        font.draw(batch, glyphLayout,(Gdx.graphics.getWidth()-glyphLayout.width)/2, 0.95f*Gdx.graphics.getHeight());
        try {
            // esto simplemente es para que, dependiendo de la conexion del movil, duerma 2 segundos para que le de tiempo al callback a cargar el ranking, pero si aun asi no lo carga, se mostrara el else que viene a continuacion del siguiente if
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(isRankingLoaded) {
            boolean nuevoRank = false;
            for (int i = 1; i < listaRanking.size(); i++) {
                labelID = new Label(i+"ª", skin);
                label = new Label(String.valueOf(listaRanking.get(i).getPuntuacion()), skin);
                label.setAlignment(Align.center);
                labelID.setAlignment(Align.left);
                if ((!nuevoRank) && (Partida.partidaAux.getPuntuacion()==listaRanking.get(i).getPuntuacion())){
                    label.setFontScale(8);
                    labelID.setFontScale(9);
                    nuevoRank = true;
                }else{
                    label.setFontScale(4);
                    labelID.setFontScale(5);
                }
                table.row();
                table.add(labelID).padRight(100);
                table.add(label).padLeft(100);
            }
            isRankingLoaded = false;
        }else{
            if(listaRanking==null){
                font.getData().setScale(2.5f);
                glyphLayout.setText(font, "Conectate a internet para");
                font.draw(batch, glyphLayout, (Gdx.graphics.getWidth()-glyphLayout.width)/2, 0.75f*Gdx.graphics.getHeight());
                glyphLayout.setText(font, "ver el ranking online");
                font.draw(batch, glyphLayout, (Gdx.graphics.getWidth()-glyphLayout.width)/2, 0.7f*Gdx.graphics.getHeight());
            }
        }
        batch.end();
        Gdx.gl.glClearColor(0.4f,0.2f,0.7f,0.7f); //morada
        stage.draw(); // Pintar los actores los botones por encima del background
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
