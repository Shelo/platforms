package cl.jara.game;

import cl.jara.platforms.Platform;

import java.util.concurrent.LinkedBlockingQueue;

public class Pool {
	static LinkedBlockingQueue<Particle> particles 	= new LinkedBlockingQueue<Particle>();
	static LinkedBlockingQueue<Platform> platforms 	= new LinkedBlockingQueue<Platform>();

	public static Particle requestParticle(float x, float y, float vx, float vy) {
		if(!particles.isEmpty())
			return particles.poll().reset(x, y, vx, vy);
		else
			return new Particle(x, y, vx, vy);
	}

	public static void addParticle(Particle particle) {
		particles.add(particle);
	}
}
