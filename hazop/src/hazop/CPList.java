package hazop;
import java.util.*;

public class CPList {
	private String deviceid;
	private String cplid;
	
	private Vector<ControlPoint> cplist;
	
	private void addCP(ControlPoint _cp){
		cplist.add(_cp);
	}
	
	private void removeCP(ControlPoint _cp){
		cplist.remove(_cp);
	}
	private int sizeofCPL(){
		return cplist.size();
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getCplid() {
		return cplid;
	}

	public void setCplid(String cplid) {
		this.cplid = cplid;
	}

	public Vector<ControlPoint> getCplist() {
		return cplist;
	}

	public void setCplist(Vector<ControlPoint> cplist) {
		this.cplist = cplist;
	}

	
}
