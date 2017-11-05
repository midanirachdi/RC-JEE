package tn.esprit.utils;


import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import tn.esprit.entities.Admin;
import tn.esprit.entities.Camp;
import tn.esprit.entities.CampChef;
import tn.esprit.entities.DistrictChef;
import tn.esprit.entities.News;
import tn.esprit.entities.Stock;
import tn.esprit.entities.User;
import tn.esprit.entities.Volunteer;
import tn.esprit.services.CampService;
import tn.esprit.services.NewsService;
import tn.esprit.services.StockService;
import tn.esprit.services.UserService;


@WebListener
public class IntializerServlet implements javax.servlet.ServletContextListener  {

	
	 @EJB 
	 private UserService us;
	 @EJB
	 private StockService ss;
	 @EJB
	 private NewsService ns;
	 @EJB
	 private CampService cs;
	
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		initUsers();
		initStock();
		initNews();
		initCamps();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	
	private void initUsers(){
		User u=new Admin();
		u.setFirstName("Mohamad");
		u.setLastName("khreibi");
		u.setEmail("khreibi.mohamad@gmail.com");
		u.setDisable(false);
		u.setPassword("superadmin");
		us.registerUser(u);
		
		User u1= new Admin();
		u1.setFirstName("khalil");
		u1.setLastName("fakhfekh");
		u1.setEmail("khalil.fakhfekh@esprit.com");
		u1.setDisable(false);
		u1.setPassword("superadmin");
		us.registerUser(u1);
		
		User u2= new CampChef();
		u2.setFirstName("mideni");
		u2.setLastName("rachdi");
		u2.setEmail("mideni.rachdi@esprit.com");
		u2.setDisable(false);
		u2.setPassword("CampChef");
		us.registerUser(u2);
		
		User u3= new DistrictChef();
		u3.setFirstName("salim");
		u3.setLastName("salim");
		u3.setEmail("salim.salim@esprit.com");
		u3.setDisable(false);
		u3.setPassword("DistrictChef");
		us.registerUser(u3);
		
		
		User u4= new DistrictChef();
		u4.setFirstName("hafsa");
		u4.setLastName("mtu");
		u4.setEmail("hafsa.mtu@esprit.com");
		u4.setDisable(false);
		u4.setPassword("DistrictChef");
		us.registerUser(u4);
		
		
		User u5= new Volunteer();
		u5.setFirstName("mohamed amin");
		u5.setLastName("garci");
		u5.setEmail("mohamedamine.garci@esprit.tn");
		u5.setDisable(false);
		u5.setPassword("Volunteer");
		us.registerUser(u5);
		
		User u6= new Volunteer();
		u6.setFirstName("maysen");
		u6.setLastName("ayed");
		u6.setEmail("maysen.ayed@esprit.com");
		u6.setDisable(false);
		u6.setPassword("Volunteer");
		us.registerUser(u6);
		
		
		User u7= new Volunteer();
		u7.setFirstName("ahlem");
		u7.setLastName("jerbi");
		u7.setEmail("ahlem.jerbi@esprit.com");
		u7.setDisable(false);
		u7.setPassword("Volunteer");
		us.registerUser(u7);
		
		
		
		User u8= new Volunteer();
		u8.setFirstName("mohamed");
		u8.setLastName("salim");
		u8.setEmail("mohamed.salim@esprit.com");
		u8.setDisable(false);
		u8.setPassword("Volunteer");
		us.registerUser(u8);
	}
	private void initCamps(){
		Camp c1=new Camp("Camp1", true, "TN", 250, new Date());
		Camp c2=new Camp("Camp2", true, "FR", 500, new Date());
		Camp c3=new Camp("Camp3", false, "SU", 350, new Date());
		CampChef cf=(CampChef)us.find(3);
		Camp cwc=new Camp("relatedCamp", cf, "US", 1000, new Date());
		cs.add(c1);
		cs.add(c2);
		cs.add(c3);
		cs.add(cwc);
	}
	private void initStock() {
		Stock st=new Stock();
		st.setStockType(Stock.stockNeedsEnum.Water);
		st.setNotes("nothing");
		st.setQteTotal(100);
		st.setQteInStock(100);
		st.setStockValue(500);
		ss.add(st);
	}
	
	private void initNews() {
		News n=new News();
		n.setAuthor("Salim");
		n.setContent("Test news test news test news");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		n.setDateOfCreation(timestamp);
		ns.add(n);
			
	}
	
}
