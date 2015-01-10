package model.algorithm;

import java.io.Serializable;

public class Action implements Serializable{

	private static final long serialVersionUID = 2226368497223644299L;
	String description;
	
	public Action(String description) {
		this.description=description;
	}
	
	@Override
	public int hashCode(){
		return description.hashCode();
	}
	
	@Override
	public String toString(){
		return description;
	}

	public int getPrice() {
		return 1;
	}
}
