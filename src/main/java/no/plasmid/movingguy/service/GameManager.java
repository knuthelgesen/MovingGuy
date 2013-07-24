package no.plasmid.movingguy.service;

import no.plasmid.movingguy.im.Game;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GameManager implements Service {

	private Game currentGame = null;
	
	/**
	 * Get the current game. Might be null if no game is currently running.
	 * @return
	 */
	public Game getCurrentGame() {
		return currentGame;
	}
	
	/**
	 * Create a new game
	 * @return
	 */
	public void createNewGame() {
		currentGame = new Game();
	}
	
	/**
	 * Load a saved game from disk
	 * @return
	 */
	public Game loadGame() {
		throw new NotImplementedException();
	}
	
	/**
	 * Save a game to disk
	 * @param game
	 */
	public void saveGame(Game game) {
		throw new NotImplementedException();
	}
	
	@Override
	public void initializeService() {
		//Nothing to do here
	}

}
