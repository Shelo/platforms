package cl.jara.game;

public class AchievementManager {
	private static final int BOUNCES_TO_PUSH = 5;
	private static final int PASSTRH_TO_PUSH = 3;

	private static String[] bounceAch = {"CgkI6JzeotQdEAIQAQ", "CgkI6JzeotQdEAIQCg", };
	private static String[] passThroughAch = {"CgkI6JzeotQdEAIQCA", "CgkI6JzeotQdEAIQCw", };
	private static String[] instantGameOver = {"CgkI6JzeotQdEAIQDA", };

	private static int passThroughs;
	private static int bounces;

	public static void reset() {
		passThroughs 	= 0;
		bounces 		= 0;
	}

	/**
	 * Incrementa el contador de bounces y los envia al servidor de Google Play por batches.
	 */
	public static void bounce() {
		if(!Main.instance.actionResolver.getSignedIn())
			return;

		bounces += 1;

		if(bounces >= BOUNCES_TO_PUSH) {
			for(String achievement : bounceAch)
				Main.instance.actionResolver.incrementAchievement(achievement, bounces);
			bounces = 0;
		}
	}

	/**
	 * Incrementa el contador de bounces y los envia al servidor de Google Play por batches.
	 */
	public static void passThrough() {
		if(!Main.instance.actionResolver.getSignedIn())
			return;

		passThroughs += 1;

		if(passThroughs >= PASSTRH_TO_PUSH) {
			for(String achievement : passThroughAch)
				Main.instance.actionResolver.incrementAchievement(achievement, passThroughs);

			passThroughs = 0;
		}
	}

	/**
	 * Incrementa el contador de bounces y los envia al servidor de Google Play por batches.
	 */
	public static void instantGameOver() {
		if(!Main.instance.actionResolver.getSignedIn())
			return;

		for(String achievement : instantGameOver)
			Main.instance.actionResolver.incrementAchievement(achievement, 1);
	}
}
