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
	public boolean getSignedInGPGS() {
		return gameHelper.isSignedIn();
	}

	@Override
	public void loginGPGS() {
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
	public void submitScoreGPGS(int score) {

	}

	@Override
	public void unlockAchievementGPGS(String achievementId) {
		Games.Achievements.unlock(gameHelper.getApiClient(), achievementId);
	}

	@Override
	public void getLeaderboardGPGS() {

	}

	@Override
	public void getAchievementsGPGS() {

	}

	@Override
	public void showAchievementsGPGS() {
		startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), 1);
	}

	@Override public void onSignInFailed() { }
	@Override public void onSignInSucceeded() { }
}