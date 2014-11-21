package cl.jara.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Menu implements Screen, InputProcessor {
	private static final String CAPTION = "Bouncer";
	private static final String PLAY = "PLAY";

	private static OrthographicCamera camera;
	private static ShapeRenderer shape;
	private static SpriteBatch batch;

	private BitmapFont.TextBounds buttonBounds;
	private float titleWidth;
	private float playHeight;
	private float playWidth;

	SubmenuScreen screen;

	public Menu() {
		camera = new OrthographicCamera();
		shape = new ShapeRenderer();
		batch = new SpriteBatch();
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		buttonBounds 	= Assets.uilittle.getBounds(Objectives.TITLE);
		playHeight 		= Assets.uititle.getBounds(PLAY).height;
		titleWidth 		= Assets.uititle.getBounds(CAPTION).width;
		playWidth 		= Assets.uititle.getBounds(PLAY).width;

		Gdx.input.setInputProcessor(this);
		Gdx.gl.glClearColor(0, 0, 0, 1);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.setColor(Main.getCurrentColor());
		shape.rect(0, 0, View.width, View.height);
		shape.end();

		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.setColor(Color.WHITE);
		shape.arc(View.width / 2, View.height / 2, View.width / 2 - 20, Main.arcRadStart, 90);
		shape.arc(View.width / 2, View.height / 2, View.width / 2 - 20, Main.arcRadStart + 180, 90);

		shape.setColor(Main.getDrawColor());
		shape.circle(View.width / 2, View.height / 2, View.width / 2 - 50);

		// dibujar botones.
		shape.rect(0, View.height, View.width / 2, buttonBounds.height - 40);

		shape.setColor(1, 1, 1, 1);
		shape.rect(View.width / 2, View.height, View.width / 2, buttonBounds.height - 40);
		shape.end();

		batch.begin();
		Assets.uititle.draw(batch, CAPTION, (View.width - titleWidth) / 2.0f, 50);
		Assets.uititle.draw(batch, PLAY, (View.width - playWidth) / 2.0f, View.height / 2 + playHeight / 2);

		Assets.uilittle.setColor(Color.WHITE);
		Assets.uilittle.draw(batch, Achievements.TITLE, 20, View.height + buttonBounds.height - 20);

		Assets.uilittle.setColor(Main.getDrawColor());
		Assets.uilittle.draw(batch, Objectives.TITLE, View.width - buttonBounds.width + 20,
				View.height + buttonBounds.height - 20);
		batch.end();

		if(screen != null) {
			shape.setColor(Color.WHITE);
			screen.animate();
			screen.update();
			screen.render(batch, shape);
		}
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(true, 480, (float) height / width * 480);
		shape.setProjectionMatrix(camera.combined);
		batch.setProjectionMatrix(camera.combined);

		View.height = (float) height / width * 480;
		View.width 	= 480;
	}

	public void hideScreen() {
		screen = null;
	}

	public void showScreen(SubmenuScreen screen) {
		Gdx.input.setInputProcessor(screen);
		this.screen = screen;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenX = (int) (View.width / Gdx.graphics.getWidth() * screenX);
		screenY = (int) (View.height / Gdx.graphics.getHeight() * screenY);

		screenY = (int) View.height - screenY;

		if(screenX > 50 && screenX < View.width - 50 && screenY > View.height / 2 - View.width / 2 + 50 &&
				screenY < View.height / 2 + View.width / 2 - 50) {
			Main.instance.toGame();
		} else if(screenY < - buttonBounds.height + 40) {
			if(screenX < View.width / 2) {
				showScreen(new Achievements());
			}
		}

		System.out.println(screenX < View.width / 2);

		return false;
	}

	@Override public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
	@Override public boolean mouseMoved(int screenX, int screenY) { return false; }
	@Override public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }
	@Override public boolean scrolled(int amount) { return false; }
	@Override public boolean keyTyped(char character) { return false; }
	@Override public boolean keyDown(int keycode) { return false; }
	@Override public boolean keyUp(int keycode) { return false; }

	@Override public void dispose() { }
	@Override public void resume() { }
	@Override public void pause() { }
	@Override public void show() { }
	@Override public void hide() { }
}
