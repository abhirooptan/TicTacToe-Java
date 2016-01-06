package wit.cgd.xando.game.ai;

import wit.cgd.xando.game.BasePlayer;
import wit.cgd.xando.game.Board;

public class FirstSpacePlayer extends BasePlayer {
	
	public FirstSpacePlayer(Board board, int symbol) {
		super(board, symbol);
	}

	@Override
	public int move() {
		for (int r=2; r>=0; --r)
			for (int c=0; c<3; ++c) 
				if (board.cells[r][c]==board.EMPTY) 
					return r*3+c;
		return -1;
	}
		
}
