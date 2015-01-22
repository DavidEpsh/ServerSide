package model.algorithm;

import java.util.HashMap;

/**
 * this interface holds the functionality expected from our domains
 *
 */
public interface SearchDomain {

	// this functoin ......
	State getStartState();
	State getGoalState();
	HashMap<Action,State> getAllPossibleMoves(State current);
	void init(String args);
	void init();	
	String getProblemDescription();
	String getGameDescription();
}
