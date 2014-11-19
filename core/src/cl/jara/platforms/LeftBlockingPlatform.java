package cl.jara.platforms;

import cl.jara.game.Ball;
import cl.jara.game.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class LeftBlockingPlatform extends Platform {
	private static final float BLOCKING_HEIGHT 	= 25;
	private static final float MARGIN 			= 5;

	public LeftBlockingPlatform(float x, float y) {
		super(x, y);
	}

	@Override
	public void onCollisionStay(Ball ball, float ballX) {
		if(ballX - Ball.RADIUS <= getHeight() && ball.vx < 0) {
			ball.x = x + getHeight() + Ball.RADIUS;
			ball.vx = 0;
		}
	}

	@Override
	public void render(ShapeRenderer shape) {
		shape.setColor(Color.WHITE);
		shape.rect(x, y, getWidth(), getHeight());
		shape.rect(x, y + getHeight(), getHeight(), BLOCKING_HEIGHT);

		shape.setColor(Game.getDrawColor());
		shape.rect(x + MARGIN, y + MARGIN, getWidth() - MARGIN * 2, getHeight() - MARGIN * 2);
		shape.rect(x + MARGIN, y + getHeight() - MARGIN, getHeight() - MARGIN * 2, BLOCKING_HEIGHT);
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
		return new LeftBlockingPlatform(x ,y);
	}

	@Override public void onCollisionEnter(Ball ball, float ballX) { }
	@Override public void onCollisionExit(Ball ball) { }
}
