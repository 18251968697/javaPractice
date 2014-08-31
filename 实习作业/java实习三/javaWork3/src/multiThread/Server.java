package multiThread;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serverSocket;
	public Server(){
		try{
			serverSocket = new ServerSocket(2323, 100);
			System.out.println("server started");
		}catch(Exception e){e.printStackTrace();}
	}
	
	public void server(){
		int clientNo = 1;
		while(true){
			Socket socket = null;
			try{
				socket = serverSocket.accept();
				System.out.println("New connection accepted "
						+socket.getInetAddress()+": "+socket.getPort());
				HandleAClient task = new HandleAClient(socket);
				new Thread(task).start();
			}catch(Exception e){e.printStackTrace();}
		}
	}
	public static void main(String args[]){
		Server server = new Server();
		server.server();
	}
}
