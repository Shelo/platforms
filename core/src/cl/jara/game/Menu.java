package cl.jara.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Menu implements Screen {
	private static OrthographicCamera camera;
	private static ShapeRenderer shape;
	private static SpriteBatch batch;

	public Menu() {
		camera = new OrthographicCamera();
		shape = new ShapeRenderer();
		batch = new SpriteBatch();

		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		Gdx.gl.glClearColor(0.23828125f, 0.34765625f, 0.66796875f, 1);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(true, 480, (float) height / width * 480);
		shape.setProjectionMatrix(camera.combined);

		View.height = (float) height / width * 480;
		View.width 	= 480;
	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
}
