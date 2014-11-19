package cl.jara.platforms;

import cl.jara.game.Ball;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RegularPlatform extends Platform {
	public RegularPlatform(float x, float y) {
		super(x, y);
	}

	@Override
	public void onCollisionEnter(Ball ball, float ballX) {
		ball.bounce();
	}

	@Override
	public void render(ShapeRenderer shape) {
		shape.setColor(Color.WHITE);
		shape.rect(x, y, getWidth(), getHeight());
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
		return new RegularPlatform(x, y);
	}

	@Override public void onCollisionStay(Ball ball, float ballX) { }
	@Override public void onCollisionExit(Ball ball) { }
}
