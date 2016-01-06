package wit.cgd.xando.game;

import wit.cgd.xando.game.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {

	public static final String	TAG			= Assets.class.getName();
	public static final Assets	instance	= new Assets();
	private AssetManager		assetManager;

	public AssetFonts fonts;
	
	public Asset				board;	
	public Asset				x;
	public Asset				o;
	
	public AssetSounds          sounds;
	public AssetMusic           music;

	private Assets() {}
	
	public void init(AssetManager assetManager) {
	
		this.assetManager = assetManager;
		assetManager.setErrorListener(this);
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		
		// load sounds
		assetManager.load("../android/assets/sounds/first.wav", Sound.class);
		assetManager.load("../android/assets/sounds/second.wav", Sound.class);
		assetManager.load("../android/assets/sounds/win.wav", Sound.class);
		assetManager.load("../android/assets/sounds/draw.wav", Sound.class);

		// load music
		assetManager.load("../android/assets/music/keith303_-_brand_new_highscore.mp3", Music.class);
		
		assetManager.finishLoading();
		Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
		for (String a : assetManager.getAssetNames())
			Gdx.app.debug(TAG, "asset: " + a);
		
		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
		
		for (Texture t : atlas.getTextures())				// enable texture filtering for pixel smoothing
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		fonts = new AssetFonts();
		
		// create game resource objects
		board = new Asset(atlas, "board");
		x = new Asset(atlas, "x");		
		o = new Asset(atlas, "o");
		
		sounds = new AssetSounds(assetManager);
		music = new AssetMusic(assetManager);
		
	}

	@Override
	public void dispose() {
		assetManager.dispose();
	}


	@Override
	public void error(@SuppressWarnings("rawtypes") AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "Couldn't load asset '" + asset + "'", (Exception) throwable);
	}
	

	public class Asset {
		public final AtlasRegion	region;

		public Asset(TextureAtlas atlas, String imageName) {
			region = atlas.findRegion(imageName);
			Gdx.app.log(TAG, "Loaded asset '" + imageName + "'");
		}
	}

	public class AssetFonts {
		public final BitmapFont defaultSmall;
		public final BitmapFont defaultNormal;
		public final BitmapFont defaultBig;

		public AssetFonts () {
			// create three fonts using Libgdx's built-in 15px bitmap font
			defaultSmall = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"), true);
			defaultNormal = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"), true);
			defaultBig = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"), true);
			// set font sizes
			defaultSmall.setScale(0.75f);
			defaultNormal.setScale(1.0f);
			defaultBig.setScale(4.0f);
			// enable linear texture filtering for smooth fonts
			defaultSmall.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			defaultNormal.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			defaultBig.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
	}

	public class AssetSounds {

	    public final Sound  first;
	    public final Sound  second;
	    public final Sound  win;
	    public final Sound  draw;

	    public AssetSounds(AssetManager am) {
	        first = am.get("../android/assets/sounds/first.wav", Sound.class);
	        second = am.get("../android/assets/sounds/second.wav", Sound.class);
	        win = am.get("../android/assets/sounds/win.wav", Sound.class);
	        draw = am.get("../android/assets/sounds/draw.wav", Sound.class);
	    }
	}
	
	public class AssetMusic {
	    public final Music  song01;

	    public AssetMusic(AssetManager am) {
	        song01 = am.get("../android/assets/music/keith303_-_brand_new_highscore.mp3", Music.class);
	    }
	}
}
