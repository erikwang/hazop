package hazop;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.*;

public class InitialDatabase {
	Session session;
	Transaction tx = null;
	
	public void clearDatabase(){
		System.out.println("[Init] Clearing database datas...");
		try{
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			String hql = "delete from MeasuringPoint"; 
			Query q = session.createQuery(hql); 
			q.executeUpdate();
			hql = "delete from Device";
			q = session.createQuery(hql); 
			q.executeUpdate();
			/*hql = "delete from devicemeasuringpoint";
			q = session.createQuery(hql); 
			q.executeUpdate();*/
			tx.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("[Init] Clearing database done.");
	}
}
