package cl.jara.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets {
	public static BitmapFont uititle;
	public static BitmapFont uifont;
	public static BitmapFont font;

	static {
		uifont = new BitmapFont(Gdx.files.internal("fonts/century_gothic.fnt"));
		uifont.setScale(1, -1);

		uititle = new BitmapFont(Gdx.files.internal("fonts/century_gothic.fnt"));
		uititle.setScale(1.25f, -1.25f);
	}
}
