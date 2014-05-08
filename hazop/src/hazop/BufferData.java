package hazop;

import java.util.Vector;

public  class BufferData {
	Vector<String> buffdata = new Vector<String>();
	public BufferData(){
		buffdata = new Vector<String>();
	}
	public void saveLogInMemory(String log){
		buffdata.add(log);
	}
	public String readData(){
		return buffdata.get(0);
	}
	public int getSize(){
		return buffdata.size();
	}
	public String getStr(int t){
		return buffdata.get(t);
	}
	public void clearData(){
		buffdata = new Vector<String>();
	}
	
}