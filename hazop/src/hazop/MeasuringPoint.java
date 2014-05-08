package hazop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MeasuringPoint implements Runnable{
	int count = 0;
	Socket socket;
	public void run(){
		// Begin a thread
		this.initSocket();
		this.sendData();
	}
	//private int devicesn;
	
	private Device device;
	private String mpsn;
	//communicate socket
	private String porttolistener;
	private String listenerip;
	//Device id and measuring point id
	private String deviceid;
	private String mpid;
	
	//type of the measuring point
	private String mptype;
	
	//property of a measuring point 
	private double mvalue;
	
	private double thresholdvalue;
	
	
	public double getThresholdvalue() {
		return thresholdvalue;
	}
	public void setThresholdvalue(double thresholdvalue) {
		this.thresholdvalue = thresholdvalue;
	}
	public void setMvalue(double mvalue) {
		this.mvalue = mvalue;
	}
	public String getPorttolistener() {
		return porttolistener;
	}
	public void setPorttolistener(String porttolistener) {
		this.porttolistener = porttolistener;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getMpid() {
		return mpid;
	}
	public void setMpid(String mpid) {
		this.mpid = mpid;
	}
	
	public double getMvalue() {
		return mvalue;
	}
	public void setMvalue(float mvalue) {
		this.mvalue = mvalue;
	}
	public String getMptype() {
		return mptype;
	}
	public void setMptype(String mptype) {
		this.mptype = mptype;
	}
	
	public void initSocket(){
		int port;
		String server = listenerip;
		//BufferedReader input;
		// read arguments
			try {
				port = Integer.parseInt(porttolistener);
			}
			catch (Exception e) {
				System.out.println("Exception: server port = 1500 (default)");
				port = 1500;
			}
		// connect to server
			boolean flag = false;
			while(! flag){
				try {
					socket = new Socket(server, port);
					System.out.println("[MP]Connected with server " +socket.getInetAddress() +":" + socket.getPort());
					flag = true;
				}
				catch(Exception e){
					System.out.println("[MP]Cann't connect to listener, try again after 5 sec");
					try{Thread.sleep(5000);}
					catch(Exception e1){
						e1.printStackTrace();
					}
				}
			}
		
	}
	
	public void sendData(){
		PrintWriter output;	
		String lineToBeSent;	
			try {
				//input = new BufferedReader(new InputStreamReader(System.in));
				output = new PrintWriter(socket.getOutputStream(),true);
				// get user input and transmit it to server
				System.out.println("new mp created with mpid"+mpid);
				for(;;){
					if(socket.isOutputShutdown()){
						System.out.println("[MP] socket closed with listener, try again after 5 secs");
						try{Thread.sleep(5000);}
						catch (Exception e){};
						initSocket();
					}
					lineToBeSent = deviceid+":"+mpid+":"+getData()+":"+mptype;
					output.println(lineToBeSent);
					try{Thread.sleep(1000);}
						catch(Exception e){}
					}
			}
			catch (IOException e) {
				System.out.println(e);
			}
			try {
				socket.close();
			}
			catch (IOException e) {
				System.out.println(e);
			}
		}
	
	private double getData(){
		//Get Data from sensor
		if(mptype.equals("T")){
			java.util.Random r=new java.util.Random();
			float  t = 0;
			t = r.nextInt(71);
			return t;
		}else{
			if(mptype.equals("P")){
			java.util.Random r=new java.util.Random();
			float  t = 0;
			t = r.nextInt(4);
			return t;
			}else{
				return 0;
			}
			}
		}

	public String getListenerip() {
		return listenerip;
	}
	public void setListenerip(String listenerip) {
		this.listenerip = listenerip;
	}
	public String getMpsn() {
		return mpsn;
	}
	public void setMpsn(String mpsn) {
		this.mpsn = mpsn;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	/*public int getDevicesn() {
		return devicesn;
	}
	public void setDevicesn(int devicesn) {
		this.devicesn = devicesn;
	}*/
}


 