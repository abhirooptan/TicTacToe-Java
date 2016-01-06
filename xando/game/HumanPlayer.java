package wit.cgd.xando.game;

public class HumanPlayer extends BasePlayer {

	
	public HumanPlayer(Board board, int symbol) {
		super(board, symbol);
		human = true;
	}

	@Override
	public int move() {
		// human move handled in worldController
		return 0;
	}
}
