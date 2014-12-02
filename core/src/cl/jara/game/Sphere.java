package cl.jara.game;

public class Sphere {
	public static final float BASE_SPEED = 250;
	public static final int SIZE = 25;
	public static final int OSCILATION_RADIUS = 2;

	public int x;
	public int y;
	public int o;

	public Sphere(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void update() {
		y += (int) (Math.cos(Time.time * 5f) * OSCILATION_RADIUS);
		y += BASE_SPEED * Time.delta;
	}
}
