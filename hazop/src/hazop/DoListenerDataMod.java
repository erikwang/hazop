package hazop;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;


public class DoListenerDataMod implements Runnable {
	
	BufferedReader input;
	Socket socket;
	Socket sockettocp;
	HazopMod hazopmod = new HazopMod();
	PrintWriter output;
	
	MPList mplist =new MPList();
	BufferData bufferdata = new BufferData();
	//Vector<String> bufferdata = new Vector<String>();
	
	public void setMPList(MPList _mpl){
		this.mplist = _mpl;
	}
	
	public void setSocket(Socket _socket){
		this.socket = _socket;
	}
	
	public void setBufferData(BufferData _bufferdata){
		this.bufferdata = _bufferdata;
	}
	
	/*public void setBufferData(Vector<String> _bufferdata){
		this.bufferdata = _bufferdata;
	}*/
	

	
	public void run() {
		// TODO Auto-generated method stub
		initSocketToCP();
		readData();
	}
	
	public synchronized void readData(){
		MeasuringPoint mp = new MeasuringPoint();
		int localsocket = socket.getLocalPort();
		try{
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}catch(Exception e){
			e.printStackTrace();
		}
		// print received data
			try {
				
				while(true) {
					/*for (int ii = 0 ; ii < mplist.sizeofMPL(); ii ++){
						System.out.println("~"+mplist.getMPByIndex(ii).getMpid());
					}*/
					if(! socket.isConnected()){System.out.println("!!!!!Find a socket disconnected!");return;}
					if (input == null){ break;}
					String message = input.readLine();
					if (message==null){ break;}
					bufferdata.saveLogInMemory(message);
					//bufferdata.add(message);
					String datavalue = message.split(":")[2];
					//mplist = new XmlReader().getMPList(mplist,message.split(":")[0]);
					//System.out.println("mplist size is"+mplist.sizeofMPL());
					mp = mplist.getMP(message.split(":")[1]);
					if(mp  == null){
						if(mplist.getMplid()==null){
							mplist.setDeviceid(message.split(":")[0]);
							mplist.setMplid(message.split(":")[0]+socket.getLocalPort());
							System.out.println("[MPL] mpl id setted."+mplist.getMplid());
						}
						mp = new MeasuringPoint();
						mp.setDeviceid(message.split(":")[0]);
						mp.setMpid(message.split(":")[1]);
						mp.setThresholdvalue(new XmlReader().getThresholdValue(message.split(":")[1]));
						System.out.println(message.split(":")[1]);
						System.out.println("[mp]"+mp.getThresholdvalue());
						mplist.addMeasuringPoint(mp);
						System.out.println("[MP] new mp created "+message.split(":")[1]);
					}
						mp.setDeviceid(message.split(":")[0]); 
						mp.setMptype(message.split(":")[3]);
						mp.setMvalue((new Float(datavalue).floatValue()));
						//System.out.println("*"+mplist.sizeofMPL());
						hazopmod.setMp(mp);
						
						if(! hazopmod.checkThreshold()){
							callCP();
							saveToAlertLog();
						}
					
				}	
				socket.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
	}

	/*public Vector<String> getBufferdata() {
		return bufferdata;
	}*/

	/*public void setBufferdata(Vector<String> bufferdata) {
		this.bufferdata = bufferdata;
	}*/
	public void initSocketToCP(){
		int port;
		String server = new XmlReader().getCPHost();
		try {
			port = Integer.parseInt("7000");
		}
		catch (Exception e) {
			System.out.println("Exception: server port = 7000 (default)");
			port = 7000;
		}
	// connect to server
		boolean flag = false;
		while(! flag){
			try {
				sockettocp = new Socket(server, port);
				System.out.println("[CP client] >Connected with server " +sockettocp.getInetAddress() +":" + sockettocp.getPort());
				flag = true;
				output = new PrintWriter(sockettocp.getOutputStream(),true);
			}
			
			catch (Exception e){
				//e.printStackTrace();
				System.out.println("[CP client] cann't connect to cp, try again after 5 sec");
				try{Thread.sleep(5000);}
				catch(Exception e1){
					e1.printStackTrace();
				}
			}
		}
	}
	
	public void callCP(){
		// read arguments
		 	//initSocketToCP();	
		 	try {
		 		//lineToBeSent = "HAZOP alert!!!!!!"+mp.getMpid();
				output.println(hazopmod.getHazopAlertString());
			}
			catch (Exception e) {
				System.out.println(e);
			}finally{}
	}
	
	public synchronized void saveToAlertLog(){
		try{
			FileOutputStream fos = new FileOutputStream("c:\\mpalert"+mplist.getDeviceid()+".log",true);
			//FileOutputStream fos = new FileOutputStream("c:\\mpalert"+".log",true);
			String logMain = hazopmod.getHazopAlterLogString();
			fos.write(logMain.getBytes());
			fos.close();
		}catch(Exception e){
			
		}finally{
			
		}
	}
	
	
	public BufferData getBufferdata() {
		return bufferdata;
	}

	public MPList getMplist() {
		return mplist;
	}

	public void setMplist(MPList mplist) {
		this.mplist = mplist;
	}

}