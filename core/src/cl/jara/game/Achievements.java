package cl.jara.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Achievements extends SubmenuScreen {
	public static final String TITLE = "Achievements";

	BitmapFont.TextBounds titleBounds;

	private Achievement[] achievements = {
			new Achievement("Achievement", "50 Jumps"),
	};

	public Achievements() {
		super();
	}

	@Override
	public void update() {

	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer shape) {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.setColor(Color.BLACK);
		shape.rect(MARGIN, y + MARGIN, View.width - MARGIN * 2, View.height - MARGIN * 2);

		shape.setColor(Main.getDrawColor());
		shape.rect(INNER_MARGIN, y + INNER_MARGIN, View.width - INNER_MARGIN * 2, View.height - INNER_MARGIN * 2);
		shape.end();

		batch.begin();
		batch.end();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
