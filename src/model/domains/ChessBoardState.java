package model.domains;

import model.algorithm.State;

public class ChessBoardState extends State {

	int[] redChar = new int[2], blueChar = new int[2];
	
	public ChessBoardState(int[] blueChar, int[] redChar) {
		super();
		
		this.blueChar = blueChar;
		this.redChar = redChar;
	}
	public int[] getRedChar() {
		return redChar;
	}
	
	public int[] getBlueChar() {
		return blueChar;
	}
	
	public void setBlueChar(int[] blueChar) {
		this.blueChar = blueChar;
	}
	
	public void setRedChar(int[] redChar){
		this.redChar = redChar;
	}
	
	public void initBlueChar(String args){
		String[] charB = args.split(",");
		blueChar[0] = Integer.parseInt(charB[0]);
		blueChar[1] = Integer.parseInt(charB[1]);
	}
	
	public void initRedChar(String args){
		String[] charR = args.split(",");
		redChar[0] = Integer.parseInt(charR[0]);
		redChar[1] = Integer.parseInt(charR[1]);
		
	}
	@Override
	public int compareTo(State arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEvaluation(State goal) {
		// TODO Auto-generated method stub
		return 0;
	}

}

