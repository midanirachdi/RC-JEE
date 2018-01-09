package tn.esprit.utils;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import tn.esprit.entities.Admin;
import tn.esprit.entities.Camp;
import tn.esprit.entities.CampChef;
import tn.esprit.entities.DistrictChef;
import tn.esprit.entities.Evenement;
import tn.esprit.entities.JobOffer;
import tn.esprit.entities.Need;
import tn.esprit.entities.News;
import tn.esprit.entities.Refugee;
import tn.esprit.entities.Refugee.HighestDegreeEnum;
import tn.esprit.entities.Refugee.LanguageLevelEnum;
import tn.esprit.entities.Stock;
import tn.esprit.entities.Stock.stockNeedsEnum;
import tn.esprit.entities.StockNotification;
import tn.esprit.entities.Task;
import tn.esprit.entities.User;
import tn.esprit.entities.Volunteer;
import tn.esprit.services.CampService;
import tn.esprit.services.EvenementService;
import tn.esprit.services.JobOfferImpl;
import tn.esprit.services.NeedImpl;
import tn.esprit.services.NewsService;
import tn.esprit.services.RefugeeService;
import tn.esprit.services.StockNotificationService;
import tn.esprit.services.StockService;
import tn.esprit.services.TaskImpl;
import tn.esprit.services.UserService;



@WebListener
public class IntializerServlet implements javax.servlet.ServletContextListener  {

	 @EJB 
	 private TaskImpl ts;
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

	 @EJB
	 private JobOfferImpl js;
	 
