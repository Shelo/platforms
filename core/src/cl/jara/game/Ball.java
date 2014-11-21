package cl.jara.game;

import cl.jara.platforms.Platform;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Ball {
	public static final float BOUNCE_HEIGHT = 500;
	public static final float RADIUS = 10;
	public static final float SPEED = 350;

	public float vx;
	public float vy;
	public float x;
	public float y;

	Platform lastCollide;
	boolean froze;
	boolean death;

	public Ball(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void update(ArrayList<Platform> platforms) {

		// actualizar movimiento vertical.

		vy += Game.GRAVITY;
		y += vy * Time.delta;

		// actualizar movimiento horizontal.
		if (!froze)
			vx = Input.horizontalAxis * SPEED;

		// revisar colision con plataformas.
		for (Platform platform : platforms)
			if (Intersector.intersectRectangles(
					new Rectangle(x - RADIUS, y - RADIUS, RADIUS * 2, RADIUS),
					new Rectangle(platform.x, platform.y, platform.getWidth(), platform.getHeight()),
					new Rectangle())
					) {

				// si la plataforma no es solida simplemente terminamos.
				if(!platform.isSolid())
					continue;

				// encontrar por cual lado se chocó.
				if (x >= platform.x + platform.getWidth()) {
					// derecha.
					x = platform.x + platform.getWidth() + RADIUS;
					vx = 0;
				} else if (x <= platform.x) {
					// izquierda.
					x = platform.x - RADIUS;
					vx = 0;
				} else {
					y = platform.y + platform.getHeight() + RADIUS;
					vy = 0;
				}

				if(platform.isPlatform()) {
					// activar triggers.
					if (lastCollide == platform)
						// se reconoce la misma plataforma anterior, le avisamos que seguimos sobre ella.
						lastCollide.onCollisionStay(this, x - platform.x);
					else {
						// son distintas plataformas.
						if (lastCollide != null)
							// se avisa a la plataforma anterior que salimos.
							lastCollide.onCollisionExit(this);

						// se avisa a la nueva plataforma que entramos.
						lastCollide = platform;
						lastCollide.onCollisionEnter(this, x - platform.x);
						if(lastCollide.isParticleSpawner())
							ParticleSystem.genCone(x, y, 15);
					}
				}
			} else {
				// avisamos que ya salimos de la plataforma si estabamos en ella y no colisionan.
				if (lastCollide == platform) {
					lastCollide.onCollisionExit(this);
					lastCollide = null;
				}
			}

		x += vx * Time.delta;

		if(x < Ball.RADIUS)
			x = Ball.RADIUS;

		if(x > View.width - Ball.RADIUS)
			x = View.width - Ball.RADIUS;
	}

	public void freeze() {
		froze = true;
	}

	public void unFreeze() {
		froze = false;
	}

	/**
	 * Make the ball jump. Caution: the event onCollisionStay will not be triggered.
	 */
	public void bounce() {
		vy = BOUNCE_HEIGHT;
	}

	public void render(ShapeRenderer shape) {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.circle(x, y, RADIUS);
		shape.end();
	}

	public void kill() {
		death = true;
	}

	public boolean isDeath() {
		return death;
	}
}
