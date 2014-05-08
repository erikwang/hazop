package hazop;
import java.util.*;
import org.jdom.input.*;   
import org.jdom.Document;
import org.jdom.Element;

public class XmlReader {
	
	public String getCPHost(){
		String filename = "MP.xml";
		SAXBuilder   sb   =   new   SAXBuilder();   
		Document   doc   =   null;
		try{
			doc   =   sb.build(filename);
		}catch(Exception e){
			e.printStackTrace();
		}   
		Element  em = doc.getRootElement().getChild("CP");
		return em.getAttributeValue("cphost");
	}
	
	public int getNumberOfDevices(){
		String filename = "MP.xml";
		SAXBuilder   sb   =   new   SAXBuilder();   
		Document   doc   =   null;   
		try{
			doc   =   sb.build(filename);
		}catch(Exception e){
			e.printStackTrace();
		}   
		List  mpleml = doc.getRootElement().getChild("Device").getChildren("MPList");
		return mpleml.size();
	}
	
	
	public Device getDevice(String did){
		Device device = new Device();
		return device;
	}
	
	public Vector<String> getDeviceIDs(){
		Vector<String> dids = new Vector<String>();
		MPList mplist = new MPList();
		String filename = "MP.xml";
		SAXBuilder   sb   =   new   SAXBuilder();   
		Document   doc   =   null;   
		try{
			doc   =   sb.build(filename);
		}catch(Exception e){
			e.printStackTrace();
		}   
		List  eml = doc.getRootElement().getChildren("Device");
		for(int t=0;t<eml.size();t++){
			Element em = (Element)eml.get(t);
			dids.add(em.getAttributeValue("DEVICEID"));
		}
		return dids;
	}
	
	
	public MPList getMPList(MPList _mpl,String DeviceID){
		MPList mplist;
		if(_mpl == null){
			mplist = new MPList();
		}else{
			mplist = _mpl;
		}
		String filename = "MP.xml";
		SAXBuilder   sb   =   new   SAXBuilder();   
		Document   doc   =   null;   
		try{
			doc   =   sb.build(filename);
		}catch(Exception e){
			e.printStackTrace();
		}   
		List mpleml = null;
		List  dl = doc.getRootElement().getChildren("Device");
		for(int t=0;t<dl.size();t++){
			Element emdev = (Element)dl.get(t);
			if (emdev.getAttributeValue("DEVICEID").equals(DeviceID)){
				mpleml = emdev.getChildren("MPList");
			}
		}
		for(int i = 0;i<mpleml.size();i++){
			Element mplem = (Element)mpleml.get(i);
			if(mplem.getAttributeValue("DEVICEID").equals(DeviceID)){
				String ip = mplem.getAttributeValue("IP");
				List eml = mplem.getChildren("MeasuringPoint");
				mplist.setDeviceid(mplem.getAttributeValue("DEVICEID"));
				mplist.setMplid(mplem.getAttributeValue("DEVICEID")+mplem.getAttributeValue("PORT"));
				for(int t=0;t<eml.size();t++){
					Element em = (Element)eml.get(t);
					MeasuringPoint mp = new MeasuringPoint();
					mp.setListenerip(ip);
					mp.setDeviceid(em.getAttributeValue("DeviceID"));
					mp.setMpid(em.getAttributeValue("MPID"));
					mp.setMptype(em.getAttributeValue("MPTYPE"));
					mp.setPorttolistener(em.getAttributeValue("Porttolistener"));
					mp.setThresholdvalue(new Double(em.getAttributeValue("ThresholdValue")).doubleValue());
					mplist.addMeasuringPoint(mp);
				}
			}
		}
		return mplist;
	}
	
	public double getThresholdValue(String _mpid){
		MPList mplist = new MPList();
		String filename = "MP.xml";
		SAXBuilder   sb   =   new   SAXBuilder();   
		Document   doc   =   null;   
		try{
			doc   =   sb.build(filename);
		}catch(Exception e){
			e.printStackTrace();
		}   
		List eml = doc.getRootElement().getChildren("Device");
		
		for(int i=0;i<eml.size();i++){
			List eml2 = ((Element)eml.get(i)).getChildren("MPList");
			for(int t=0;t<eml2.size();t++){
				List eml3 = ((Element)eml2.get(t)).getChildren("MeasuringPoint");
				for(int tt=0;tt<eml3.size();tt++){
					Element em = (Element)eml3.get(tt);
					if(em.getAttributeValue("MPID").equals(_mpid)){
						return new Double(em.getAttributeValue("ThresholdValue")).doubleValue();	
					}
				}
			}
		}
		return 0;
	}
}
