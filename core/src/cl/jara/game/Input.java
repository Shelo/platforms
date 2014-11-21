package cl.jara.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class Input implements InputProcessor {
	public static Input instance;
	public static float horizontalAxis = 0;

	public static boolean rightButtonDown;
	public static boolean leftButtonDown;
	public static boolean passThrowButton;

	private static float touchInitY;
	private static float touchDraggedY;

	// crear singleton.
	static { instance = new Input(); }

	public static void update() {
		if(rightButtonDown)
			horizontalAxis = 1;

		if(leftButtonDown)
			horizontalAxis = -1;

		if(!leftButtonDown && !rightButtonDown)
			horizontalAxis = 0;

		if(touchInitY - touchDraggedY > 25)
			horizontalAxis = 0;

		passThrowButton = false;
	}

	public static void reset() {
		leftButtonDown = false;
		rightButtonDown = false;
		passThrowButton = false;
		touchInitY = 0;
		touchDraggedY = 0;
		horizontalAxis = 0;
		instance = new Input();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenX = (int) (View.width / Gdx.graphics.getWidth() * screenX);
		screenY = (int) (View.height / Gdx.graphics.getHeight() * screenY);

		screenY = (int) View.height - screenY;

		touchDraggedY 	= screenY;
		touchInitY 		= screenY;

		if(screenX > View.width / 2)
			rightButtonDown = true;
		else
			leftButtonDown = true;

		if(rightButtonDown && leftButtonDown) {
			passThrowButton = true;
			rightButtonDown = false;
			leftButtonDown = false;
		}

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenX = (int) (View.width / Gdx.graphics.getWidth() * screenX);
		screenY = (int) (View.height / Gdx.graphics.getHeight() * screenY);

		screenY = (int) View.height - screenY;

		if(screenX > View.width / 2) {
			rightButtonDown = false;
		} else {
			leftButtonDown = false;
		}

		if(touchInitY - screenY > 25) {
			passThrowButton = true;
			touchDraggedY = 0;
			touchInitY = 0;
		}

		touchInitY = -1;
		return false;
	}

	@Override public boolean touchDragged(int screenX, int screenY, int pointer) {
		screenX = (int) (View.width / Gdx.graphics.getWidth() * screenX);
		screenY = (int) (View.height / Gdx.graphics.getHeight() * screenY);

		touchDraggedY = View.height - screenY;

		if(screenX > View.width / 2) {
			rightButtonDown = true;
			leftButtonDown = false;
		} else {
			leftButtonDown = true;
			rightButtonDown = false;
		}

		return false;
	}

	@Override public boolean keyDown(int keycode) {
		if(keycode == com.badlogic.gdx.Input.Keys.A)
			leftButtonDown = true;
		else if(keycode == com.badlogic.gdx.Input.Keys.D)
			rightButtonDown = true;
		else if(keycode == com.badlogic.gdx.Input.Keys.S)
			passThrowButton = true;

		return false;
	}

	@Override public boolean keyUp(int keycode) {
		if(keycode == com.badlogic.gdx.Input.Keys.A)
			leftButtonDown = false;
		else if(keycode == com.badlogic.gdx.Input.Keys.D)
			rightButtonDown = false;
		else if(keycode == com.badlogic.gdx.Input.Keys.S)
			passThrowButton = false;

		return false;
	}

	@Override public boolean mouseMoved(int screenX, int screenY) { return false; }
	@Override public boolean keyTyped(char character) { return false; }
	@Override public boolean scrolled(int amount) { return false; }
}
