package cl.jara.game;

import cl.jara.platforms.Platform;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class ParticleSystem {
	private static final float PARTICLE_SIZE = 5;

	private static ArrayList<Particle> particles = new ArrayList<Particle>();

	public static void update(ArrayList<Platform> platforms) {
		for(int i = particles.size() - 1; i > - 1; i--) {
			Particle particle = particles.get(i);

			particle.update();

			if(particle.lifeTime <= 0) {
				Pool.addParticle(particle);
				particles.remove(i);
				continue;
			}

			for(Platform platform : platforms) {
				if(particle.x > platform.x && particle.x < platform.x + platform.getWidth()) {
					if(particle.y > platform.y && particle.y < platform.y + platform.getHeight()) {
						particle.vy *= - Particle.BOUNCINESS;
						if(particle.vy < Particle.MIN_BOUNCE) particle.vy = Particle.MIN_BOUNCE;
						particle.y = platform.y + platform.getHeight();
						break;
					}
				}
			}
		}
	}

	public static void render(ShapeRenderer shape) {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.setColor(Color.WHITE);
		for(Particle particle : particles)
			shape.rect(particle.x, particle.y, PARTICLE_SIZE, PARTICLE_SIZE);
		shape.end();
	}

	public static void genCone(float x, float y, int ammount) {
		for(float t = (float) Math.PI / 4; t < Math.PI * 3 / 4; t += (Math.PI / 2 + 1) / ammount)
			particles.add(Pool.requestParticle(x, y, (float) Math.cos(t) * 300, (float) Math.sin(t) * 400));
	}

	public static void clear() {
		for(int i = particles.size() - 1; i > - 1; i--) {
			Pool.addParticle(particles.get(i));
			particles.remove(i);
		}
	}
}
