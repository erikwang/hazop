package hazop;

import java.util.HashSet;
import java.util.Set;

public class Condition {
	private String csn;
	private String condpara;	
	private double condvalue;
	private String condoper;
	private String mpid;
	private String deviceid;
	private Set cfs = new HashSet();
	public String getCsn() {
		return csn;
	}
	public void setCsn(String csn) {
		this.csn = csn;
	}
	public String getCondpara() {
		return condpara;
	}
	public void setCondpara(String condpara) {
		this.condpara = condpara;
	}
	public double getCondvalue() {
		return condvalue;
	}
	public void setCondvalue(double condvalue) {
		this.condvalue = condvalue;
	}
	public String getCondoper() {
		return condoper;
	}
	public void setCondoper(String condoper) {
		this.condoper = condoper;
	}
	public String getMpid() {
		return mpid;
	}
	public void setMpid(String mpid) {
		this.mpid = mpid;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public Set getCfs() {
		return cfs;
	}
	public void setCfs(Set cfs) {
		this.cfs = cfs;
	}
	public void addCfunctions(String title,String main,String csn,String cfsn) {
	    Cfunctions cf1 = new Cfunctions();
	    cf1.setCftitle(title);
	    cf1.setCfsn(csn);
	    cf1.setCfmain(main);
	    cf1.setCfsn(cfsn);
		cfs.add(cf1);
	    }

	
}
