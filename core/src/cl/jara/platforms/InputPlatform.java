package cl.jara.platforms;

import cl.jara.game.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

/**
 * Experimental.
 */
public class InputPlatform extends Platform {
	private static final int SCREEN_MARGIN = 20;
	private static final int MARGIN = 4;
	private boolean playerOnTop;

	private boolean touched;
	private int touchPointX;
	private int touchPointY;
	private int touchPointC;
	private int touchPointR;

	public InputPlatform(float y) {
		super(0, y);

		touchPointX = (int) MathUtils.random(SCREEN_MARGIN, View.width - SCREEN_MARGIN * 2);
		touchPointY = (int) MathUtils.random(SCREEN_MARGIN, View.height - SCREEN_MARGIN * 2);
		touchPointR = 50;
		touchPointC	= MathUtils.random(5, 10);
	}

	@Override
	public void onCollisionEnter(Ball ball, float ballX) {
		playerOnTop = true;
		ball.vx = 0;
		ball.freeze();
	}

	@Override
	public void onCollisionExit(Ball ball) {
		playerOnTop = false;
		ball.unFreeze();
	}

	@Override
	public void render(ShapeRenderer shape) {
		shape.setColor(Color.WHITE);
		shape.rect(x, y, getWidth(), getHeight());

		shape.setColor(Main.getDrawColor());
		shape.rect(x + MARGIN, y + MARGIN, getWidth() - MARGIN * 2, getHeight() - MARGIN * 2);

		if(playerOnTop)
			draw(shape);
	}

	private void draw(ShapeRenderer shape) {
		shape.setColor(Main.getDrawColor());
		shape.circle(touchPointX, touchPointY, touchPointR);

		shape.setColor(Color.BLACK);
		shape.circle(touchPointX, touchPointY, touchPointR - touchPointC * 3);
	}

	@Override
	public void update() {
		simpleMove();

		if(playerOnTop)
			Time.factor = 0.5f;

		if(Input.getTouchX() > touchPointX - touchPointR && Input.getTouchX() < touchPointX + touchPointR) {
			if(Input.getTouchY() > touchPointY - touchPointR && Input.getTouchY() < touchPointY + touchPointR) {
				touchPointC--;
			}
		}

		if(touchPointC <= 0) {
			playerOnTop = false;
			solid = false;
		}
	}

	@Override
	public float getHeight() {
		return 15;
	}

	@Override
	public float getWidth() {
		return View.width;
	}

	@Override
	public Platform copy(float x, float y) {
		return new InputPlatform(y);
	}

	@Override public void onCollisionStay(Ball ball, float ballX) { }
}
