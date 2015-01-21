package model.domains;

import java.util.ArrayList;

import model.MyModel;
import model.algorithm.*;

public class EightPuzzleState extends State {
	int[][] state;
	int currZeroX,currZeroY;
	
	
	public EightPuzzleState() {     // Default Constructor when puzzle not given
		this.state = new int[3][3];
		int [] tempIntegers;
		tempIntegers = new int[]{1,2,3,4,5,6,7,8};
		// ArrayList<Integer> temp = new ArrayList<Integer>(){1,2,3,4,5,6,7,8,0};
		for ( int i=0 ; i<8 ; i++){
			int rX = (int)(Math.random()*3);
			int rY = (int)(Math.random()*3);
			
			if (this.state[rY][rX] == 0)
				state[rY][rX]=tempIntegers[i];
			else
				i--;
		}
	}
	
	public EightPuzzleState(String args){       // Constructor when puzzle is given
		String[] EightPuzzleProperties = args.split(",");
		this.state = new int[3][3];
		
		for (int i = 0; i < 9; i++) {
			this.state[i/3][i%3] = Integer.parseInt(EightPuzzleProperties[i]);
		}

	}
	public int[][] getState(){
		return this.state;
	}
	
	@Override
	public int getEvaluation(State goal) {
		if(goal == null)
			goal = new EightPuzzleState("1,2,3,4,5,6,7,8,0");
		
		EightPuzzleState goalState = (EightPuzzleState) goal;
			int distance=0,tempInt;
			
			for (int y=0 ; y<3 ; y++){
				for(int x=0 ; x<3 ; x++){	
					if(this.state[y][x]==0)
						continue;
					
					tempInt = this.state[y][x];
					
					for (int y1=0 ; y1<3 ; y1++){
						for (int x1=0 ; x1<3 ; x1++){
							if (tempInt == goalState.getPoint(x1, y1))
								distance += Math.abs(x1-x)+Math.abs(y1-y);
						}
					}
				}
			}
	
			return distance;
		}
	
	public void setCurrZeroLocation(){
		
		for (int y=0 ; y<3 ; y++){
			for (int x=0 ; x<3 ; x++){
				if (this.state[y][x] == 0)
				{
					this.currZeroX = x;
					this.currZeroY = y;
				}
			}
		}
	}
	
	public int getCurrZeroY(){
		setCurrZeroLocation();
		return this.currZeroY;
	}
	
	public int getCurrZeroX(){
		setCurrZeroLocation();
		return this.currZeroX;
	}
	
	@Override
	public String toString() {
		String ret = "";
		
		for(int y=0 ; y<3 ; y++){
			for (int x=0 ; x<3 ; x++){
				if (x == 2 && y ==2 ){
					ret += this.state[y][x];
					continue;
				}
				ret += this.state[y][x]+",";
			}
		}
		return  ret; 
	}
	
	public int getPoint(int x,int y){
		return this.state[y][x];
	}
	
	public void setPoint(int x, int y, int newInt){
		this.state[y][x] = newInt;
	}
	
	public String getNewString(int x, int y, int newX, int newY){
		EightPuzzleState newState = new EightPuzzleState();
		
		for(int y1=0 ; y1<3 ; y1++){
			for (int x1=0 ; x1<3 ; x1++)
				newState.setPoint(x1,y1,this.state[y1][x1]);
		}
		
			int temp= newState.getPoint(x,y);
			newState.setPoint(x, y, this.state[newY][newX]);
			newState.setPoint(newX,newY,temp);
			
			return ""+newState.toString();
	}

	@Override
	public int compareTo(State o) {
		 
		 return this.getEvaluation(null) - o.getEvaluation(null);

	}
	
	
}
