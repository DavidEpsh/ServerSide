package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.concurrent.ConcurrentHashMap;
/**
 * This class holds a .dat file which contains HashMap of Games that we have previously solved.
 * using this class we are able to to check if we already have a solution for the given problem and give it instead of calling our Solve functions.
 * if we haven't solved the specific problem, on exit, our new problems will be saved to this HashMap and to the file.
 */
public class SolutionManager {

	private static SolutionManager instance = null;
	private static final String FILE_NAME = "resources/solution.dat";
	private ConcurrentHashMap<String, Solution> solutionMap;

	protected SolutionManager() {
		
		File f = new File(FILE_NAME);
		System.out.println("the length of the file is : " +f.length());

		InputStream file = null;
		if( f.isFile() && f.length() > 0) {
			try {
				file = new BufferedInputStream(new FileInputStream(f));
				if (file != null) {
					ObjectInput input = new ObjectInputStream(file);
					solutionMap = (ConcurrentHashMap<String, Solution>) input.readObject();
				}
			} catch (IOException e) {
				 e.printStackTrace();
			} catch (ClassNotFoundException e) {
				 e.printStackTrace();
			} finally {
				if (file != null)
					try {
						file.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			
		}
		else {
			solutionMap = new ConcurrentHashMap<String,Solution>();
		}
			
	}
	
	public static SolutionManager getInstance() {
		if (instance == null)
			instance = new SolutionManager();

		return instance;
	}

	public void addSolution(String description, Solution Solution) {
		solutionMap.putIfAbsent(description, Solution);
	}

	public Solution getSolution(String description) {
		
		return solutionMap.get(description);
	}
	
	public void Print() {
		System.out.println("the size of the map is : " + solutionMap.size());
	}

	public void saveSolutionsInFile() {
		
		System.out.println("Solution Saved To file");
		System.out.println("New size:" +solutionMap.size());
		
		File f = new File(FILE_NAME);
		
		if(!f.isFile()) {
			try {
				f.createNewFile();
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}
		

		
		try (OutputStream file = new FileOutputStream(FILE_NAME);
	    	  OutputStream buffer = new BufferedOutputStream(file);
	    	    ObjectOutput output = new ObjectOutputStream(buffer)) 
		{		
			output.writeObject(solutionMap);
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
			
	}

}
