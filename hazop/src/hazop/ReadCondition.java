package hazop;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ReadCondition {

	
	public static void main(String[] args) {
		getCondition();
		//addCondition();
	}
	
	public static void getCondition(){
		SessionFactory sf;
		Session session;
		Session session1;
		Configuration config1;
		Condition cond = new Condition();
		Set set1;
		try {
	    		List <Object> mpl;
        		//_mpid="T1t01";
	    		config1 = new Configuration().configure();
	    		session = HibernateSessionFactory.getSession();
	    		mpl = session.createQuery("FROM Condition").list();
	            Transaction tx = session.beginTransaction();
	            tx.commit();
	            
	            if(mpl.size() !=0){
	       			for(int tt=0;tt<mpl.size();tt++){
	       				cond = (Condition)mpl.get(tt);
		       			String str = cond.getCondpara()+cond.getCondoper()+cond.getCondvalue();
		       			//byte[]   bytes   =   str.getBytes("ISO-8859-1");   
		       		    //String sstr   =   new   String(bytes,"UTF-8");
		       		    System.out.println(str);
		       		    //Set st = cond.getCfs();
		       		    Object[] ccond = cond.getCfs().toArray();
		       		    for(int t=0;t<ccond.length;t++){
		       		    	String cfm1 = ((Cfunctions)ccond[t]).getCfmain();
		       		    	byte[]   bytes   =   cfm1.getBytes("ISO-8859-1");   
		       		    	String scfm1  =   new   String(bytes,"gb2312"); 
		       		    	System.out.println("*"+((Cfunctions)ccond[t]).getCftitle()+scfm1);	
		       		    }
	       			}    
	       		}else{  }
		       	 session.close();
			            /*session1 = HibernateSessionFactory.getSession();
			    		mpl = session1.createQuery("FROM Cfunctions WHERE csn = '"+cond.getCsn()+"'").list();
			            Transaction tx1 = session1.beginTransaction();
			            tx1.commit();
			            session1.close();
			            for(int t=0;t<mpl.size();t++){
			            	Cfunctions cfun = (Cfunctions)mpl.get(t); 
			            	System.out.println(cfun.getCftitle()+cfun.getCfmain());
			            	
			            }*/
	      } catch (Exception e) {
            e.printStackTrace();
	      }
	}
	
	public static void addCondition(){
		SessionFactory sf;
		Session session;
		Session session1;
		Configuration config1;
		Condition cond = new Condition();
		Set set1;
		cond.setMpid("R200t01");
		cond.setCondoper("=");
		cond.setCondpara("T");
		cond.setCondvalue(165);
		cond.setDeviceid("R200");
		cond.setCsn("c0004");
		String sstr = "";
		try{
			//sstr = new String("test main~~~~1,测试文字1".getBytes("ISO-8859-1"), "UTF-8");
			//sstr = new String("test main~~~~1,测试文字1");
			sstr = "中";
		}catch(Exception e){}
		cond.addCfunctions("test main field",sstr,"c0004","7");
		//cond.addCfunctions("test main field","test main2,测试文字2","c0004","8");
		cond.addCfunctions("test main field",sstr,"c0004","9");
		session = HibernateSessionFactory.getSession();
        session.saveOrUpdate(cond);
		Transaction tx = session.beginTransaction();
        tx.commit();
        session.close();
        System.out.println("!新记录添加成功");
	}
}
