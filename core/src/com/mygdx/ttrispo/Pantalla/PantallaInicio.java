package com.mygdx.ttrispo.Pantalla;
 import com.badlogic.gdx.Gdx;
 import com.badlogic.gdx.graphics.GL20;
 import com.badlogic.gdx.scenes.scene2d.Actor;
 import com.badlogic.gdx.scenes.scene2d.Stage;
 import com.badlogic.gdx.scenes.scene2d.ui.Skin;
 import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
 import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
 import com.badlogic.gdx.utils.viewport.FitViewport;
 import com.mygdx.ttrispo.MyGdxGame;

public class PantallaInicio extends PantallaBase{
    private Stage stage;
    private Skin skin;
    private TextButton start;

    public PantallaInicio (final MyGdxGame game) {
        super(game);
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        skin = new Skin(Gdx.files.internal("skins/default/skin/uiskin.json"));
        start = new TextButton("Start", skin);

        start.setSize(300, 100);
        start.setPosition(Gdx.graphics.getWidth() / 2.65f, Gdx.graphics.getHeight() / 2);
        super.stage.addActor(start);

        start.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.partida);
            }
        });
    }

    @Override
    public void show() {
        super.show();
        //Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() { //usamos dispose porque si cambiamos muchas veces de pantalla
        super.hide();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.4f,0.5f,0.8f,1f); //azul

    }

    @Override
    public void dispose() {
        super.dispose();
    }

}

