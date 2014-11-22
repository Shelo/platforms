package cl.jara.game;

public interface ActionResolver {
	public boolean getSignedIn();
	public void login();
	public void submitScore(int score);
	public void unlockAchievement(String code);
	public void getLeaderboard();
	public void getAchievements();
	public void showAchievements();
	public void incrementAchievement(String code, int steps);
	public void showLeaderboards();
}