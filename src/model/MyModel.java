package model;

import java.util.ArrayList;
import java.util.Observable;

import model.algorithm.Action;
import model.algorithm.Astar;
import model.algorithm.H_functions;
import model.algorithm.SearchDomain;
import model.algorithm.Searcher;
import model.domains.*;

public class MyModel extends Observable implements Model {
	
	private SearchDomain domain;
	private Searcher algorithm;
	private H_functions heuristic;
	private SearchAlgorithmsFactory algorithmsFactory;
	private HeuristicFactory heurisitecFactory;
	private Solution solution;
	private SolutionManager solutionManager;
	
	public MyModel()
	{
		algorithmsFactory = new SearchAlgorithmsFactory();
		heurisitecFactory = new HeuristicFactory();
		solutionManager = SolutionManager.getInstance();
	}

	@Override
	public void selectDomain(String domainName, String args) {
		
		this.domain = DomainFactory.createDomain(domainName);
			
			if (args != null)
				this.domain.init(args);
			
			else
				this.domain.init();
			
	}

	@Override
	public void selectAlgorithm(String algorithmName) {
		algorithm = algorithmsFactory.createAlgorithm(algorithmName);
		
		if (algorithm instanceof Astar){		
			if (domain instanceof EightPuzzleDomain){
				setHeuristicName("8puzzle");
				((Astar) algorithm).setHeuristic(heuristic);
			}
			else if (domain instanceof MazeGameDomain){
				setHeuristicName("maze");
			((Astar) algorithm).setHeuristic(heuristic);
		}
		}
	}
	
	public void setHeuristicName(String name){
		this.heuristic = heurisitecFactory.createHeuristic(name);
	}

	@Override
	public void solveDomain() {	
		String problemDescription = domain.getProblemDescription();
		this.solution = solutionManager.getSolution(problemDescription);
		
		if (solution != null)
			System.out.println("Solution Exists in file");
		    
		
		if (solution == null) {	
			ArrayList<Action> actions = algorithm.search(domain);
			solution = new Solution();
			solution.setActions(actions);
			solutionManager.addSolution(problemDescription, solution);
		}
		
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public Solution getSolution() {
		return solution;
	}

	@Override
	public void doTask() {
		solveDomain();
	}
}
