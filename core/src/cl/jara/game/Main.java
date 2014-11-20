package cl.jara.game;

public class Main extends com.badlogic.gdx.Game {
	public static Main instance;

	@Override
	public void create() {
		instance = this;
		toGame();
	}

	public void toGame() {
		setScreen(new Game());
	}

	public void toMenu() {
		setScreen(new Menu());
	}
}
