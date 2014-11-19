package cl.jara.game;

import cl.jara.platforms.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Random;

public class PlatformSystem {
	public static final float APPEARING_FREQUENCY = 0.5f;

	ArrayList<Platform> platforms = new ArrayList<Platform>();
	Random random = new Random();

	public float appearingFactor = 1;
	public float appearingTimer;

	Platform[] platformsTemplates = {
			new LeftPlatform(0, 0),
			new RightPlatform(0, 0),
			new PassThroughPlatform(0, 0),
			new RegularPlatform(0, 0),
			new CentricDeathPlatform(0, 0),
			new ExtremesDeathPlatform(0, 0),
			new LeftDeathPlatform(0, 0),
			new RightDeathPlatform(0, 0),
	};

	public PlatformSystem() {

	}

	public void update() {
		if(appearingTimer <= 0) {
			spawn();
			appearingTimer = APPEARING_FREQUENCY * appearingFactor;
		} else
			appearingTimer -= Time.delta;

		for(int i = platforms.size() - 1; i > - 1; i--) {
			Platform platform = platforms.get(i);
			platform.update();

			if(platform.y > View.height)
				platforms.remove(i);
		}
	}

	public void render(ShapeRenderer shape) {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		for(Platform platform : platforms)
			platform.render(shape);

		shape.end();
	}

	public void spawn() {
		int index = random.nextInt(platformsTemplates.length);

		float x = random.nextFloat() * (View.width - platformsTemplates[index].getWidth());
		float y = 0;

		platforms.add(platformsTemplates[index].copy(x, y));
	}
}
