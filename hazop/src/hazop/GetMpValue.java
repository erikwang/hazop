package hazop;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class GetMpValue {
	public MeasuringPoint mp;
	 Session session;
	
	public int getMpValueFromDb(){
		String _mpid;
		Configuration config1;
        try {
	    		List <Object> mpl;
        		_mpid="T1t01";
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
		return new Double(mp.getMvalue()).intValue();
	}
	
	/*public static void main(String args[]){
		double temp = new GetMpValue().getMpValueFromDb("T1t01");
		System.out.println(temp);
	}*/
	
}
