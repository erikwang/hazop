package hazop;


public class RunCP {
	public static void main(String[] args) {
		ControlPoint cp = new ControlPoint();
		Thread tcp = new Thread(cp);
		tcp.start();
	}
}
