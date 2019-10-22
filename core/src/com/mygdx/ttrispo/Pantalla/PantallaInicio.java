package com.mygdx.ttrispo.Pantalla;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.graphics.GL20;
        import com.badlogic.gdx.scenes.scene2d.Stage;
        import com.badlogic.gdx.utils.viewport.FitViewport;
        import com.mygdx.ttrispo.MyGdxGame;

public class PantallaInicio extends PantallaBase{
    private Stage stage;

    public PantallaInicio (MyGdxGame game) {
        super(game);
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f,0.5f,0.8f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void hide() { //usamos dispose porque si cambiamos muchas veces de pantalla
        stage.dispose();     // se haría everytime un show, si abandonamos la pantalla = DISPOSE
    }
}
