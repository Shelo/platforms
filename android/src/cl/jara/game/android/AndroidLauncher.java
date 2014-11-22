package cl.jara.game.android;

import android.content.Intent;
import android.os.Bundle;
import cl.jara.game.ActionResolver;
import cl.jara.game.Main;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.Games;

public class AndroidLauncher extends AndroidApplication implements ActionResolver, GameHelper.GameHelperListener {
	GameHelper gameHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Main(this), config);

		gameHelper = new GameHelper(this, 1);
		gameHelper.setup(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		gameHelper.onStart(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		gameHelper.onStop();
	}

	@Override
	public void onActivityResult(int request, int response, Intent data) {
		super.onActivityResult(request, response, data);
		gameHelper.onActivityResult(request, response, data);
	}

	@Override
	public boolean getSignedIn() {
		return gameHelper.isSignedIn();
	}

	@Override
	public void login() {
		try {
			runOnUiThread(new Runnable(){
				public void run() {
					gameHelper.beginUserInitiatedSignIn();
				}
			});
		} catch (final Exception ex) {

		}
	}

	@Override
	public void submitScore(int score) {
		Games.Leaderboards.submitScore(gameHelper.getApiClient(), "CgkI6JzeotQdEAIQCQ", score);
	}

	@Override
	public void unlockAchievement(String achievementId) {
		Games.Achievements.unlock(gameHelper.getApiClient(), achievementId);
	}

	public void incrementAchievement(String code, int steps) {
		Games.Achievements.increment(gameHelper.getApiClient(), code, steps);
	}

	@Override
	public void showLeaderboards() {
		Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), "CgkI6JzeotQdEAIQCQ");
	}

	@Override
	public void getLeaderboard() {

	}

	@Override
	public void getAchievements() {

	}

	@Override
	public void showAchievements() {
		startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), 1);
	}

	@Override public void onSignInFailed() { }
	@Override public void onSignInSucceeded() { }
}