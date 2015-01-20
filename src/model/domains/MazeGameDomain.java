package model.domains;

import java.util.HashMap;





import model.algorithm.Action;
import model.algorithm.SearchDomain;
import model.algorithm.State;

public class MazeGameDomain implements SearchDomain{
	
	MazeGameState start,goal;
	
	protected int width, length, blocks;
	String mazeGameDescription;
	
	int[][] maze;

	public MazeGameDomain() {
	}
	
	public void init(String args){
		
		String[] mazeProperties = args.split(",");
		if (mazeProperties.length > 6){
			initGivenGame(mazeProperties);
		}
		else{
		this.width = Integer.parseInt( mazeProperties[0] );
		this.length = Integer.parseInt( mazeProperties[1] );
		this.blocks = Integer.parseInt( mazeProperties[2] );
		
		this.start = new MazeGameState(0, 0);
		this.goal = new MazeGameState(width-1,length-1);
			
		this.maze= new int[width][length];
		
		for(int i=0 ; i<width ; i++) {
			for (int j=0; j<length ; j++ ){
				this.maze[i][j]=1;
			}
		}
		
		for (int x=0 ; x < this.blocks ; x++){
			int rX = (int) (Math.random()*width);
			int rY = (int) (Math.random()*length);
			
			if ((rX==0 && rY==0) || (rX == width-1 && rY == length -1 ) ){
				x--;
			}
			
			else if (this.maze[rX][rY]==1)
						this.maze[rX][rY]=0;
			
			else 
				x--;
		}
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
		
		for (int x = Math.max(0, currMazeState.getX()-1) ; x <= Math.min(this.width - 1, currMazeState.getX()+1) ; x++ ){
			for (int y = Math.max(0, currMazeState.getY()-1) ; y <= Math.min(this.length - 1, currMazeState.getY()+1) ; y++){
				if(x == currMazeState.getX() && y == currMazeState.getY() )
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
		
		String temp = "";
//		mazeGameDescription += Integer.toString(length)+",";
//		mazeGameDescription += Integer.toString(width)+",";
		
		
		for (int i =0 ; i<width ; i++){
			for(int j=0 ; j<length ; j++){
				
				if(i==width-1 && j== length-1){
					temp += Integer.toString(maze[i][j]);
					}
				
				else{
					temp += Integer.toString(maze[i][j]) + ",";
				}
				}
			}
		mazeGameDescription = temp;
	}

	public void initGivenGame(String[] args){
		
		width = Integer.parseInt(args[2]);
		length = Integer.parseInt(args[3]);
		
		int[][] intArray = new int[width][length];
		int k =4;
		for (int i=0 ; i<length ; i++){
			for (int j=0 ; j<width ; j++){
			intArray[i][j] = Integer.parseInt(args[k]);
			k++;
			}
		}
		
		start = new MazeGameState(Integer.parseInt(args[1]), Integer.parseInt(args[0]));
		goal = new MazeGameState(Integer.parseInt(args[3])-1, Integer.parseInt(args[2])-1);
		
		maze = intArray;
		setMazeGameDescription();
	}
	
	@Override
	public String getProblemDescription() {
		
		return ""+ Integer.toString(start.getX())+ "," + Integer.toString(start.getY()) + "-->" + (this.width-1) + "," + (this.length-1);
	}
	
	@Override
	public String getGameDescription(){
		return this.mazeGameDescription;
	}
}
