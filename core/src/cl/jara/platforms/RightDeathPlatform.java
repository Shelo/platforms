package cl.jara.platforms;

import cl.jara.game.Ball;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RightDeathPlatform extends Platform {
	public RightDeathPlatform(float x, float y) {
		super(x, y);
	}

	@Override
	public void onCollisionEnter(Ball ball, float ballX) {
		if(ballX < getWidth() / 2)
			ball.bounce();
	}

	@Override
	public void onCollisionStay(Ball ball, float ballX) {

	}

	@Override
	public void onCollisionExit(Ball ball) {

	}

	@Override
	public void render(ShapeRenderer shape) {
		shape.setColor(Color.WHITE);
		shape.rect(x, y, getWidth() / 2, getHeight());

		shape.setColor(Color.BLACK);
		shape.rect(x + getWidth() / 2, y, getWidth() / 2, getHeight());
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
		return new LeftDeathPlatform(x, y);
	}
}
