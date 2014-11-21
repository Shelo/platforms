package cl.jara.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameOver implements Screen, InputProcessor {
	private static final String GAME_OVER = "Is Over";
	private static final String SCORE = "SCORE";

	private static OrthographicCamera camera;
	private static ShapeRenderer shape;
	private static SpriteBatch batch;

	private float titleWidth;
	private float scoreTextHeight;
	private float scoreHeight;
	private float scoreWidth;
	private float scoreTextWidth;
	private int score;

	public GameOver(int score) {
		this.score = score;

		camera = new OrthographicCamera();
		shape = new ShapeRenderer();
		batch = new SpriteBatch();

		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		scoreTextHeight = Assets.uititle.getBounds(SCORE).height;
		titleWidth 	= Assets.uititle.getBounds(GAME_OVER).width;
		scoreWidth 	= Assets.gameScoreI.getBounds(score + "").width;
		scoreHeight	= Assets.gameScoreI.getBounds(score + "").height;
		scoreTextWidth = Assets.uifont.getBounds(SCORE).width;

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
		shape.end();

		batch.begin();
		Assets.uititle.draw(batch, GAME_OVER, (View.width - titleWidth) / 2.0f, 50);
		Assets.gameScoreI.draw(batch, score + "", (View.width - scoreWidth) / 2.0f, View.height / 2 + scoreHeight / 2);
		Assets.uifont.draw(batch, SCORE, (View.width - scoreTextWidth) / 2.0f, View.height / 2 + View.width / 2 + 50);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(true, 480, (float) height / width * 480);
		shape.setProjectionMatrix(camera.combined);
		batch.setProjectionMatrix(camera.combined);

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

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenX = (int) (View.width / Gdx.graphics.getWidth() * screenX);
		screenY = (int) (View.height / Gdx.graphics.getHeight() * screenY);

		screenY = (int) View.height - screenY;

		if(screenX > 50 && screenX < View.width - 50) {
			if(screenY > View.height / 2 - View.width / 2 + 50 && screenY < View.height / 2 + View.width / 2 - 50) {
				Main.instance.toMenu();
			}
		}

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
