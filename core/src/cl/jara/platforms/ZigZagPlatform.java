package cl.jara.platforms;

import cl.jara.game.Ball;
import cl.jara.game.Time;
import cl.jara.game.View;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class ZigZagPlatform extends Platform {
	private static final float SPEED = 150;
	int direction = 1;

	public ZigZagPlatform(float x, float y) {
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
		x += SPEED * direction * Time.delta;

		if(x < 0)
			direction *= -1;

		if(x > View.width - getWidth())
			direction *= -1;

		x = MathUtils.clamp(x, 0, View.width - getWidth());
	}

	@Override
	public float getHeight() {
		return 15;
	}

	@Override
	public float getWidth() {
		return 240;
	}

	@Override
	public Platform copy(float x, float y) {
		return new ZigZagPlatform(x, y);
	}

	@Override public void onCollisionStay(Ball ball, float ballX) { }
	@Override public void onCollisionExit(Ball ball) { }
}
