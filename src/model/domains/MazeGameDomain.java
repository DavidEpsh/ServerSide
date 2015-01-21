package model.domains;

import java.util.HashMap;





import model.algorithm.Action;
import model.algorithm.SearchDomain;
import model.algorithm.State;

public class MazeGameDomain implements SearchDomain{
	
	MazeGameState start,goal;
	
	protected int length, heigth ,blocks;
	String mazeGameDescription;
	
	int[][] maze;

	public MazeGameDomain() {
	}
	
	public void init(String args){
		
		String[] mazeProperties = args.split(",");
		if (mazeProperties.length > 4){
			initGivenGame(mazeProperties);
		}
		
		this.length = Integer.parseInt( mazeProperties[0] );
		this.heigth = Integer.parseInt( mazeProperties[1] );
		this.blocks = Integer.parseInt( mazeProperties[2] );
		
		this.start = new MazeGameState(0, 0);
		this.goal = new MazeGameState(length-1,heigth-1);
			
		this.maze= new int[heigth][length];
		
		for(int i=0 ; i<length ; i++) {
			for (int j=0; j<heigth ; j++ ){
				this.maze[i][j]=1;
			}
		}
		
		for (int x=0 ; x < this.blocks ; x++){
			int rX = (int) (Math.random()*length);
			int rY = (int) (Math.random()*heigth);
			
			if ((rX==0 && rY==0) || (rX == length-1 && rY == heigth -1 ) ){
				x--;
			}
			
			else if (this.maze[rY][rX]==1)
						this.maze[rY][rX]=0;
			
			else 
				x--;
		}
		
		setMazeGameDescription();
		
	}
	
	public MazeGameDomain(String args) {
		init(args);
	}
	
	public boolean isPossible(int x, int y) {
		if (maze[y][x]==0)
			return false;
		else 
			return true;
	}
	@Override
	public State getStartState() {
		return start;
	}

	@Override
	public State getGoalState() {
		return goal;
	}

	@Override
	public HashMap<Action, State> getAllPossibleMoves(State current) {
		
		HashMap<Action, State> moves=new HashMap<Action,State>();

		MazeGameState currMazeState = (MazeGameState)current;
		
		for (int y = Math.max(0, currMazeState.getY()-1) ; y <= Math.min(this.heigth - 1, currMazeState.getY()+1) ; y++){
			for (int x = Math.max(0, currMazeState.getX()-1) ; x <= Math.min(this.length - 1, currMazeState.getX()+1) ; x++ ){
				if( (x == currMazeState.getX() && y == currMazeState.getY()) ||  Math.abs(x-currMazeState.getX()) + Math.abs(y-currMazeState.getY()) >1)
					continue;
			
				if (this.isPossible( x , y )) {
					
					State next = new MazeGameState(x,y);

					Action a = new Action(current.toString() + "-->" + next.toString());
					moves.put(a, next);	
				}
				
			}
		}
		return moves;
	}

	@Override
	public void init() {
		init("8,8,20");
		
	}
	
	public void setMazeGameDescription(){
		
		mazeGameDescription = "";
		mazeGameDescription += Integer.toString(length)+",";
		mazeGameDescription += Integer.toString(heigth)+",";
		
		
		for (int y=0 ; y<heigth ; y++){
			for(int x=0 ; x<length ; x++){
				
				if(y==heigth-1 && x== length-1){
					mazeGameDescription += Integer.toString(maze[y][x]);
					}
				
				mazeGameDescription += Integer.toString(maze[y][x]) + ","; 	
				}
			}
	}

	public void initGivenGame(String[] args){
		
		int[] intArray = new int[args.length];
		
		for (int i=0 ; i<args.length ; i++){
			intArray[i] = Integer.parseInt(args[i]);
		}
		
		start = new MazeGameState(intArray[0], intArray[1]);
		
	}
	
	@Override
	public String getProblemDescription() {
		
		return ""+ Integer.toString(start.getX()) + Integer.toString(start.getY()) + "-->" + (this.length-1) + "," + (this.heigth-1);
	}
	
	@Override
	public String getGameDescription(){
		return this.mazeGameDescription;
	}
}
