package hazop;
import java.util.*;

public class ThresholdList {
	private String mpid;
	Vector<String> threshold;
	public String getMpid() {
		return mpid;
	}
	public void setMpid(String mpid) {
		this.mpid = mpid;
	}
	public Vector<String> getThreshold() {
		return threshold;
	}
	public void setThreshold(Vector<String> threshold) {
		this.threshold = threshold;
	}
}
