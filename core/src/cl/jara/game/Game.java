package cl.jara.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Game implements Screen {
	public static final float COLOR_CHANGE_FREQ = 5;
	public static final float GRAVITY = -9.8f;

	private static Color drawingColor;
	private static Color currentColor;
	private static Color[] colors = {
			new Color(0.23828125f, 0.34765625f, 0.66796875f, 1),	// azul.
			new Color(0.18359375f, 0.30859375f, 0.30859375f, 1),	// dark slate green.
			new Color(0.6953125f, 0.1328125f, 0.01171875f, 1),		// rojo.
			new Color(0.1796875f, 0.54296875f, 0.33984375f, 1),		// sea green.
			new Color(0.25f, 0.25f, 0.25f, 1),						// grey.
			new Color(0.3828125f, 0.27734375f, 0.1953125f, 1),		// brown.
			new Color(0.703125f, 0.8203125f, 0.88671875f, 1),		// celeste.
	};

	float colorChangeTimer;
	int nextColorIndex;
	int colorIndex;

	OrthographicCamera camera;
	PlatformSystem platformSystem;
	ShapeRenderer shape;
	Ball ball;

	public Game() {
		camera	= new OrthographicCamera();
		shape 	= new ShapeRenderer();

		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// crear pelota.
		ball = new Ball(View.width / 2, View.height - 100);

		// inicializar sistemas.
		platformSystem = new PlatformSystem();

		drawingColor = new Color();

		// establecer variables.
		nextColorIndex 	= MathUtils.random(colors.length - 1);
		colorIndex 		= MathUtils.random(colors.length - 1);

		colorChangeTimer = COLOR_CHANGE_FREQ;

		// establecer configuraciones.
		Gdx.input.setInputProcessor(Input.instance);

		Gdx.gl.glClearColor(0, 0, 0, 1);
	}

	@Override
	public void render(float delta) {
		// actualizar todo el contenido primero.
		update(delta);

		// limpiar buffers.
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shape.setColor(currentColor);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.rect(0, 0, View.width, View.height);
		shape.end();

		// siempre se debe iniciar el color en blanco.
		shape.setColor(1, 1, 1, 1);

		ball.render(shape);
		platformSystem.render(shape);
		ParticleSystem.render(shape);
	}

	public void update(float delta) {
		Time.update(delta);

		platformSystem.update();
		ball.update(platformSystem.platforms);

		if(currentColor == null)
			currentColor = new Color(colors[colorIndex]);

		float vr = (colors[nextColorIndex].r - currentColor.r) * 0.0015f;
		float vg = (colors[nextColorIndex].g - currentColor.g) * 0.0015f;
		float vb = (colors[nextColorIndex].b - currentColor.b) * 0.0015f;

		currentColor.add(vr, vg, vb, 0);

		colorChangeTimer -= Time.delta;
		if(colorChangeTimer <= 0) {
			nextColorIndex = MathUtils.random(colors.length - 1);
			colorChangeTimer = COLOR_CHANGE_FREQ;
		}

		drawingColor.set(currentColor).mul(1.5f);

		ParticleSystem.update(platformSystem.platforms);
		Input.update();
	}

	public static Color getDrawColor() {
		return drawingColor;
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, 480, (float) height / width * 480);
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
