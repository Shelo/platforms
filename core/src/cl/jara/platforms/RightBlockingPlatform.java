package cl.jara.platforms;

import cl.jara.game.Ball;
import cl.jara.game.Game;
import cl.jara.game.Main;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RightBlockingPlatform extends Platform {
	private static final float BLOCKING_HEIGHT 	= 25;
	private static final float MARGIN 			= 5;

	private class BlockAddon extends Platform {
		public BlockAddon(float x, float y) {
			super(x, y);
			spawnParticles = false;
			platform = false;
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
			return 25;
		}

		@Override
		public float getWidth() {
			return 15;
		}

		@Override public void onCollisionEnter(Ball ball, float ballX) { }
		@Override public void onCollisionStay(Ball ball, float ballX) { }
		@Override public void onCollisionExit(Ball ball) { }
		@Override public Platform copy(float x, float y) { return null; }
	}

	public RightBlockingPlatform(float x, float y) {
		super(x, y);
	}

	@Override
	public void onCreate() {
		Game.instance.platformSystem.add(new BlockAddon(x + getWidth() - getHeight(), y + getHeight()));
	}

	@Override
	public void render(ShapeRenderer shape) {
		shape.setColor(Color.WHITE);
		shape.rect(x, y, getWidth(), getHeight());

		shape.setColor(Main.getDrawColor());
		shape.rect(x + MARGIN, y + MARGIN, getWidth() - MARGIN * 2, getHeight() - MARGIN * 2);
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
		return new RightBlockingPlatform(x, y);
	}

	@Override public void onCollisionStay(Ball ball, float ballX) { }
	@Override public void onCollisionEnter(Ball ball, float ballX) { }
	@Override public void onCollisionExit(Ball ball) { }
}
