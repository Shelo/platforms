package cl.jara.game.desktop;

import cl.jara.game.ActionResolver;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import cl.jara.game.Main;

public class DesktopLauncher {
	public static ActionResolver actionResolver = new ActionResolver() {
		@Override public boolean getSignedIn() { return false; }
		@Override public void login() { }
		@Override public void submitScore(int score) { }
		@Override public void unlockAchievement(String code) { }
		@Override public void getLeaderboard() { }
		@Override public void getAchievements() { }
		@Override public void showAchievements() { }
		@Override public void incrementAchievement(String code, int steps) { }
		@Override public void showLeaderboards() { }
	};

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 480;
		config.height = 700;
		new LwjglApplication(new Main(actionResolver), config);
	}
}
