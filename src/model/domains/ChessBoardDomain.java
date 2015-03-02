package model.domains;

import java.util.HashMap;

import model.algorithm.Action;
import model.algorithm.SearchDomain;
import model.algorithm.State;

public class ChessBoardDomain implements SearchDomain{

	int[][] chessBoard = new int[8][8];
	String description;
	ChessBoardState game;
	
	@Override
	public State getStartState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State getGoalState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Action, State> getAllPossibleMoves(State current) {
		ChessBoardState currState = (ChessBoardState)current;
		int x = currState.getBlueChar()[0];
		int y = currState.getBlueChar()[1];
		
		HashMap<Action, State> moves=new HashMap<Action,State>();
		
		for (int y1 = Math.max(0, y-2) ; y1 <= Math.min(7, y+2) ; y1++){
			for (int x1 = Math.max(0, x-2) ; x1 <= Math.min(7, x+1) ; x1++ ){
				if((Math.abs(x1-x) + Math.abs(y1-y)) == 3) {
					
					int[] blueChar = new int[]{x1,y1};
					State next = new ChessBoardState(blueChar,currState.getRedChar());

					Action a = new Action(current.toString() + "-->" + next.toString());
					moves.put(a, next);	
				}
				
			}
		}
		return moves;
	}

	@Override
	public void init(String args) {
		String[] chars = args.split("-->");
		
		game.initBlueChar(chars[0]);
		game.initRedChar(chars[1]);
		
		
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getProblemDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGameDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isPossible(int x, int y){
		
		if (x > 7 || y > 7 || x < 0 || y < 0)
			return false;
		else 
			return true;
	}

}
