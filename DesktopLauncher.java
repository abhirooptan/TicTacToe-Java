/**
 * @file        DesktopLauncher.java
 * @author      CBurke	20062067
 * @assignment  XandO
 * @brief       
 *
 * @notes      
 */

package wit.cgd.xando.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import wit.cgd.xando.XandOMain;

public class DesktopLauncher {
    /*private static boolean  rebuildAtlas        = true;
    private static boolean  drawDebugOutline    = false*/;

    public static void main(String[] args) {
        /*if (rebuildAtlas) {
            Settings settings = new Settings();
            settings.maxWidth = 1024;
            settings.maxHeight = 1024;
            settings.debug = drawDebugOutline;
            TexturePacker.process(settings, "assets-raw/images", "../android/assets/images/",
                    "xando.atlas");
            TexturePacker.process(settings, "assets-raw/images-ui", "../android/assets/images/",
                    "ui.atlas");
        }*/

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "XandO";
        config.width = 800;
        config.height = 480;
        new LwjglApplication(new XandOMain(), config);
    }
}