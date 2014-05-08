package hazop;
import java.util.*;

public class RunDevice {
		
	public static MPList getMpl(String _deviceid){
		//MPList mpl = new MPList();
		return new XmlReader().getMPList(null,_deviceid);
	}
	
	public CPList getCpl(){
		CPList cpl = new CPList();
		return cpl;
	}
	
	
	public static void main(String[] args) {
		Device device;
		Vector<String> dids =  new XmlReader().getDeviceIDs();
		for(int i=0;i<dids.size();i++){
			String did = dids.get(i);
			device = new Device();
			MPList mplist = new MPList();
			mplist = new XmlReader().getMPList(mplist,did);
			System.out.println("mpl from xml size:"+mplist.sizeofMPL());
			//device.setMpl(mplist);
			for(int t=0;t<mplist.sizeofMPL();t++){
				System.out.println("~"+mplist.getMPByIndex(t).getDeviceid());
			}
			RunMP runmp = new RunMP();
			//runmp.runDeviceMpList(device);
			runmp.runDeviceMpList(mplist);
		}
		/*ControlPoint cp = new ControlPoint();
		Thread tcp = new Thread(cp);
		tcp.start();*/
	}
	
}
