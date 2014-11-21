package cl.jara.platforms;

import cl.jara.game.Ball;
import cl.jara.game.Input;
import cl.jara.game.Main;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class CentricDeathPlatform extends Platform {
	public static float EXTREME_MARGIN = 50;

	public CentricDeathPlatform(float x, float y) {
		super(x, y);
	}

	@Override
	public void onCreate() {

	}

	@Override
	public void onCollisionEnter(Ball ball, float ballX) {
	}

	@Override
	public void onCollisionStay(Ball ball, float ballX) {
		if(ballX > EXTREME_MARGIN && ballX < getWidth() - EXTREME_MARGIN)
			ball.kill();

		if(Input.passThrowButton) {
			if(ballX < EXTREME_MARGIN)
				solid = false;
			else if(ballX > getWidth() - EXTREME_MARGIN)
				solid = false;
		}
	}

	@Override
	public void onCollisionExit(Ball ball) {

	}

	@Override
	public void render(ShapeRenderer shape) {
		shape.setColor(Main.getDrawColor());
		shape.rect(x, y, EXTREME_MARGIN, getHeight());
		shape.rect(x + getWidth(), y, - EXTREME_MARGIN, getHeight());

		shape.setColor(Color.BLACK);
		shape.rect(x + EXTREME_MARGIN, y, getWidth() - EXTREME_MARGIN * 2, getHeight());
	}

	@Override
	public void update() {
		simpleMove();
	}

	@Override
	public float getHeight() {
		return 15;
	}

	@Override
	public float getWidth() {
		return 150;
	}

	@Override
	public Platform copy(float x, float y) {
		return new CentricDeathPlatform(x, y);
	}
}
