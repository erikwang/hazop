package hazop;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class BufferDataReader implements  Runnable{
	
	BufferData buff1 = new BufferData();
	//static Vector<String> buff1 = new Vector<String>();
	File tfile;
	public void setFilename(String filename){
		tfile = new File(filename);
	}
	
	public void run(){
			try{
					while(true){
					saveToFile();
					Thread.sleep(2000);
					}
			}catch(Exception e){
			}
	}
	
	synchronized void saveToFile(){
		try{
			FileOutputStream fos = new FileOutputStream(tfile,true);
			if (buff1 != null){
				for(int t=0;t<buff1.getSize();t++){
					String logMain = new Date()+"> "+ buff1.getStr(t)+"\n";
					//save to file?
					//fos.write(logMain.getBytes());
				}
			}
			if(buff1.getSize() != 0){
				//System.out.println("buff size:"+buff1.getSize());
				clearBuff();
				//System.out.println("buff size after clear:"+buff1.getSize());
			}
			//buff1.clearData();
			
		}catch(Exception e){
			System.out.println("!!!!!!!!!!!!!!!!!");
			e.printStackTrace();
		}
	}
	
	public synchronized void setBuffData(BufferData inbuff){
		buff1 = inbuff;		
	}
	/*public void setBuffData(Vector<String> inbuff){
		buff1 = inbuff;		
	}*/
	
	synchronized void clearBuff(){
		buff1.clearData();
		//System.out.println("Buff cleared."+buff1.getSize());
	}
	
}
