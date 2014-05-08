package hazop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class DoControlPointData implements Runnable {
	
	private Socket socket;
	BufferedReader input;
	
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("[DCPD Mod]New thread run");
		try{
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}catch(Exception e){
			e.printStackTrace();
		}
		boolean flag = true;
		while(flag){
			try{
				if (input == null){ System.out.println("emtpy");break;}
				String message = input.readLine();
				if (message==null){ System.out.println("emtpy1");break;}
				System.out.println("[DCPD Mod]FROM HAZOP:"+message);
			}catch(Exception e){
				System.out.println("[DCPD Mod] Hazop mod gone");
				try{
					input.close();
					socket.close();
					flag = false;
					break;
				}catch(Exception e1){}
			}finally{
			}
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	

}
