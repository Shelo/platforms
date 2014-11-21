package cl.jara.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets {
	public static BitmapFont uilittle;
	public static BitmapFont uititle;
	public static BitmapFont uifont;
	public static BitmapFont gameScore;
	public static BitmapFont gameScoreI;

	public static void load() {
		uilittle = new BitmapFont(Gdx.files.internal("fonts/berlin100.fnt"));
		uilittle.setScale(0.3f, -0.3f);

		uifont = new BitmapFont(Gdx.files.internal("fonts/berlin100.fnt"));
		uifont.setScale(0.75f, -0.75f);

		uititle = new BitmapFont(Gdx.files.internal("fonts/berlin100.fnt"));
		uititle.setScale(1, -1);

		gameScore = new BitmapFont(Gdx.files.internal("fonts/berlin100.fnt"));
		gameScore.setScale(2, 2);

		gameScoreI = new BitmapFont(Gdx.files.internal("fonts/berlin100.fnt"));
		gameScoreI.setScale(2, - 2);
	}
}
