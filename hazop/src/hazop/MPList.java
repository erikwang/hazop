package hazop;

import java.util.Vector;

public class MPList {
	private String mplid;
	private String deviceid;
	
	private Vector<MeasuringPoint> mplist;
	
	public MPList(){
		mplist = new Vector<MeasuringPoint>();
	}
	
	public int sizeofMPL(){
		return mplist.size();
	}
	
	public synchronized MeasuringPoint getMP(String _mpid){
		if(mplist != null){
			for(int t=0;t<mplist.size();t++){
				//System.out.println("+"+mplist.get(t).getMpid()+"+"+_mpid);
				if((mplist.get(t).getMpid()).equals(_mpid)){
					return mplist.get(t);
				}
			}	
		}
		return null;
	}
	
	public MeasuringPoint getMPByIndex(int index){
		return mplist.get(index);
	} 
	
	public void addMeasuringPoint(MeasuringPoint _mp){
		mplist.add(_mp);
	}

	public String getMplid() {
		return mplid;
	}

	public void setMplid(String mplid) {
		this.mplid = mplid;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public Vector<MeasuringPoint> getMplist() {
		return mplist;
	}

	public void setMplist(Vector<MeasuringPoint> mplist) {
		this.mplist = mplist;
	}
}
