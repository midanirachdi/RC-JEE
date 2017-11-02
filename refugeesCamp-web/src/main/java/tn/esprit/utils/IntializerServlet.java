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
import tn.esprit.entities.JobOffer;
import tn.esprit.entities.Need;
import tn.esprit.entities.News;
import tn.esprit.entities.Stock;
import tn.esprit.entities.Stock.stockNeedsEnum;
import tn.esprit.entities.StockNotification;
import tn.esprit.entities.User;
import tn.esprit.services.CampService;
import tn.esprit.services.NeedImpl;
import tn.esprit.services.NewsService;
import tn.esprit.services.StockNotificationService;
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
	 @EJB
	 private StockNotificationService sns;
	 @EJB
	 private NeedImpl nes;
	 
	
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		initUsers();
		initStock();
		initNews();
		initCamps();
		initNotification();
		initNeeds();
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
		
		User u4= new Admin();
		u3.setFirstName("salim");
		u3.setLastName("salim");
		u3.setEmail("salim@test.com");
		u3.setDisable(false);
		u3.setPassword("salim");
		us.registerUser(u4);
		
		
		
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
		st.setQteInStock(65);
		st.setStockValue(500);
		ss.add(st);
		
		Stock st2=new Stock();
		st2.setStockType(Stock.stockNeedsEnum.Clothes);
		st2.setNotes("nothing");
		st2.setQteTotal(100);
		st2.setQteInStock(65);
		st2.setStockValue(500);
		ss.add(st2);
		
		Stock st3=new Stock();
		st3.setStockType(Stock.stockNeedsEnum.Medicines);
		st3.setNotes("nothing");
		st3.setQteTotal(100);
		st3.setQteInStock(80);
		st3.setStockValue(500);
		ss.add(st3);
	}
	
	private void initNews() {
		News n=new News();
		n.setAuthor("Salim");
		n.setContent("Test news test news test news");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		n.setDateOfCreation(timestamp);
		ns.add(n);
		
		News n2=new News();
		n2.setAuthor("Salim");
		n2.setContent("222222 Test news test news test news");
		n2.setDateOfCreation(timestamp);
		ns.add(n2);
			
	}
	
	private void initNotification() {
		StockNotification st=new StockNotification();
		st.setMessage("Stock épuiser");
		st.setStatus(0);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		st.setDateOfNotification(timestamp);
		sns.add(st);
	}
	
	private void initNeeds() {
		User u3= new DistrictChef();
		u3.setFirstName("testmen");
		u3.setLastName("testmen");
		u3.setEmail("salim245@esprit.com");
		u3.setDisable(false);
		u3.setPassword("DistrictChef");
		us.registerUser(u3);
		
		Need n=new Need();
		n.setDescription("need test need");
		n.setQuantity(20);
		n.setType(stockNeedsEnum.Clothes);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		n.setDate(timestamp);
		n.setDischef((DistrictChef)u3);
		nes.addNeed(n);
		
		Need n2=new Need();
		n2.setDescription("2 need test need");
		n2.setQuantity(50);
		n2.setType(stockNeedsEnum.Water);
		n2.setDate(timestamp);
		n2.setDischef((DistrictChef)u3);
		nes.addNeed(n2);
		
		Need n3=new Need();
		n3.setDescription("2 need test need");
		n3.setQuantity(35);
		n3.setType(stockNeedsEnum.Medicines);
		n3.setDate(timestamp);
		n3.setDischef((DistrictChef)u3);
		nes.addNeed(n3);
		
		Need n4=new Need();
		n4.setDescription("2 need test need");
		n4.setQuantity(35);
		n4.setType(stockNeedsEnum.Clothes);
		n4.setDate(timestamp);
		n4.setStatus(-1);
		n4.setDischef((DistrictChef)u3);
		nes.addNeed(n4);
		
		Need n5=new Need();
		n5.setDescription("2 need test need");
		n5.setQuantity(35);
		n5.setType(stockNeedsEnum.Tents);
		n5.setDate(timestamp);
		n5.setStatus(1);
		n5.setDischef((DistrictChef)u3);
		nes.addNeed(n5);
		
	}
	private void initJobOffers(){
		//TODO midani
	}
	
	
}
