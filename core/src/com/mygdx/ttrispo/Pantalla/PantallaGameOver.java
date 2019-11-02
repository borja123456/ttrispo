package com.mygdx.ttrispo.Pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
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


public class PantallaGameOver extends PantallaBase {
    private Skin skin;
    private TextButton retry;
    private TextButton home;
    private Texture fondoGameOver;
    private BitmapFont font;
    private boolean isRankingLoaded, activo;
    private ArrayList<Jugador> listaRanking;
    private Table table;
    private Label label, labelID, labelAlias;
    private GlyphLayout glyphLayout;

    private String alias;
    private long pasado;
    private long futuro;


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
        activo = false;
        listaRanking=null;
        pasado = 0;
    }
    private void realShow1() {
        firebaseHelper.rellenarArrayDeRanking(new FirebaseCallback() {
            @Override
            public void onCallback(ArrayList<Jugador> lista) {
                firebaseHelper.insertarPuntuacionEnRanking(alias, Partida.partidaAux.getPuntuacion());
                listaRanking = lista;
                isRankingLoaded = true;
            }
        });
    }
    private void realShow2() {
        recogerAlias(new AliasCallback() {
            @Override
            public void onCallback(String cadena) {
                alias = cadena;
                if (alias.length() > 8) {
                    alias = alias.substring(0, 8);
                    alias = alias + "...";
                }
                pasado = System.currentTimeMillis();
                realShow1();
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
        if(!activo) {
            realShow2();
            activo = true;
        }
        font.setColor(Color.YELLOW);
        glyphLayout.setText(font, "TOP 10 MEJORES PUNTUACIONES");
        font.draw(batch, glyphLayout,(Gdx.graphics.getWidth()-glyphLayout.width)/2, 0.95f*Gdx.graphics.getHeight());
        font.setColor(Color.WHITE);
        if(isRankingLoaded) {
            boolean nuevoRank = false;
            for (int i = 1; i < listaRanking.size(); i++) {
                labelID = new Label(i+"ª", skin);
                labelAlias = new Label(listaRanking.get(i).getNombre(), skin);
                label = new Label(String.valueOf(listaRanking.get(i).getPuntuacion()), skin);
                label.setAlignment(Align.right);
                labelAlias.setAlignment(Align.center);
                labelID.setAlignment(Align.left);
                if ((!nuevoRank) && (Partida.partidaAux.getPuntuacion()==listaRanking.get(i).getPuntuacion())){
                    label.setFontScale(8);
                    labelID.setFontScale(9);
                    labelAlias.setFontScale(5);
                    nuevoRank = true;
                }else{
                    label.setFontScale(4);
                    labelID.setFontScale(5);
                    labelAlias.setFontScale(3);
                }
                table.row();
                table.add(labelID).padRight(50);
                table.add(labelAlias).padLeft(50);
                table.add(label).padLeft(50);
            }
            isRankingLoaded = false;
        }else if(!isRankingLoaded && listaRanking==null){
            font.getData().setScale(2.5f);
            futuro = System.currentTimeMillis();
            if(futuro >= pasado+20000 && pasado!=0) { //20 SEGUNDOS DE ESPERA
                glyphLayout.setText(font, "Conectate a internet para");
                font.draw(batch, glyphLayout, (Gdx.graphics.getWidth()-glyphLayout.width)/2, 0.75f*Gdx.graphics.getHeight());
                glyphLayout.setText(font, "ver el ranking online");
                font.draw(batch, glyphLayout, (Gdx.graphics.getWidth()-glyphLayout.width)/2, 0.7f*Gdx.graphics.getHeight());
            }else{
                glyphLayout.setText(font, "cargando ranking...");
                font.draw(batch, glyphLayout, (Gdx.graphics.getWidth()-glyphLayout.width)/2, 0.75f*Gdx.graphics.getHeight());
            }
        }
        batch.end();
        Gdx.gl.glClearColor(0.4f,0.2f,0.7f,0.7f); //morada
        stage.draw(); // Pintar los actores los botones por encima del background
    }
    private void recogerAlias(final AliasCallback aliasCallback){
        Gdx.input.getTextInput(new Input.TextInputListener() {
            @Override
            public void input(String cadena) {
                alias = cadena;
                aliasCallback.onCallback(cadena);
            }

            @Override
            public void canceled() {
                alias = "\"annonymous\"";
                aliasCallback.onCallback(alias);
            }
        }, "Introduce tu alias", "", " _ _ _ _ _ _ _ _");
    }
}
