package model.domains;

import model.algorithm.H_functions;
import model.algorithm.State;
import java.lang.Math;
public class MazeHeuristic implements H_functions {

	@Override
	public int h_function(State current, State goal) {
		MazeGameState mazeCurrent = (MazeGameState)current;
		MazeGameState mazeGoal = (MazeGameState)goal;
		
		int currentX = mazeCurrent.getX();
		int currentY = mazeCurrent.getY();
		int goalX = mazeGoal.getX();
		int goalY = mazeGoal.getY();
		
		int s = Math.abs(currentX - goalX);
		int t = Math.abs(currentY - goalY);
		
		if (s>t)
			return s;
		else 
			return t;
		
	}

}
