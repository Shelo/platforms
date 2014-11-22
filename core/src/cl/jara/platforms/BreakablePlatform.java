package cl.jara.platforms;

import cl.jara.game.Ball;
import cl.jara.game.Game;
import cl.jara.game.Time;
import cl.jara.game.View;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class BreakablePlatform extends Platform {
	private static final int MIDDLE_MEDIUM = 4;
	public float width = 200;
	public int factor;

	public BreakablePlatform(float x, float y) {
		super(x, y);
	}

	@Override
	public void onCollisionEnter(Ball ball, float ballX) {
		ball.bounce();

		if(factor == 0) {
			factor = -1;
			BreakablePlatform newPlatform = new BreakablePlatform(x + width / 2 + MIDDLE_MEDIUM, y);
			newPlatform.factor = 1;
			newPlatform.width = width / 2;
			Game.instance.platformSystem.addSafely(newPlatform);
			width /= 2;
		}
	}

	@Override
	public void render(ShapeRenderer shape) {
		shape.setColor(Color.WHITE);

		if(factor == 0) {
			shape.rect(x, y, getWidth() / 2 - MIDDLE_MEDIUM, getHeight());
			shape.rect(x + getWidth() / 2 + MIDDLE_MEDIUM * 2, y, getWidth() / 2 - MIDDLE_MEDIUM, getHeight());
		} else
			shape.rect(x, y, getWidth() - MIDDLE_MEDIUM, getHeight());
	}

	@Override
	public void update() {
		simpleMove();
		x += factor * Time.delta * 100;
		x = MathUtils.clamp(x, 0, View.width - getWidth());
	}

	@Override
	public float getHeight() {
		return 15;
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public Platform copy(float x, float y) {
		return new BreakablePlatform(x, y);
	}

	@Override public void onCollisionStay(Ball ball, float ballX) { }
	@Override public void onCollisionExit(Ball ball) { }
}
