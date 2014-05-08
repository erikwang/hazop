package hazop;
import java.util.concurrent.*;


public class RunMP {
	
	public void runDeviceMpList(MPList mpl){
		//MPList mpl = _device.getMpl();
		Executor tp = Executors.newFixedThreadPool(mpl.sizeofMPL());
		for(int t=0;t<mpl.sizeofMPL();t++){
			//MeasuringPoint mp = new MeasuringPoint();
			tp.execute(mpl.getMPByIndex(t));
			//System.out.println("New thread for MP"+mpl.getMPByIndex(t).getMpid()+" start.");
		}
	}
}