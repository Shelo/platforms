package cl.jara.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Game implements Screen {
	public static Game instance;

	public static final float BORDER_TOLERANCE = 50;
	public static final float GRAVITY = -9.8f;

	OrthographicCamera camera;
	ShapeRenderer shape;
	SpriteBatch batch;
	float score;
	Ball ball;

	public PlatformSystem platformSystem;

	public Game() {
		instance = this;

		camera	= new OrthographicCamera();
		shape 	= new ShapeRenderer();
		batch	= new SpriteBatch();

		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// crear pelota.
		ball = new Ball(View.width / 2, View.height - 100);

		// inicializar sistemas.
		platformSystem = new PlatformSystem();

		Input.reset();

		// establecer configuraciones.
		Gdx.input.setInputProcessor(Input.instance);

		ParticleSystem.clear();
		AchievementManager.reset();

		Gdx.gl.glClearColor(0, 0, 0, 1);
	}

	@Override
	public void render(float delta) {
		// actualizar todo el contenido primero.
		update(delta);

		// limpiar buffers.
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shape.setColor(Main.getCurrentColor());
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.rect(0, 0, View.width, View.height);
		shape.end();

		BitmapFont.TextBounds bounds = Assets.gameScore.getBounds((int) score + "");
		float height 	= bounds.height;
		float width 	= bounds.width;

		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.setColor(0, 0, 0, 0.25f);

		shape.circle(View.width / 2, View.height / 2, View.width / 2 - 50);
		if((int) score % 2 == 0)
			shape.arc(View.width / 2, View.height / 2, View.width / 2 - 50, 0, (score - (int) score) * 360);
		else
			shape.arc(View.width / 2, View.height / 2, View.width / 2 - 50, (score - (int) score) * 360,
					(1 - (score - (int) score)) * 360);
		shape.end();

		batch.begin();
		Assets.gameScore.setColor(0, 0, 0, 0.25f);
		Assets.gameScore.draw(batch, (int) score + "", (View.width - width) / 2.0f, (View.height + height) / 2.0f);
		batch.end();

		Gdx.gl.glDisable(GL20.GL_BLEND);

		// siempre se debe iniciar el color en blanco.
		shape.setColor(1, 1, 1, 1);

		ball.render(shape);
		platformSystem.render(shape);
		ParticleSystem.render(shape);
	}

	public void update(float delta) {
		platformSystem.update();
		ball.update(platformSystem.platforms);

		score += delta;

		ParticleSystem.update(platformSystem.platforms);
		Input.update();

		if(ball.y < - Game.BORDER_TOLERANCE || ball.isDeath() || ball.y > View.height + Game.BORDER_TOLERANCE) {
			if(ball.platformsTouched == 0)
				AchievementManager.instantGameOver();

			Main.instance.toGameOver((int) score);
		}
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, 480, (float) height / width * 480);
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
}
