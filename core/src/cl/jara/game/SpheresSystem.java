package cl.jara.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class SpheresSystem {
	float APPEARING_FREQUENCY = 2;
	private ArrayList<Sphere> spheres = new ArrayList<Sphere>();

	float appearingFactor = 1;
	float appearingTimer = APPEARING_FREQUENCY;

	public void update() {
		if(appearingTimer <= 0) {
			spheres.add(new Sphere(MathUtils.random(0, (int) View.width - Sphere.SIZE), 0));
			appearingTimer = APPEARING_FREQUENCY * appearingFactor;
		} else
			appearingTimer -= Time.delta;

		for(int i = spheres.size() - 1; i > - 1; i--) {
			Sphere sphere = spheres.get(i);
			sphere.update();

			if(sphere.y > View.height + Game.BORDER_TOLERANCE)
				spheres.remove(i);
		}
	}

	public void render(SpriteBatch batch) {
		batch.begin();

		for(int i = spheres.size() - 1; i > - 1; i--) {
			Sphere sphere = spheres.get(i);
			batch.setColor(Main.getDrawColor());
			batch.draw(Assets.sphere, sphere.x - Sphere.SIZE / 2, sphere.y - Sphere.SIZE / 2,
					Sphere.SIZE, Sphere.SIZE);

			batch.setColor(Color.WHITE);
			batch.draw(Assets.sphereMask, sphere.x - Sphere.SIZE / 2, sphere.y - Sphere.SIZE / 2,
					Sphere.SIZE, Sphere.SIZE);
		}

		batch.end();
	}
}
