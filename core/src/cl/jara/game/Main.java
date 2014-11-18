package cl.jara.game;

import com.badlogic.gdx.Gdx;

public class Main extends com.badlogic.gdx.Game {
	@Override
	public void create () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		setScreen(new Game());
	}
}
