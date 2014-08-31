package multiThread;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HandleAClient implements Runnable{
	private Socket socket;
	public HandleAClient(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		try{
			DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
			DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
			String str = null;
			BufferedReader bf = new BufferedReader(new InputStreamReader(inputFromClient));
			while(true){

				str = bf.readLine();
				if(str == null)
					return;
				if(str.equals(""))
					break;
				System.out.println(str);
			}
			String ret = "hello, NJU!\n";
			String response = "HTTP/1.1 200 OK\r\nContent-Length: "+ret.length()+"Connection: close\r\n\r\n"+ret;
			outputToClient.write(response.getBytes());
			outputToClient.flush();
			outputToClient.close();
			System.out.println("send over\n");
		}catch(Exception e){e.printStackTrace();}
	}
}
