package hazop;
import java.util.*;

public class Device {
	
	private String deviceid;
	private String devicesn;	
	private CPList cpl;
	private MPList mpl;
	private Set mps;


	public String getDevicesn() {
		return devicesn;
	}
	public void setDevicesn(String devicesn) {
		this.devicesn = devicesn;
	}
	public CPList getCpl() {
		return cpl;
	}
	public void setCpl(CPList cpl) {
		this.cpl = cpl;
	}
	public MPList getMpl() {
		return mpl;
	}
	public void setMpl(MPList mp) {
		this.mpl = mp;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public Set getMps() {
		return mps;
	}
	public void setMps(Set mps) {
		this.mps = mps;
	}
	
	
}
