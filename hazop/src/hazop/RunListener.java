package hazop;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

public class RunListener {
	public static void main(String args[]) {
		/*SessionFactory sf;
		Configuration config1;
		config1 = new Configuration().configure();
		sf = config1.buildSessionFactory();
		Session session1 = sf.openSession();
		Session session2 = sf.openSession();
		Session session3 = sf.openSession();*/
		new InitialDatabase().clearDatabase(); 
		
		Listener rl = new Listener();
		//rl.setLogfile("c:\\nlog1.txt");
		rl.setPort("1500");
		Thread hl1 = new Thread(rl);
		hl1.start();
		
		ReadDeviceMod rd = new ReadDeviceMod();
		//rd.setSession(session1);
		rd.setMpl(rl.getMpl());
		Thread trd1 = new Thread(rd);
		trd1.start();
		
		Listener rl2 = new Listener();
		rl2.setPort("1501");
		//rl2.setLogfile("c:\\nlog2.txt");
		Thread hl2 = new Thread(rl2);
		hl2.start();
		
		ReadDeviceMod rd2 = new ReadDeviceMod();
		//rd2.setSession(session2);
		rd2.setMpl(rl2.getMpl());
		Thread trd2 = new Thread(rd2);
		trd2.start();
		
		Listener rl3 = new Listener();
		rl3.setPort("1502");
		//rl2.setLogfile("c:\\nlog2.txt");
		Thread hl3 = new Thread(rl3);
		hl3.start();
		
		ReadDeviceMod rd3 = new ReadDeviceMod();
		//rd3.setSession(session3);
		rd3.setMpl(rl3.getMpl());
		Thread trd3 = new Thread(rd3);
		trd3.start();
		
		Listener rl4 = new Listener();
		rl4.setPort("1503");
		//rl2.setLogfile("c:\\nlog2.txt");
		Thread hl4 = new Thread(rl4);
		hl4.start();
		
		ReadDeviceMod rd4 = new ReadDeviceMod();
		rd4.setMpl(rl4.getMpl());
		Thread trd4 = new Thread(rd4);
		trd4.start();
		
		BufferDataReader bufferdatareadermod = new BufferDataReader();
		bufferdatareadermod.setBuffData(rl.getBufferdata());
		bufferdatareadermod.setFilename("c:\\nlog1.txt");
		Thread tbdrmod = new Thread(bufferdatareadermod);
		tbdrmod.start();
		
		BufferDataReader bufferdatareadermod2 = new BufferDataReader();
		bufferdatareadermod2.setBuffData(rl2.getBufferdata());
		bufferdatareadermod2.setFilename("c:\\nlog2.txt");
		Thread tbdrmod2 = new Thread(bufferdatareadermod2);
		tbdrmod2.start();
		
		BufferDataReader bufferdatareadermod3 = new BufferDataReader();
		bufferdatareadermod3.setBuffData(rl3.getBufferdata());
		bufferdatareadermod3.setFilename("c:\\nlog3.txt");
		Thread tbdrmod3 = new Thread(bufferdatareadermod3);
		tbdrmod3.start();
		
		BufferDataReader bufferdatareadermod4 = new BufferDataReader();
		bufferdatareadermod4.setBuffData(rl4.getBufferdata());
		bufferdatareadermod4.setFilename("c:\\nlog4.txt");
		Thread tbdrmod4 = new Thread(bufferdatareadermod4);
		tbdrmod4.start();
	}
}
