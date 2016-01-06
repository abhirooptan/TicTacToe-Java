package wit.cgd.xando.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;

public class GamePreferences {
	
	public boolean	 firstPlayerHuman;
	public float 	 firstPlayerSkill;
	public boolean 	 secondPlayerHuman;
	public float 	 secondPlayerSkill;
	
	public boolean 	 music;
	public float 	 musicVolume;
	public boolean 	 sound;
	public float 	 soundVolume;

    public static final String          TAG         = GamePreferences.class.getName();

    public static final GamePreferences instance    = new GamePreferences();
    private Preferences                 prefs;
	
    

    private GamePreferences() {
        prefs = Gdx.app.getPreferences(Constants.PREFERENCES);
    }

    public void load() {
    	firstPlayerHuman = prefs.getBoolean("firstPlayerHuman");
    	firstPlayerSkill = prefs.getFloat("firstPlayerSkill", 
    			MathUtils.clamp(firstPlayerSkill, 0, 10));
    	secondPlayerHuman = prefs.getBoolean("secondPlayerHuman");
    	secondPlayerSkill = prefs.getFloat("secondPlayerSkill",
    			MathUtils.clamp(secondPlayerSkill, 0, 10));
    }

    public void save() {
    	prefs.putBoolean("firstPlayerHuman", firstPlayerHuman);
    	prefs.putFloat("firstPlayerSkill", firstPlayerSkill);
    	prefs.putBoolean("secondPlayerHuman", secondPlayerHuman);
    	prefs.putFloat("secondPlayerSkill", secondPlayerSkill);
        prefs.flush();
    }

}