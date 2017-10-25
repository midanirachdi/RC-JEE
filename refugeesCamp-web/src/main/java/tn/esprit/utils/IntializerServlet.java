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
import tn.esprit.entities.News;
import tn.esprit.entities.Refugee;
import tn.esprit.entities.Stock;
import tn.esprit.entities.User;
import tn.esprit.services.CampService;
import tn.esprit.services.NewsService;
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
	 private RefugeeService rs;
	
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		initUsers();
		initStock();
		initNews();
		initCamps();
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
