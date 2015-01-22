package main;
import java.util.Scanner;

import model.SolutionManager;
import networking.MyTCPIPServer;


public class Run {
	private MyTCPIPServer server;	

	public static void main(String[] args) {
		Run run = new Run();
		
		System.out.println("Server side");
		String action = "";
		Scanner scanner = new Scanner(System.in);
		do
		{
			System.out.print("\nEnter command: 'start' to initialize the server, 'exit' to close \n");
			action = scanner.nextLine();		
			
			run.handleAction(action);
			
		} while (!(action.equals("exit")));
		scanner.close();
	}
	
	private void handleAction(String action) {
		String sp[] = action.split(" ");
		if (sp[0].equals("start")) {
			
			if (sp.length > 1) {
				int port = Integer.parseInt(sp[1]);
				int numOfClients = Integer.parseInt(sp[2]);
				server = new MyTCPIPServer(port, numOfClients);
			}
			else {
				server = new MyTCPIPServer();
			}						
			server.startServer();							
		}
		else if (sp[0].equals("exit")) {
			server.stopServer();
			SolutionManager.getInstance().saveSolutionsInFile();
		} else {
			System.out.println("Invalid command. Try again");
		}		
	}
	
}
