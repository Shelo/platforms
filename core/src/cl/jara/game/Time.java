package cl.jara.game;

public class Time {
	public static float delta;
	public static float time;
	public static float factor = 1;

	public static void update(float delta) {
		Time.delta = delta * factor;
		time += delta;
		factor = 1;
	}
}
