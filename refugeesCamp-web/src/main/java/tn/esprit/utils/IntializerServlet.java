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
import tn.esprit.entities.Refugee;
import tn.esprit.entities.Stock;
import tn.esprit.entities.Stock.stockNeedsEnum;
import tn.esprit.entities.StockNotification;
import tn.esprit.entities.User;
import tn.esprit.entities.Volunteer;
import tn.esprit.services.CampService;
import tn.esprit.services.NeedImpl;
import tn.esprit.services.NewsService;
import tn.esprit.services.StockNotificationService;
import tn.esprit.services.RefugeeService;
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
	 @EJB
	 private RefugeeService rs;
	
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		initUsers();
		initStock();
		initNews();
		initCamps();
		initNotification();
		initNeeds();
		initRefugees();
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
		
		
		User u5= new DistrictChef();
		u5.setFirstName("hafsa");
		u5.setLastName("mtu");
		u5.setEmail("hafsa.mtu@esprit.com");
		u5.setDisable(false);
		u5.setPassword("DistrictChef");
		us.registerUser(u5);
		
		
		User u9= new Volunteer();
		u9.setFirstName("mohamed amin");
		u9.setLastName("garci");
		u9.setEmail("mohamedamine.garci@esprit.tn");
		u9.setDisable(false);
		u9.setPassword("Volunteer");
		us.registerUser(u9);
		
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
	
	private void initRefugees(){
		
		Refugee r1 = new Refugee();
		r1.setFirstname("moez");
		r1.setLastName("Feki");
		r1.setPhoneNumber(52314985);
		r1.setDateOfBirth(new Date(1993-1900,05,25));
		r1.setSex("homme");
		r1.setYearsOfExperience(3);
		rs.add(r1);	
		
		Refugee r2 = new Refugee(); 
		r2.setFirstname("Khaled");
		r2.setLastName("Feki");
		r2.setPhoneNumber(52314985);
		r2.setDateOfBirth(new Date(2012-1900,05-1,19));
		r2.setSex("homme");
		r2.setYearsOfExperience(3);
		rs.add(r2);
		
		Refugee r3 = new Refugee();
		r3.setFirstname("Walid");
		r3.setLastName("ghedira");
		r3.setPhoneNumber(21365478);
		r3.setDateOfBirth(new Date(2009-1900,05-1,16));
		r3.setSex("homme");
		r3.setYearsOfExperience(3);
		rs.add(r3);
		
		Refugee r4 = new Refugee();
		r4.setFirstname("soufia");
		r4.setLastName("ghedira");
		r4.setPhoneNumber(21569874);
		r4.setDateOfBirth(new Date(1930-1900,06-1,30));
		r4.setSex("femme");
		r4.setYearsOfExperience(3);
		rs.add(r4);
		
		Refugee r5 = new Refugee();
		r5.setFirstname("michelle");
		r5.setLastName("alora");
		r5.setPhoneNumber(21569874);
		r5.setDateOfBirth(new Date(1950-1900,03-1,02));
		r5.setSex("femme");
		r5.setYearsOfExperience(2);
		rs.add(r5);
		
		Refugee r6 = new Refugee();
		r6.setFirstname("jack");
		r6.setLastName("fina");
		r6.setPhoneNumber(23658741);
		r6.setDateOfBirth(new Date(1990-1900,03-1,10));
		r6.setSex("homme");
		r6.setYearsOfExperience(2);
		rs.add(r6);
		
		Refugee r7 = new Refugee();
		r7.setFirstname("steve");
		r7.setLastName("job");
		r7.setPhoneNumber(22258741);
		r7.setDateOfBirth(new Date(2005-1900,05-1,23));
		r7.setSex("homme");
		r7.setYearsOfExperience(2);
		rs.add(r7);
		
		Refugee r8 = new Refugee();
		r8.setFirstname("majda");
		r8.setLastName("loumi");
		r8.setPhoneNumber(22269741);
		r8.setDateOfBirth(new Date(1987-1900,07-1,12));
		r8.setSex("femme");
		r8.setYearsOfExperience(2);
		rs.add(r8);
		
		Refugee r9 = new Refugee();
		r9.setFirstname("maria");
		r9.setLastName("safir");
		r9.setPhoneNumber(22266341);
		r9.setDateOfBirth(new Date(1920-1900,07-1,04));
		r9.setSex("femme");
		r9.setYearsOfExperience(0);
		rs.add(r9);
		
		Refugee r10 = new Refugee();
		r10.setFirstname("louis");
		r10.setLastName("fonci");
		r10.setPhoneNumber(25697341);
		r10.setDateOfBirth(new Date(1960-1900,03-1,9));
		r10.setSex("homme");
		r10.setYearsOfExperience(1);
		rs.add(r10);
		
	}
	
}
