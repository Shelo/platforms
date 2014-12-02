package cl.jara.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

public class Main extends com.badlogic.gdx.Game {
	public static final float COLOR_CHANGE_FREQ = 5;

	public static Main instance;
	ActionResolver actionResolver;

	private static Color drawingColor;
	private static Color currentColor;
	private static Color[] colors = {
			new Color(0.23828125f, 0.34765625f, 0.66796875f, 1),	// azul.
			new Color(0.18359375f, 0.30859375f, 0.30859375f, 1),	// dark slate green.
			new Color(0.6953125f, 0.1328125f, 0.01171875f, 1),		// rojo.
			new Color(0.1796875f, 0.54296875f, 0.33984375f, 1),		// sea green.
			new Color(0.25f, 0.25f, 0.25f, 1),						// grey.
			new Color(0.3828125f, 0.27734375f, 0.1953125f, 1),		// brown.
	};

	public static float arcRadStart;

	float colorChangeTimer;
	int nextColorIndex;
	int colorIndex;

	public Main(ActionResolver actionResolver) {
		this.actionResolver = actionResolver;
	}

	@Override
	public void create() {
		instance = this;

		Assets.load();

		// establecer variables.
		nextColorIndex = MathUtils.random(colors.length - 1);
		colorIndex = MathUtils.random(colors.length - 1);
		colorChangeTimer = COLOR_CHANGE_FREQ;
		drawingColor = new Color();

		toMenu();
	}

	public void toGame() {
		// desbloquea el achievement Begginner's Lucks.
		if(actionResolver.getSignedIn())
			actionResolver.unlockAchievement("CgkI6JzeotQdEAIQAg");
		setScreen(new Game());
	}

	public void toGameOver(int score) {
		setScreen(new GameOver(score));

		if(actionResolver.getSignedIn())
			actionResolver.submitScore(score);
	}

	public void toMenu() {
		setScreen(new Menu());
	}

	@Override
	public void render () {
		Time.update(Gdx.graphics.getDeltaTime());

		if(currentColor == null)
			currentColor = new Color(colors[colorIndex]);

		float vr = (colors[nextColorIndex].r - currentColor.r) * 0.0015f;
		float vg = (colors[nextColorIndex].g - currentColor.g) * 0.0015f;
		float vb = (colors[nextColorIndex].b - currentColor.b) * 0.0015f;
		currentColor.add(vr, vg, vb, 0);

		colorChangeTimer -= Time.delta;
		if(colorChangeTimer <= 0) {
			nextColorIndex = MathUtils.random(colors.length - 1);
			colorChangeTimer = COLOR_CHANGE_FREQ;
		}

		drawingColor.set(currentColor).mul(1.5f);
		arcRadStart += 90 * Time.delta;

		if(getScreen() != null)
			getScreen().render(Gdx.graphics.getDeltaTime());
	}

	public static Color getCurrentColor() {
		return currentColor;
	}

	public static Color getDrawColor() {
		return drawingColor;
	}

	@Override
	public void resume () {
		Assets.load();
		if(getScreen() != null)
			getScreen().resume();
	}

	@Override
	public void dispose() {
		super.dispose();
		Assets.dispose();
	}
}
