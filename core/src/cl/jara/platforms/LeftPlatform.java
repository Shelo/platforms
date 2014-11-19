package cl.jara.platforms;

import cl.jara.game.Ball;
import cl.jara.game.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class LeftPlatform extends Platform {
	float indicatorX = 0;
	float indicatorMargin = 3;

	public LeftPlatform(float x, float y) {
		super(x, y);
	}

	@Override
	public void onCollisionEnter(Ball ball, float ballX) {
		ball.freeze();
	}

	@Override
	public void onCollisionStay(Ball ball, float ballX) {
		ball.vx = - 300;
	}

	@Override
	public void onCollisionExit(Ball ball) {
		ball.unFreeze();
		solid = false;
	}

	@Override
	public void render(ShapeRenderer shape) {
		shape.setColor(Color.WHITE);
		shape.rect(x, y, getWidth(), getHeight());

		shape.setColor(Game.getDrawColor());
		shape.rect(x + indicatorX, y + indicatorMargin, getHeight(), getHeight() - indicatorMargin * 2);
	}

	@Override
	public void update() {
		simpleMove();

		indicatorX -= 2;
		if(indicatorX <= 0)
			indicatorX = getWidth() - getHeight();
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
		return new LeftPlatform(x, y);
	}
}
