package tn.esprit.utils;


import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import tn.esprit.entities.Admin;
import tn.esprit.entities.CampChef;
import tn.esprit.entities.DistrictChef;
import tn.esprit.entities.JobOffer;
import tn.esprit.entities.News;
import tn.esprit.entities.Stock;
import tn.esprit.entities.User;
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
	
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		initUsers();
		initStock();
		initNews();
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
	
	
}
