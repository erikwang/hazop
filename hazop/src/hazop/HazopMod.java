package hazop;

import java.io.PrintWriter;
import java.util.Date;
import java.net.Socket;


public class HazopMod {
	PrintWriter output;
	Socket sockettocp = null;
	MeasuringPoint mp = new MeasuringPoint();

	public HazopMod(){
		//initSocketToCP();
	}
	
	public boolean checkThreshold(){
		if(mp.getMvalue() > mp.getThresholdvalue()){
			//saveToAlertLog();
			System.out.println("===+++A L E R T+++=== Overflow threshold value! "+mp.getDeviceid()+"."+mp.getMpid()+" "+mp.getMptype()+" "+mp.getMvalue()+" "+mp.getThresholdvalue());
			//callCP();
			return false;
		}else{
			/*System.out.println(mp.getMpid());
			System.out.println("MP value:"+mp.getMvalue());
			System.out.println("Thredshold:"+mp.getThresholdvalue());*/
			return true;
		}
	}
	
	public String getHazopAlertString(){
		return  "<HAZOP alert> "+new Date()+" "+mp.getMpid()+" "+mp.getMvalue()+" "+mp.getThresholdvalue();
	}
	
	public String getHazopAlterLogString(){
		return ">Caution: "+new Date()+" "+mp.getDeviceid()+" alert for "+mp.getDeviceid()+" "+mp.getMptype()+" "+mp.getMvalue()+"\n";
	}
	
	public MeasuringPoint getMp() {
		return mp;
	}

	public void setMp(MeasuringPoint mp) {
		this.mp = mp;
	}

}
