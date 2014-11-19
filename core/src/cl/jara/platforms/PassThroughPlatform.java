package cl.jara.platforms;

import cl.jara.game.Ball;
import cl.jara.game.Game;
import cl.jara.game.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PassThroughPlatform extends Platform {
	public PassThroughPlatform(float x, float y) {
		super(x, y);
	}

	@Override
	public void render(ShapeRenderer shape) {
		shape.setColor(Game.getDrawColor());
		shape.rect(x, y, getWidth(), getHeight());
	}

	@Override
	public void update() {
		simpleMove();
	}

	@Override
	public void onCollisionStay(Ball ball, float ballX) {
		if(Input.passThrowButton)
			solid = false;
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
		return new PassThroughPlatform(x, y);
	}

	@Override public void onCollisionEnter(Ball ball, float ballX) { }
	@Override public void onCollisionExit(Ball ball) { }
}
