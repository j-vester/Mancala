package mancala.domain;

public class MancalaImpl implements Mancala {
    Kalaha kalaha_player_one;

    public MancalaImpl() {
        this.kalaha_player_one = new Kalaha();
    }

    @Override
    public boolean isPlayersTurn(int player) {
        if (player == Mancala.PLAYER_ONE) {
            return this.kalaha_player_one.getPlayer().isCurrentPlayer();
        } else {
            return this.kalaha_player_one.getPlayer().getOpponent().isCurrentPlayer();
        }
    }

    private Player getPlayer(int index) {
        Player player;
        if (index < 7) {
            player = this.kalaha_player_one.getPlayer();
        } else {
            player = this.kalaha_player_one.getPlayer().getOpponent();
        }
        return player;
    }

    @Override
	public void playPit(int index) throws MancalaException {
        Player player = getPlayer(index);
        int id = (index + 1) % 7;
        if (id == 0) {
            this.kalaha_player_one.getKalaha(player).playPit();
        } else {
            this.kalaha_player_one.getPlayingPit(id, player).playPit();
        }
    }
	
	@Override
	public int getStonesForPit(int index) {
        Player player = getPlayer(index);
        int id = (index + 1) % 7;
        if (id == 0) {
            return this.kalaha_player_one.getKalaha(player).getStones();
        } else {
            return this.kalaha_player_one.getPlayingPit(id, player).getStones();
        }
    }

	@Override
	public boolean isEndOfGame() {
        return (!this.isPlayersTurn(Mancala.PLAYER_ONE) && !this.isPlayersTurn(Mancala.PLAYER_TWO)); 
    }

	@Override
	public int getWinner() {
        if (!this.isEndOfGame()) {
            return Mancala.NO_PLAYERS;
        } else {
            int stones_player_one = this.kalaha_player_one.getStones();
            Player player_two = this.kalaha_player_one.getPlayer().getOpponent();
            int stones_player_two = this.kalaha_player_one.getKalaha(player_two).getStones();
            if (stones_player_one > stones_player_two) {
                return Mancala.PLAYER_ONE;
            } else if (stones_player_one < stones_player_two) {
                return Mancala.PLAYER_TWO;
            } else {
                return Mancala.BOTH_PLAYERS;
            }
        }
    }
}