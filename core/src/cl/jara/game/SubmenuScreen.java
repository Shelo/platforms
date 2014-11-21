package cl.jara.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class SubmenuScreen implements InputProcessor {
	protected static int INNER_MARGIN 	= 32;
	protected static int MARGIN 		= 30;

	float _y;
	float y;

	public SubmenuScreen() {
		_y 	= 0;
		y 	= View.height;
	}

	public void animate() {
		y += (_y - y ) * 0.1f;
	}

	public abstract void update();
	public abstract void render(SpriteBatch batch, ShapeRenderer shape);
}
