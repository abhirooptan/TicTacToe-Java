package wit.cgd.xando.game;

import wit.cgd.xando.game.ai.*;
import wit.cgd.xando.game.util.GameStats;

import com.badlogic.gdx.Game;

import wit.cgd.xando.screens.MenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WorldController extends InputAdapter {

	@SuppressWarnings("unused")
	private static final String	TAG	= WorldController.class.getName();
	public float				viewportWidth;
	public int					width, height;
	float						timeLeftGameOverDelay;

	public Board				board;
	
	private Game                game;
	
	boolean                     dragging = false;
    int                         dragX, dragY;
    TextureRegion               dragRegion;

	private void backToMenu() {
	    // switch to menu screen
	    game.setScreen(new MenuScreen(game));
	}

	public WorldController(Game game) {
	    this.game = game;
	    init();
	}

	private void init() {
		Gdx.input.setInputProcessor(this);
		board = new Board();
		board.firstPlayer = new HumanPlayer(board, board.X);
		board.secondPlayer = new RandomSpacePlayer(board, board.O);
		board.start();

		timeLeftGameOverDelay = 2;
	}

	@SuppressWarnings("static-access")
	public void update(float deltaTime) {
	    if (board.gameState == Board.GameState.PLAYING) {
	        board.move();
	    }

	    if (board.gameState != Board.GameState.PLAYING) {
	        timeLeftGameOverDelay -= deltaTime;
	        if (timeLeftGameOverDelay < 0) {
	        	if (board.gameState== board.gameState.X_WON) {
		    	    GameStats.instance.win();
		    	} else if (board.gameState== board.gameState.O_WON) {
		    	    GameStats.instance.lose();
		    	} else {
		    	    GameStats.instance.draw();
		    	}
	            backToMenu();
	        }
	    }
	}
	
	@Override
	public boolean keyUp(int keycode) {
	    if (keycode == Keys.ESCAPE || keycode == Keys.BACK) {
	        backToMenu();
	    }
	    return false;
	}

	@Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (board.gameState == Board.GameState.PLAYING && board.currentPlayer.human) {

            // convert to cell position
            int row = 4 * (height - screenY) / height;
            int col = (int) (viewportWidth * (screenX - 0.5 * width) / width) + 1;

            // board move - just place piece and return
            if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                board.move(row, col);
                return true;
            }

            dragX = screenX;
            dragY = screenY;

            // check if valid start of a drag for first player
            if (row == 1 && col == -1 && board.currentPlayer==board.firstPlayer) {
                dragging = true;
                dragRegion = Assets.instance.x.region;
                return true;
            }
            // check if valid start of a drag for second player
            if (row == 1 && col == 3 && board.currentPlayer==board.secondPlayer) {
                dragging = true;
                dragRegion = Assets.instance.o.region;
                return true;
            }

        }

        return true;
    }
	
	@Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        dragX = screenX;
        dragY = screenY;        
        return true;
    }
	
	@Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        dragging = false;

        // convert to cell position
        int row = 4 * (height - screenY) / height;
        int col = (int) (viewportWidth * (screenX - 0.5 * width) / width) + 1;

        // if a valid board cell then place piece
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            board.move(row, col);
            return true;
        }

        return true;
    }

}
