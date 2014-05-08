package hazop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ControlPoint implements Runnable{
	private String deviceid;
	private String cpid;
	private String strport;
	
	private String commandid;
	BufferedReader input;

	public void run(){
		// Begin a thread
		this.listenCommand(strport);
	}
	
	public void listenCommand(String _strport){
		int port;
		ServerSocket server_socket;
		try {
			port = Integer.parseInt(_strport);
		}
		catch (Exception e) {
			System.out.println("port = 7000 (default)");
			port = 7000;
		}
		try {
			server_socket = new ServerSocket(port);
			System.out.println("[cp] Control Point waiting for listener's on port " +server_socket.getLocalPort());

			// server infinite loop
			
			while(true) {
				//Listener catch a connection, and product a new MOD to do with the data
				Socket socket = server_socket.accept();
				System.out.println("[cp] New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
				
				DoControlPointData dcpdmod = new DoControlPointData(); 
				dcpdmod.setSocket(socket);
				Thread tdcpdmod = new Thread(dcpdmod);
				tdcpdmod.start();
			}
		}
		catch (IOException e) {
			System.out.println(e);
		}

	}
	
	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getCpid() {
		return cpid;
	}

	public void setCpid(String cpid) {
		this.cpid = cpid;
	}

	public String getCommandid() {
		return commandid;
	}

	public void setCommandid(String commandid) {
		this.commandid = commandid;
	}
		
}
