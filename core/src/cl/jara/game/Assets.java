package cl.jara.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets {
	public static BitmapFont uilittle;
	public static BitmapFont uititle;
	public static BitmapFont uifont;
	public static BitmapFont gameScore;
	public static BitmapFont gameScoreI;

	public static Texture sphere;
	public static Texture sphereMask;

	public static void load() {
		uilittle = new BitmapFont(Gdx.files.internal("fonts/berlin100.fnt"));
		uilittle.setScale(0.3f, -0.3f);

		uifont = new BitmapFont(Gdx.files.internal("fonts/berlin100.fnt"));
		uifont.setScale(0.75f, -0.75f);

		uititle = new BitmapFont(Gdx.files.internal("fonts/berlin100.fnt"));
		uititle.setScale(1, -1);

		gameScore = new BitmapFont(Gdx.files.internal("fonts/berlin200.fnt"));
		gameScore.setScale(1, 1);

		gameScoreI = new BitmapFont(Gdx.files.internal("fonts/berlin200.fnt"));
		gameScoreI.setScale(1, - 1);

		sphere = new Texture("sphere.png");
		sphereMask = new Texture("mask.png");
	}

	public static void dispose() {
		sphere.dispose();
		sphereMask.dispose();
		uititle.dispose();
		uifont.dispose();
		gameScore.dispose();
		gameScoreI.dispose();
	}
}
