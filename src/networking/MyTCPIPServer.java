package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import config.HandleProperties;
import config.ServerProperties;
import tasks.TaskRunnable;
/**
 * This class is in charge of managing the servers properties, 
 * a thread is assigned to each new client connection request
 * @author David
 *
 */
public class MyTCPIPServer {
	private ServerSocket server;
	private ExecutorService executor;
	private Thread thread;
	private int port;
	private int numOfClients;
		
	public MyTCPIPServer() {
		ServerProperties properties = HandleProperties.readProperties();
		this.port = properties.getPort();
		this.numOfClients = properties.getNumOfClients();
	}	
	
	public MyTCPIPServer(int port, int numOfClients) {
		this.port = port;
		this.numOfClients = numOfClients;
	}
	
	public void startServer() {
		try {
			server = new ServerSocket(port);			
			server.setSoTimeout(2000);
			
			executor = Executors.newFixedThreadPool(numOfClients);
		
			thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while(!Thread.interrupted()) {
					try {
						Socket socket = server.accept();
						System.out.println("Got new connection");
						
						if (socket != null) {
							ClientHandler handler = new ClientHandler(socket);
							executor.submit(new TaskRunnable(handler));
						}
					} catch (SocketTimeoutException e){
						
					} catch (IOException e) {
						e.printStackTrace();
					}catch (Exception e) {
					}
				}		
				
				executor.shutdownNow();
				
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			}			
		});
			thread.start();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void stopServer() {		
			thread.interrupt();
	}
}