	 @EJB 
	 private EvenementService es;
	
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		initUsers();
		initStock();
		initNews();
		initCamps();
		initEvenements();
		initNotification();
		initNeeds();
		initRefugees();
		try {
			initJobOffers();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	private void initTasks(){
		
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
		u4.setFirstName("salim");
		u4.setLastName("salim");
		u4.setEmail("salim@test.com");
		u4.setDisable(false);
		u4.setPassword("salim");
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
		
		User u11= new CampChef();
		u11.setFirstName("ahmed");
		u11.setLastName("derbala");
		u11.setEmail("ahmed.derbala@esprit.com");
		u11.setDisable(false);
		u11.setPassword("CampChef");
		us.registerUser(u11);
		
		Task t = new Task();
		t.setName("install new tents");
		t.setDescription("use the new bought tents");
		t.setStartDate(new Date());
		t.setEndDate(new Date());
		t.setProgress(50);
		t.setStatus("started");
		t.setAssignedTo(u2);
		
		Task t1 = new Task();
		t1.setName("Repair the main building roof ");
		t1.setDescription("talk to the concerned district chief");
		t1.setStartDate(new Date());
		t1.setEndDate(new Date());
		t1.setProgress(100);
		t1.setStatus("closed");
		t1.setAssignedTo(u2);
		
		Task t2 = new Task();
		t2.setName("Clean the 3rd district");
		t2.setDescription("split into two groups");
		t2.setStartDate(new Date());
		t2.setEndDate(new Date());
		t2.setProgress(80);
		t2.setStatus("started");
		t2.setAssignedTo(u3);
		
		Task t3 = new Task();
		t3.setName("Add all the refugees to the platform");
		t3.setDescription("verify coordinates");
		t3.setStartDate(new Date());
		t3.setEndDate(new Date());
		t3.setProgress(100);
		t3.setStatus("closed");
		t3.setAssignedTo(u4);
		
	}
	private void initEvenements(){
		Evenement ev1=new Evenement();
		ev1.setDateEvent(new Date());
		ev1.setName("Tous pour l'humanité");
		ev1.setLocation("Tunis,Centre Ville");
		ev1.setImagename("humanite");
		ev1.setNbplace(50);
		ev1.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
		ev1.setCreator((CampChef)us.find(3));
		es.add(ev1);
		Evenement ev2=new Evenement();
		ev2.setDateEvent(new Date());
		ev2.setName("Refugees all over England");
		ev2.setLocation("UK,England");
		ev2.setImagename("engrefs");
		ev2.setNbplace(25);
		ev2.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
		ev2.setCreator((CampChef)us.find(3));
		es.add(ev2);
		Evenement ev3=new Evenement();
		ev3.setDateEvent(new Date());
		ev3.setName("Snow Event");
		ev3.setLocation("Suisse,Genéve");
		ev3.setImagename("snow");
		ev3.setNbplace(42);
		ev3.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
		ev3.setCreator((CampChef)us.find(3));
		es.add(ev3);
		Evenement ev4=new Evenement();
		ev4.setDateEvent(new Date());
		ev4.setName("Help Syrian everywhere");
		ev4.setLocation("Syria,Damascus");
		ev4.setImagename("event2");
		ev4.setNbplace(150);
		ev4.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
		ev4.setCreator((CampChef)us.find(3));
		es.add(ev4);
		Evenement ev5=new Evenement();
		ev5.setDateEvent(new Date());
		ev5.setName("All For Russia");
		ev5.setLocation("Russia,Moscow");
		ev5.setImagename("event3");
		ev5.setNbplace(130);
		ev5.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
		ev5.setCreator((CampChef)us.find(10));
		es.add(ev5);
		Evenement ev6=new Evenement();
		ev6.setDateEvent(new Date());
		ev6.setName("United as Community");
		ev6.setLocation("France,Paris");
		ev6.setImagename("event4");
		ev6.setNbplace(25);
		ev6.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
		ev6.setCreator((CampChef)us.find(10));
		es.add(ev6);
		Evenement ev7=new Evenement();
		ev7.setDateEvent(new Date());
		ev7.setName("Refugees of Canada");
		ev7.setLocation("Canada,Montreal");
		ev7.setImagename("event5");
		ev7.setNbplace(250);
		ev7.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
		ev7.setCreator((CampChef)us.find(10));
		es.add(ev7);
		
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
		Admin u=new Admin();
		u.setId(1);
	
		Stock st=new Stock();
		st.setStockType(Stock.stockNeedsEnum.Water);
		st.setNotes("nothing");
		st.setQteTotal(100);
		st.setAdmin(u);
		st.setQteInStock(65);
		st.setStockValue(500);
		ss.add(st);
		
		
		Stock st2=new Stock();
		st2.setStockType(Stock.stockNeedsEnum.Clothes);
		st2.setNotes("nothing");
		st2.setQteTotal(100);
		st2.setQteInStock(65);
		st2.setStockValue(500);
		st2.setAdmin(u);
		ss.add(st2);
		
		Stock st3=new Stock();
		st3.setStockType(Stock.stockNeedsEnum.Medicines);
		st3.setNotes("nothing");
		st3.setQteTotal(100);
		st3.setQteInStock(80);
		st3.setAdmin(u);
		st3.setStockValue(500);
		ss.add(st3);
	}
	
	private void initNews() {
		String st="Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
		
		Admin u=new Admin();
		u.setId(1);
		News n=new News();
		n.setAuthor("Salim");
		n.setContent(st);
		n.setTitle("What is Lorem Ipsum?");
		n.setAdmin(u);
		n.setLocation("Tunisia");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		n.setDateOfCreation(timestamp);
		ns.add(n);
		
		News n2=new News();
		n2.setAuthor("Salim");
		n2.setContent(st);
		n2.setTitle("What is Lorem Ipsum?");
		n2.setAdmin(u);
		n2.setLocation("Japan");
		n2.setDateOfCreation(timestamp);
		ns.add(n2);
		
		News n3=new News();
		n3.setAuthor("Salim");
		n3.setContent(st);
		n3.setTitle("Why do we use it?");
		n3.setAdmin(u);
		n3.setLocation("Tunisia");
		n3.setDateOfCreation(timestamp);
		ns.add(n3);
		
		News n4=new News();
		n4.setAuthor("Salim");
		n4.setContent(st);
		n4.setTitle("Where can I get some?");
		n4.setAdmin(u);
		n4.setLocation("Egypt");
		n4.setDateOfCreation(timestamp);
		ns.add(n4);
		
		News n5=new News();
		n5.setAuthor("Salim");
		n5.setContent(st);
		n5.setTitle("Where does it come from?");
		n5.setAdmin(u);
		n5.setLocation("France");
		n5.setDateOfCreation(timestamp);
		ns.add(n5);
			
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
	private void initJobOffers() throws ParseException{
		//TODO midani
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = " 2017-02-11";
		String dateString2 = " 2017-04-11";
			Date d = sdf.parse(dateString);
			Date d2 = sdf.parse(dateString2);
		
		
		
		CampChef cf=(CampChef)us.find(3);
		DistrictChef df=(DistrictChef)us.find(4);
		
		JobOffer jo1 = new JobOffer(
				"Manage technical incidents , "
				+ "monitor the incidents , "
				+ "ensure quality of services provided on "
				+ "the perimeter of the support incidents",
				d,
				d2,
				20111121,
				"IT",
				2100,
				"Focus",
				"Focus Building, Z.I Chotrana II, Ariana, 2036, Tunisia.",
				"recruit@focus.com",
				"Mohsen cherif",
				"Technical Support BI",
				df,
				cf);
		JobOffer jo2 = new JobOffer(
				"Work within a small development "
				+ "team to build and maintain business applications and "
				+ "tools , apply Agile development methodologies,build "
				+ "back-end (database) and front-end (UI) systems ,"
				+ "design and develop reports (reporting)",
				d,
				d2,
				784545447,
				"IT",
				1200,
				"EQUIPE TECHNIQUE TUNISIENNE",
				"Centre Urbain Nord , Résidence Malek Center, "
				+ "Bloc B / 7ème étage - Appartement B7-1, Tunis,"
				+ " 1003, Tunisia.",
				"recruit@ett.tn",
				"Zied Zarga",
				"Software engineer: Senior JAVA/ANGULARJS developer",
				df,
				cf);
		JobOffer jo3 = new JobOffer(
				"développer des projets plus au moins complexes "
				+ "avec les technologies suivantes: Zend, React ",
				d,
				d2,
				71941328,
				"IT",
				1200,
				"TUIU SARL",
				"Rue des Entrepreneurs, Immeuble Delta Center, "
				+ "Charguia 2, 5, 2035, Tunisia",
				"recruit@tuiu.tn",
				"Ali Salhi",
				"Développeur Web Full Stack",
				df,
				cf);
		js.add(jo1);
		js.add(jo2);
		js.add(jo3);				
				
	}
	
	private void initRefugees(){
		
		Refugee r1 = new Refugee();
		r1.setFirstname("moez");
		r1.setLastName("Feki");
		r1.setPhoneNumber(52314985);
		r1.setDateOfBirth(new Date(1993-1900,05,25));
		r1.setSex("homme");
		r1.setYearsOfExperience(3);
		r1.setFrenchlanguageLevel(LanguageLevelEnum.A);
		r1.setEnglishlanguageLevel(LanguageLevelEnum.C);
		r1.setHighestDegree(HighestDegreeEnum.BACplus5);
		r1.setYearsOfExperience(1);
		r1.setFieldOfWork("IT");
		r1.setEmail("midani.rachdi@esprit.tn");
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

		Refugee r11 = new Refugee();
		r11.setFirstname("Ali");
		r11.setLastName("Ahmdi");
		r11.setPhoneNumber(52314985);
		r11.setDateOfBirth(new Date(1993-1900,05,25));
		r11.setSex("homme");
		r11.setYearsOfExperience(3);
		r11.setFrenchlanguageLevel(LanguageLevelEnum.C);
		r11.setEnglishlanguageLevel(LanguageLevelEnum.C);
		r11.setHighestDegree(HighestDegreeEnum.BACplus5);
		r11.setYearsOfExperience(1);
		r11.setFieldOfWork("IT");
		r11.setEmail("rachdi.midani@gmail.com");
		rs.add(r11);
		
		Refugee r12 = new Refugee();
		r12.setFirstname("Mohsen");
		r12.setLastName("Cherif");
		r12.setPhoneNumber(52314985);
		r12.setDateOfBirth(new Date(1993-1900,05,25));
		r12.setSex("homme");
		r12.setYearsOfExperience(1);
		r12.setFrenchlanguageLevel(LanguageLevelEnum.C);
		r12.setEnglishlanguageLevel(LanguageLevelEnum.C);
		r12.setHighestDegree(HighestDegreeEnum.BACplus8);
		r12.setYearsOfExperience(1);
		r12.setFieldOfWork("IT");
		r12.setEmail("midani.rachdi@esprit.tn");
		rs.add(r12);
	}
	
}
