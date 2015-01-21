package model.domains;

import java.util.HashMap;

import model.algorithm.*;

public class EightPuzzleDomain implements SearchDomain {
	
	EightPuzzleState start,goal;
	
	@Override
	public void init(String puzzleStart) {
		this.start = new EightPuzzleState(puzzleStart);
		this.goal = new EightPuzzleState("1,2,3,4,5,6,7,8,0");
		if(!this.isSolvable(start) )
			System.out.println("this is not solvable  ! ");
	}
	
	public void init(){
		this.start = new EightPuzzleState();
		this.goal = new EightPuzzleState("1,2,3,4,5,6,7,8,0");
		
		while(/*this.start.getEvaluation(goal) > 5  ||*/ !this.isSolvable(start) ) {
			this.start = new EightPuzzleState();
		}
	}
	
	@Override
	public State getStartState() {
		return this.start;
	}

	@Override
	public State getGoalState() {
		return this.goal;
	}

	@Override
	public HashMap<Action, State> getAllPossibleMoves(State current) {
		
		HashMap<Action, State> moves = new HashMap<Action,State>();
		
		EightPuzzleState currentPuzzleState = (EightPuzzleState)current;
		currentPuzzleState.setCurrZeroLocation();
			
		for (int y = Math.max(0, currentPuzzleState.getCurrZeroY()-1) ; y <= Math.min(2, currentPuzzleState.getCurrZeroY()+1) ; y++){
			for (int x = Math.max(0, currentPuzzleState.getCurrZeroX()-1) ; x <= Math.min(2, currentPuzzleState.getCurrZeroX()+1) ; x++ ){
			
				if(x == currentPuzzleState.getCurrZeroX() && y == currentPuzzleState.getCurrZeroY() )
					continue;
			
				
				if (isPossible( currentPuzzleState, x , y )) {
					String newString = currentPuzzleState.getNewString(currentPuzzleState.getCurrZeroX(), currentPuzzleState.getCurrZeroY(), x, y);
					State next = new EightPuzzleState(newString);
				//	if( next.getEvaluation(goal) < current.getEvaluation(goal)){
					
						Action a = new Action(current.toString() + " --> " + next.toString());
						moves.put(a, next);	
					//}
				}
			}
		}
		return moves;

	}
	
	public boolean isPossible(EightPuzzleState state, int indexX, int indexY) {
		return (Math.abs(state.getCurrZeroX()-indexX)+Math.abs(state.getCurrZeroY()-indexY) == 1 );
	}

	public boolean isSolvable(EightPuzzleState start){
		
		int distance = start.getEvaluation(goal);
		
		return (distance%2==0 || distance == 1) ; 
		}

	@Override
	public String getProblemDescription() {
		
		return this.start.toString();
	}
	
	@Override
	public String getGameDescription(){
		return this.start.toString();
	}

}
