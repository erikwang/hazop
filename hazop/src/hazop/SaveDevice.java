package hazop;

import java.util.HashSet;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class SaveDevice {
	public static void main(String[] args){
		System.out.println("saving...");
		saveDevice();
		System.out.println("out");
	}	
	
	public static boolean saveDevice(){
		SessionFactory sf;
		Session session;
		Configuration config1;
		try{
			config1 = new Configuration().configure();
			sf = config1.buildSessionFactory();
			session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			Device d1 = new Device();
			MeasuringPoint mp = new MeasuringPoint();
			MeasuringPoint mp2 = new MeasuringPoint();
			
			Set mps = new HashSet();
			
			MPList mplist = new XmlReader().getMPList(null,"T01");
			for(int t= 0;t< mplist.sizeofMPL();t++){
				MeasuringPoint mptemp = mplist.getMPByIndex(t);
				mps.add(mptemp);
			}
			
			
			/*mp.setMpid("MPID03");
			mp.setDeviceid("T07");
			mp.setMptype("T");
			mp.setMvalue(100);
			mps.add(mp);
			
			mp2.setMpid("MPID02");
			mp2.setDeviceid("T07");
			mp2.setMptype("L");
			mp2.setMvalue(50);
			mps.add(mp2);
			*/
			
			d1.setMps(mps);
			d1.setDeviceid("T01");
			session.saveOrUpdate(d1);
			tx.commit();
			session.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return true;
	}
}
