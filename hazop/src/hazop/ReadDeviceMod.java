package hazop;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ReadDeviceMod implements Runnable{
	MPList mpl;
	SessionFactory sf;
	Session session;
	Configuration config1;
	Device tdevice = new Device();
	public MPList getMpl() {
		return mpl;
	}
	public MeasuringPoint mp;
	public synchronized void setMpl(MPList _mpl) {
		this.mpl = _mpl;
		//System.out.println("*&***"+mpl.sizeofMPL());
	}

	public void run(){
		
		try{
				config1 = new Configuration().configure();
				sf = config1.buildSessionFactory();
				while(true){
				showDeviceData();
				Thread.sleep(5000);
				}
		}catch(Exception e){
		}
	}
	synchronized void showDeviceData(){
		Set mps = new HashSet();
		try{			
			session = sf.openSession();
			Transaction tx = session.beginTransaction();
			if(mpl.sizeofMPL() > 0){
				for(int t=0;t<mpl.sizeofMPL();t++){
					//System.out.println("MPID: "+mpl.getMPByIndex(t).getMpid()+" | "+mpl.getMPByIndex(t).getMptype()+" "+mpl.getMPByIndex(t).getMvalue()+" | TH "+mpl.getMPByIndex(t).getThresholdvalue());
					MeasuringPoint mptemp = mpl.getMPByIndex(t);
					if(mptemp.getMpsn() == null){
						System.out.println("Generated a new mpsn");
						mptemp.setMpsn("mp"+mpl.getDeviceid()+new Random().nextInt(1000000));	
					}
					
					mps.add(mptemp);
				}
				if(tdevice.getDevicesn() == null){
					String dsn = "de"+mpl.getDeviceid()+new Random().nextInt(1000000);
					tdevice.setDevicesn(dsn);
				}
				tdevice.setDeviceid(mpl.getDeviceid());
				tdevice.setMps(mps);
				session.saveOrUpdate(tdevice); //persist the data
				tx.commit();
				session.close();
				System.out.println("----------------------Measuring point list report: "+mpl.getDeviceid()+"."+mpl.getMplid());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public double getMpValueFromDb(String _mpid){
		//String _mpid;
		Configuration config1;
        try {
	    		List <Object> mpl;
        		//_mpid="T1t01";
	    		config1 = new Configuration().configure();
	    		session = HibernateSessionFactory.getSession();
	    		mpl = session.createQuery("FROM MeasuringPoint WHERE mpid = '"+_mpid+"'").list();
	            Transaction tx = session.beginTransaction();
	            tx.commit();
	            session.close();
	            if(mpl.size() !=0){
	       			mp = (MeasuringPoint)mpl.get(0);
	       		}else{
	       			return 0;
	       		}
        } catch (Exception e) {
            e.printStackTrace();
        }
		//return new Double(mp.getMvalue()).intValue();
        //return new Double(mp.getMvalue());
        System.out.println(mp.getMvalue());
        return mp.getMvalue();
		
	}
	public double getValueByMID(String mpid){
		return mpl.getMP(mpid).getMvalue();
	}
	
	public int getTest(){
		Random r=new Random();
		return r.nextInt(100);
	}
	
	public String testAjax(){
		return "done";
	}
}