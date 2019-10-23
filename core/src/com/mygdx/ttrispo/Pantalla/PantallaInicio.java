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
    private TextButton retry;
    private Skin skin;

    public PantallaInicio (final MyGdxGame game) {
        super(game);

        skin = new Skin(Gdx.files.internal("skins/default/skin/uiskin.json"));
        retry = new TextButton("Retry", skin);

        retry.setSize(300,100);
        retry.setPosition(Gdx.graphics.getWidth()/2.65f, Gdx.graphics.getHeight()/2);
        stage.addActor(retry);

        retry.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.partida);
            }
        });
    }

    @Override
    public void show() {
        super.show();
    }
    @Override
    public void hide() { //usamos dispose porque si cambiamos muchas veces de pantalla
        super.hide();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f,0.5f,0.8f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }


}
