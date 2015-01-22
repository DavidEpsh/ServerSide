package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.Model;
import model.MyModel;
import model.Problem;
import model.Solution;
import tasks.Task;
/**
 * This class establishes the connection requests sent from clients
 * after a connection to a client is established, we receive an object from the client
 * new model is created and the solution to the problem is sent back to the client
 */
public class ClientHandler implements Task {
	private Socket socket;
	
	public ClientHandler(Socket socket)
	{
		this.socket = socket;
	}

	@Override
	public void doTask() {
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			
			Problem problem = (Problem)in.readObject();
						
			Model model = new MyModel();
			model.selectDomain(problem.getDomainName(), problem.getDomainArgs());
			model.selectAlgorithm(problem.getAlgorithmName());
			model.solveDomain();
			Solution solution = model.getSolution();
			
			out.writeObject(solution);			
			
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}	
	}	
}
