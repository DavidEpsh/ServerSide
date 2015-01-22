package model;

import model.algorithm.*;
import model.domains.EightPuzzleDomain;
import model.domains.MazeGameDomain;
/**
 *This class is used to create new Domains for our Model
 */
public class DomainFactory {

	public static SearchDomain createDomain (String domain) {
		
		if (domain.equals("Maze"))
			return new MazeGameDomain();
		
		else if (domain.equals("8puzzle"))
			return new EightPuzzleDomain();
		
		else 
			return new MazeGameDomain();
		}
}
