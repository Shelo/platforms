package cl.jara.game;

import com.badlogic.gdx.InputProcessor;

public class Input implements InputProcessor {
	public static Input instance;
	public static float horizontalAxis = 0;

	public static boolean rightButtonDown;
	public static boolean leftButtonDown;
	public static boolean passThrowButton;

	private float scrollInitY;

	// crear singleton.
	static { instance = new Input(); }

	public static void update() {
		if(rightButtonDown)
			horizontalAxis += (1 - horizontalAxis) * 0.1f;
		else if(leftButtonDown)
			horizontalAxis += ((- 1) - horizontalAxis) * 0.1f;
		else
			horizontalAxis += (0 - horizontalAxis) * 0.1f;

		passThrowButton = false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenY = (int) View.height - screenY;
		scrollInitY = screenY;

		if(screenX > View.width / 2)
			rightButtonDown = true;
		else
			leftButtonDown = true;

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenY = (int) View.height - screenY;

		if(screenX > View.width / 2)
			rightButtonDown = false;
		else
			leftButtonDown = false;

		if(scrollInitY - screenY > 50)
			passThrowButton = true;

		scrollInitY = -1;

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

	@Override public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
	@Override public boolean mouseMoved(int screenX, int screenY) { return false; }
	@Override public boolean keyTyped(char character) { return false; }
	@Override public boolean scrolled(int amount) { return false; }
}
