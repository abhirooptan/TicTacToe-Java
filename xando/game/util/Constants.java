package wit.cgd.xando.game.util;

public class Constants {

	// Visible game world is 5 meters wide
	public static final float	VIEWPORT_WIDTH			= 5.0f;
	// Visible game world is 5 meters tall
	public static final float	VIEWPORT_HEIGHT			= 5.0f;

	// GUI Width
	public static final int VIEWPORT_GUI_WIDTH = 800;

	// GUI Height
	public static final int VIEWPORT_GUI_HEIGHT = 480;
	
	public static final String	TEXTURE_ATLAS_OBJECTS	= "images/xando.pack";
	
	// location of game specific skin and atlas
	public static final String  SKIN_UI                 = "../android/assets/images/ui.json";
	public static final String  TEXTURE_ATLAS_UI        = "../android/assets/images/ui.atlas";

	// location of libgdx default skin and atlas
	public static final String  SKIN_LIBGDX_UI          = "../android/assets/images/uiskin.json";
	public static final String  TEXTURE_ATLAS_LIBGDX_UI = "../android/assets/images/uiskin.atlas";
	
	public static final int BUTTON_PAD      = 5;
	
	// Game setting (preferences + stats) files
	public static final String STATS = "xando.stats";
	public static final String PREFERENCES = "xando.prefs";

}
