package cl.jara.platforms;

import cl.jara.game.Ball;
import cl.jara.game.Time;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Platform {
	public static final float BASE_SPEED = 250;

	public float x;
	public float y;

	protected boolean spawnParticles = true;
	protected boolean platform = true;
	protected boolean solid = true;

	public Platform(float x, float y) {
		this.x 	= x;
		this.y 	= y;
	}

	public boolean isSolid() {
		return solid;
	}

	public boolean isPlatform() {
		return platform;
	}

	public boolean isParticleSpawner() {
		return spawnParticles;
	}

	public void simpleMove() {
		y += BASE_SPEED * Time.delta;
	}

	public void onCreate() {}

	public abstract void onCollisionEnter(Ball ball, float ballX);
	public abstract void onCollisionStay(Ball ball, float ballX);
	public abstract void onCollisionExit(Ball ball);

	public abstract void render(ShapeRenderer shape);
	public abstract void update();

	public abstract float getHeight();
	public abstract float getWidth();

	public abstract Platform copy(float x, float y);
}
