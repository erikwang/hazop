package hazop;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Listener implements Runnable{
	
	String strport;
	BufferData bufferdata = new BufferData();
	MPList mpl = new MPList();

	
	private String logfile;
	public String getLogfile() {
		return logfile;
	}

	public void setLogfile(String logfile) {
		this.logfile = logfile;
	}

	public void run(){
		run(strport);
	}
	
	public void setPort(String port1){
		strport = port1;
	}
	
	public void run(String instrport) {
		// TODO Auto-generated method stub
		
		int port;
		ServerSocket server_socket;
		try {
			port = Integer.parseInt(instrport);
		}
		catch (Exception e) {
			System.out.println("port = 1500 (default)");
			port = 1500;
		}
		try {
			server_socket = new ServerSocket(port);
			System.out.println("[Listener Socket] Server waiting for client on port " +server_socket.getLocalPort());

			// server infinite loop
			DoListenerDataMod dldmod;
			while(true) {
				//Listener catch a connection, and product a new MOD to do with the data
				Socket socket = server_socket.accept();
				System.out.println("[Listener] New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
				//begin DoListenerDataMod Thread
				dldmod = new DoListenerDataMod();
				dldmod.setMPList(mpl);
				dldmod.setSocket(socket);
				dldmod.setBufferData(bufferdata);
				Thread tdldmod = new Thread(dldmod);
				tdldmod.start();
				// connection closed by client
				/*try {
					socket.close();
					System.out.println("Connection closed by client");
				}
				catch (IOException e) {
					System.out.println(e);
				}*/
			}
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}
	
	
	public String getStrport() {
		return strport;
	}

	public void setStrport(String strport) {
		this.strport = strport;
	}

	/*public Vector<String> getBufferdata() {
		return bufferdata;
	}

	public void setBufferdata(Vector<String> bufferdata) {
		this.bufferdata = bufferdata;
	}*/

	public MPList getMpl() {
		return mpl;
	}

	public void setMpl(MPList mpl) {
		this.mpl = mpl;
	}

	public BufferData getBufferdata() {
		return bufferdata;
	}

	public void setBufferdata(BufferData bufferdata) {
		this.bufferdata = bufferdata;
	}
}
