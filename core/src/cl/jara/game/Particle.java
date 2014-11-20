package cl.jara.game;

import com.badlogic.gdx.math.MathUtils;

public class Particle {
	public static final float MIN_BOUNCE = 400;
	public static final float BOUNCINESS = 1;

	public float vx;
	public float vy;
	public float x;
	public float y;

	public float lifeTime;

	public Particle(float x, float y, float vx, float vy) {
		this.vx = vx;
		this.vy = vy;
		this.x	= x;
		this.y	= y;

		lifeTime = MathUtils.random(4.0f, 8.0f);
	}

	public Particle reset(float x, float y, float vx, float vy) {
		this.vx = vx;
		this.vy = vy;
		this.x	= x;
		this.y	= y;

		lifeTime = MathUtils.random(4.0f, 8.0f);

		return this;
	}

	public void update() {
		vy += Game.GRAVITY;

		x += vx * Time.delta;
		y += vy * Time.delta;

		lifeTime -= Time.delta;
	}
}
